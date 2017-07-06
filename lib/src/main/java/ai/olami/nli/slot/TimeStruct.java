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

public class TimeStruct {

	@Expose
	@SerializedName("Year")
	private int mYear = -1;
	
	public int getYear() {
		return mYear;
	}

	@Expose
	@SerializedName("Month")
	private int mMonth = -1;
	
	public int getMonth() {
		return mMonth;
	}

	@Expose
	@SerializedName("Day")
	private int mDay = -1;

	public int getDay() {
		return mDay;
	}

	@Expose
	@SerializedName("Hour")
	private int mHour = -1;
	
	public int getHour() {
		return mHour;
	}

	@Expose
	@SerializedName("Minute")
	private int mMinute = -1;
	
	public int getMinute() {
		return mMinute;
	}

	@Expose
	@SerializedName("Second")
	private int mSecond = -1;
	
	public int getSecond() {
		return mSecond;
	}

	@Expose
	@SerializedName("Week")
	private int mWeek = -1;
	
	public int getWeek() {
		return mWeek;
	}

	@Expose
	@SerializedName("repeat_rule")
	private RepeatRule mRepeatRule = null;
	
	/**
	 * @return The structure that describes repetition rule.
	 */
	public RepeatRule getRepeatRule() {
		return mRepeatRule;
	}
	
	/**
	 * @return TRUE if contains RepeatRule information.
	 */
	public boolean hasRepeatRule() {
		return (mRepeatRule != null);
	}

	@Expose
	@SerializedName("extend_info")
	private ExtendInfo mExtendInfo = null;
	
	/**
	 * @return Extra information.
	 */
	public ExtendInfo getExtendInfo() {
		return mExtendInfo;
	}
	
	/**
	 * @return TRUE if contains ExtendInfo information.
	 */
	public boolean hasExtendInfo() {
		return (mExtendInfo != null);
	}
	
}
