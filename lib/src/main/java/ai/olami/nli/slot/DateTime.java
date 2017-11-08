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

public class DateTime {

	public static final String TYPE_TIME_RECOMMEND = "time_recommend";
	public static final String TYPE_SEMANTIC = "time_semantic";
	public static final String TYPE_INVALID = "invalid";
	
	@Expose
	@SerializedName("type")
	private String mType = null;

	/**
	 * @return Date time type.
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
	
	@Expose
	@SerializedName("data")
	private DateTimeData mDatetimeData = null;
	
	/**
	 * @return Detailed information.
	 */
	public DateTimeData getData() {
		return mDatetimeData;
	}
	
	/**
	 * @return TRUE if contains Data information.
	 */
	public boolean hasData() {
		return (mDatetimeData != null);
	}
	
}
