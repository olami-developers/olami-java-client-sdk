/*
	Copyright 2017, VIA Technologies, Inc. & OLAMI Team.
	
	http://olami.ai

	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at

	http://www.apache.org/licenses/LICENSE-2.0

	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
*/

package ai.olami.example;

import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

import ai.olami.cloudService.APIConfiguration;
import ai.olami.cloudService.TextRecognizer;
import ai.olami.nli.NLIResult;

public class AsyncTextChatbotExample {

	// * Replace your APP KEY with this variable.
	private static String appKey = "*****your-app-key*****";
	
	// * Replace your APP SECRET with this variable.
	private static String appSecret = "*****your-app-secret*****";
	
	// * Replace the localize option you want with this variable.
	// * - Use LOCALIZE_OPTION_SIMPLIFIED_CHINESE for China
	// * - Use LOCALIZE_OPTION_TRADITIONAL_CHINESE for Taiwan 
	private static int localizeOption = APIConfiguration.LOCALIZE_OPTION_SIMPLIFIED_CHINESE;
//	private static int localizeOption = APIConfiguration.LOCALIZE_OPTION_TRADITIONAL_CHINESE;
	
	public static void main(String[] args) throws Exception {
		if (args.length == 3) {
			initByInputArgs(args);
		} else if (args.length > 0) {
			printUsageAndExit();
		}
		
		// * Step 1: Configure your key and localize option.
		APIConfiguration config = new APIConfiguration(appKey, appSecret, localizeOption);
		
		// * Step 2: Create the text recognizer.
		TextRecognizer recoginzer = new TextRecognizer(config);
		
		System.out.format("\nTell me your name: ");
		Scanner reader = new Scanner(System.in);
		
		boolean fristTalk = true;		
		while (reader.hasNext()) {
			if (fristTalk) {
				String userName = reader.nextLine();
				if (userName.isEmpty()) {
					userName = " YOU ";
				}
				// Setup end user information.
				recoginzer.setEndUserIdentifier(userName);
				
				fristTalk = false;
				System.out.format("\nHi! %s\n", userName);
				System.out.format("\nType to say something or 'bye' to exit:\n");
			}
			
			String whatUserSays = reader.nextLine();
			if (whatUserSays.toLowerCase().equals("bye")) {
				System.out.format("\n[ OLAMI Robot ] Says: Bye!\n\n");
				reader.close();
				break;
			} else {
				System.out.format("\n[ %s ] Says: %s\n",
						recoginzer.getEndUserIdentifier(),
						whatUserSays);
			}

			// Create async task to request OLAMI API.
			CompletableFuture.supplyAsync(() -> {
				// Send text to the recognizer.
				try {
					return recoginzer.requestNLI(whatUserSays);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}).whenComplete((response, exception) -> {
				// Get results to see the conversation reply.
				if (response.ok() && response.hasData()) {
					NLIResult[] nliResults = response.getData().getNLIResults();
					// Get the reply content.
					if (nliResults[0].hasDescObject()) {
						String reply = nliResults[0].getDescObject().getReplyAnswer();
						if (reply.isEmpty()) {
							System.out.format("\n[ OLAMI Robot ] Says: ...\n");
						} else {
							// Show the reply.
							System.out.format(
									"\n[ OLAMI Robot ] Says: %s", reply);
							// Show IDS data.
							if (nliResults[0].isFromIDS() 
									&& nliResults[0].hasDataObjects()) {
								System.out.println("\n");
								DumpIDSDataExample.dumpIDSData(nliResults[0]);
								System.out.println("\n");
							}
						}
						System.out.format(" (Say 'bye' to exit)\n");
					}
				}
			});			
		}
	}
	
    private static void printUsageAndExit() {
		System.out.println("\n Usage:");
		System.out.println("\t args[0]: your_app_key");
		System.out.println("\t args[1]: your_app_secret");
		System.out.println("\t args[2]: localize_option=[0|1]");
		System.out.println("\n");
		System.exit(0);
    }
    
    private static void initByInputArgs(String[] args) {
		appKey = args[0];
		appSecret = args[1];
		localizeOption = Integer.parseInt(args[2]);
    }

}
