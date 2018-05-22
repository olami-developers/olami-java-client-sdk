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

package ai.olami.ids;

import java.lang.reflect.Type;
import java.util.ArrayList;


import com.google.gson.reflect.TypeToken;

public class IDSResult {	
	
	/**
	 * IDS Modules
	 */
	public static enum Types {
		QUESTION ("question", null),
		CONFIRMATION ("confirmation", null),
		SELECTION ("selection", null),
		DATE ("date", null),
		NONSENSE ("nonsense", null),
		ZIP_CODE ("zipcode", null),
		MATH_24 ("math24", null),
		WEATHER ("weather", (new TypeToken<ArrayList<WeatherData>>() {}).getType()),
		BAIKE ("baike", (new TypeToken<ArrayList<BaikeData>>() {}).getType()),
		NEWS ("news", (new TypeToken<ArrayList<NewsData>>() {}).getType()),
		KKBOX ("kkbox", (new TypeToken<ArrayList<KKBOXData>>() {}).getType()),
		MUSIC_CONTROL ("MusicControl", (new TypeToken<ArrayList<MusicControlData>>() {}).getType()),
		TV_PROGRAM ("tvprogram", (new TypeToken<ArrayList<TVProgramData>>() {}).getType()),
		POEM ("poem", (new TypeToken<ArrayList<PoemData>>() {}).getType()),
		JOKE ("joke", (new TypeToken<ArrayList<JokeData>>() {}).getType()),
		STOCK_MARKET ("stock", (new TypeToken<ArrayList<StockMarketData>>() {}).getType()),
		MATH ("math", (new TypeToken<ArrayList<MathData>>() {}).getType()),
		UNIT_CONVERT ("unitconvert", (new TypeToken<ArrayList<UnitConvertData>>() {}).getType()),
		EXCHANGE_RATE ("exchangerate", (new TypeToken<ArrayList<ExchangeRateData>>() {}).getType()),
		COOKING ("cooking", (new TypeToken<ArrayList<CookingData>>() {}).getType()),
		OPEN_WEB ("openweb", (new TypeToken<ArrayList<OpenWebData>>() {}).getType());
		
		private String name;
		private Type dataObjArrayListType;
		
		private Types(
				String name,
				Type dataObjArrayListType
		) {
			this.name = name;
			this.dataObjArrayListType = dataObjArrayListType;
		}
		
		/**
		 * @return Module name
		 */
		public String getName() {
			return this.name;
		}
		
		/**
		 * @return Type of the DataObject ArrayList.
		 */
		public Type getDataArrayListType() {
			return this.dataObjArrayListType;
		}
	
		/**
		 * Check if the given name exists in module type list.
		 * 
		 * @param moduleName - Module name you want to check.
		 * @return TRUE if the given name exists in module type list.
		 */
		public static boolean contains(String moduleName) {
			Object[] list = Types.class.getEnumConstants();
			for (Object t : list) {
				if (((Types) t).getName().equals(moduleName)) {
					return true;
				}
			}
			return false;
		}
		
		/**
		 * Get enum by mdoule name.
		 * @param moduleName - Module name you want to find.
		 * @return Module enum.
		 */
		public static Types	getByName(String moduleName) {
			Object[] list = Types.class.getEnumConstants();
			for (Object t : list) {
				if (((Types) t).getName().equals(moduleName)) {
					return ((Types) t);
				}
			}
			return null;
		}
		
		/**
		 * Get DataObject array type by the specified module name.
		 * 
		 * @param moduleName - Module name you want to find.
		 * @return Type of the DataObject ArrayList.
		 */
		public static Type getDataArrayListType(String moduleName) {
			Object[] list = Types.class.getEnumConstants();
			for (Object t : list) {
				if (((Types) t).getName().equals(moduleName)) {
					return ((Types) t).getDataArrayListType();
				}
			}
			return null;
		}
	}
	
}
