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

import com.google.gson.Gson;

import ai.olami.cloudService.APIConfiguration;
import ai.olami.cloudService.APIResponse;
import ai.olami.cloudService.TextRecognizer;
import ai.olami.nli.NLIResult;
import ai.olami.util.GsonFactory;

public class TextInputExample {
	
	// * Replace your APP KEY with this variable.
	private static String appKey = "172c5b7b7121407ba572da444a17977c";
	
	// * Replace your APP SECRET with this variable.
	private static String appSecret = "2115d0888bd049549581b7a0a67cde62";
	
	// * Replace the text you want to analyze with this variable.
	private static String inputText = "我要唱歌";
	
	// * Replace the localize option you want with this variable.
	// * - Use LOCALIZE_OPTION_SIMPLIFIED_CHINESE for China
	// * - Use LOCALIZE_OPTION_TRADITIONAL_CHINESE for Taiwan 
	private static int localizeOption = APIConfiguration.LOCALIZE_OPTION_SIMPLIFIED_CHINESE;
//	private static int localizeOption = APIConfiguration.LOCALIZE_OPTION_TRADITIONAL_CHINESE;	
	
    public static void main(String[] args) throws Exception {
		if (args.length == 4) {
			initByInputArgs(args);
		} else if (args.length > 0) {
			printUsageAndExit();
		}
		
		Gson jsonDump = GsonFactory.getDebugGson(false);
		
		// * Step 1: Configure your key and localize option.
		APIConfiguration config = new APIConfiguration(appKey, appSecret, localizeOption);
		
		// * Step 2: Create the text recognizer.
		TextRecognizer recoginzer = new TextRecognizer(config);
		
		// * Optional steps: Setup some other configurations.
		recoginzer.setEndUserIdentifier("Someone");
		recoginzer.setTimeout(10000);
		
		// EXAMPLE 1: Request word segmentation analysis.
		System.out.println("\n[Example 1] ================================");
		System.out.println("\nRequest word segmentation for : " + inputText);
		// * Send text
		APIResponse wsResponse = recoginzer.requestWordSegmentation(inputText); 
		System.out.println("\nOriginal Response : " + wsResponse.toString());
		System.out.println("\n---------- dump ----------\n");
		System.out.println(jsonDump.toJson(wsResponse));
		System.out.println("\n--------------------------\n");
		// Check request status.
		if (wsResponse.ok() && wsResponse.hasData()) {
			// * Get word segmentation results.
			String[] ws = wsResponse.getData().getWordSegmentation();
			for (int i = 0; i < ws.length; i++) {
				System.out.println("Word[" + i + "] " + ws[i]);
			}	
		} else {
			// Error
			System.out.println("* Error! Code : " + wsResponse.getErrorCode());
			System.out.println(wsResponse.getErrorMessage());
		}
		
		// EXAMPLE 2: Request NLI analysis.
		System.out.println("\n[Example 2] ================================");
		System.out.println("\nRequest NLI analysis for : " + inputText);
		// * Send text
		APIResponse nliResponse = recoginzer.requestNLI(inputText);
		System.out.println("\nOriginal Response : " + nliResponse.toString());
		System.out.println("\n---------- dump ----------\n");
		System.out.println(jsonDump.toJson(nliResponse));
		System.out.println("\n--------------------------\n");
		// Check request status.
		if (nliResponse.ok() && nliResponse.hasData()) {
			// * Get NLI results.
			NLIResult[] nliResults = nliResponse.getData().getNLIResults();
			//
			// You can parse NLI results here and do something.
			//
			// ================================================
			// In this example, we will dump NLI results.
			//
			DumpNLIResultsExample.dumpNLIResults(nliResults);
			//
			// See also: /examples/dump-nli-results-example/
			// ================================================
		} else {
			// Error
			System.out.println("* Error! Code : " + nliResponse.getErrorCode());
			System.out.println(nliResponse.getErrorMessage());
		}	
		
		System.out.println("\n===========================================\n");
    }
    
    private static void printUsageAndExit() {
		System.out.println("\n Usage:");
		System.out.println("\t args[0]: your_app_key");
		System.out.println("\t args[1]: your_app_secret");
		System.out.println("\t args[2]: localize_option=[0|1]");
		System.out.println("\t args[3]: the_text_you_want_to_test");
		System.out.println("\n");
		System.exit(0);
    }
    
    private static void initByInputArgs(String[] args) {
		appKey = args[0];
		appSecret = args[1];
		localizeOption = Integer.parseInt(args[2]);
		inputText = args[3];
    }
    
}
