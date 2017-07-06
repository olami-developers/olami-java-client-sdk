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

import ai.olami.cloudService.APIResponse;
import ai.olami.util.GsonFactory;

import com.google.gson.Gson;

public class DumpNLIResultsExampleExecution {

    public static void main(String[] args) throws Exception {
		if (args.length == 1) {
			Gson gson = GsonFactory.getNormalGson();
			APIResponse response = (APIResponse) gson.fromJson(args[0], APIResponse.class);
			if (response.hasData() && response.getData().hasNLIResults()) {
				DumpNLIResultsExample.dumpNLIResults(response.getData().getNLIResults());
			} else {
				System.out.println("Failed! Invalid Input String.");
			}
		}
    }
}
