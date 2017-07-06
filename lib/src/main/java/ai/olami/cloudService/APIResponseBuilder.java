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

package ai.olami.cloudService;

import ai.olami.util.GsonFactory;

public class APIResponseBuilder {

	/**
	 * Create API response instance by the specified response JSON string.
	 * 
	 * @param jsonString - API Response JSON string.
	 * @return The instance mapped to the specified response JSON string.
	 */
	public static APIResponse create(String jsonString) {	

		APIResponse response = null;
				
		try {
			response = (APIResponse) GsonFactory.getNormalGson().fromJson(jsonString, APIResponse.class);
			response.setJsonStringSource(jsonString);
		} catch (Exception e) {
			response.setInvalid("Invalid Response Content --> \n" + jsonString + "\n\n" + e.getMessage());
			e.printStackTrace();
		}
		
		return response;
		
	}
	
}
