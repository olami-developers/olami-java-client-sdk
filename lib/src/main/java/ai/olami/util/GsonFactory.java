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

package ai.olami.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonFactory {
	
	private static Gson mDefaultGson = new GsonBuilder()
		.excludeFieldsWithoutExposeAnnotation().create();
	
	private static Gson mDebugGson = new GsonBuilder()
		.setPrettyPrinting()
		.create();
	
	private static Gson mDebugGsonWithoutEA = new GsonBuilder()
		.setPrettyPrinting()
		.excludeFieldsWithoutExposeAnnotation()
		.create();
	
	/**
	 * Create a new Gson instance with excludeFieldsWithoutExposeAnnotation().
	 * 
	 * @return GsonBuilder.
	 */
	public static Gson getNormalGson() {
		return mDefaultGson;
	}
	
	/**
	 * Create a new Gson instance with PrettyPrinting.
	 * 
	 * @param exposeAll - TRUE to create without excludeFieldsWithoutExposeAnnotation().
	 * @return Gson
	 */
	public static Gson getDebugGson(boolean exposeAll) {
		
		if (exposeAll) {
			return mDebugGson;
		}
		
		return mDebugGsonWithoutEA;
	}
	
}


