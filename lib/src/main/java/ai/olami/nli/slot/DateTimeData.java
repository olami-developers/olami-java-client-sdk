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

public class DateTimeData {
	
	public static final String SUB_TYPE_DURATION = "duration";
	public static final String SUB_TYPE_REPEAT = "repeat";

	@Expose
	@SerializedName("starttime")
	private int mStartTime = -1;
		
	/**
	 * @return Start time.
	 */
	public int getStartTime() {
		return mStartTime;
	}

	@Expose
	@SerializedName("endtime")
	private int mEndTime = -1;
	
	/**
	 * @return End time.
	 */	
	public int getEndTime() {
		return mEndTime;
	}

	@Expose
	@SerializedName("sub_type")
	private String mSubType = null;
	
	/**
	 * @return Sub type.
	 */
	public String getSubType() {
		return mSubType.toLowerCase();
	}
	
	/**
	 * @return TRUE if contains SubType information.
	 */
	public boolean hasSubType() {
		return (mSubType != null);
	}

	@Expose
	@SerializedName("time_struct")
	private TimeStruct mTimeStruct = null;

	/**
	 * @return The structure that describes the time period or the repetition.
	 */
	public TimeStruct getTimeStruct() {
		return mTimeStruct;
	}
	
	/**
	 * @return TRUE if contains TimeStruct information.
	 */
	public boolean hasTimeStruct() {
		return (mTimeStruct != null);
	}
	
}
