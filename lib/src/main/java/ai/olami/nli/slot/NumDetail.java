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

package ai.olami.nli.slot;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NumDetail {
	
	public static final String TYPE_NUMBER = "number";
	public static final String TYPE_FLOAT = "float";

	@Expose
	@SerializedName("recommend_value")
	private String mRecommendValue = null;

	/**
	 * @return Recommend value.
	 */
	public String getRecommendValue() {
		return mRecommendValue;
	}
	
	/**
	 * @return TRUE if contains RecommendValue information.
	 */
	public boolean hasRecommendValue() {
		return ((mRecommendValue != null) && (!mRecommendValue.equals("")));
	}
	
	@Expose
	@SerializedName("type")
	private String mType = null;
	
	/**
	 * @return The type of the number.
	 */
	public String getType() {
		return mType.toLowerCase();
	}
	
	/**
	 * @return TRUE if contains Type information.
	 */
	public boolean hasType() {
		return ((mType != null) && (!mType.equals("")));
	}
	
}
