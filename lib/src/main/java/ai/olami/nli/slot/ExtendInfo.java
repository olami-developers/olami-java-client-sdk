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

public class ExtendInfo {

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
	@SerializedName("DayOfWeek")
	private int mDayOfWeek = -1;
	
	public int getDayOfWeek() {
		return mDayOfWeek;
	}

	@Expose
	@SerializedName("festival")
	private String mFestival = null;
	
	/**
	 * @return Festival or holiday name.
	 */
	public String getFestival() {
		return mFestival;
	}
	
	/**
	 * @return TRUE if contains Festival information.
	 */
	public boolean hasFestival() {
		return (mFestival != null);
	}

	@Expose
	@SerializedName("jieqi")
	private String mJieqi = null;
	
	/**
	 * @return Name of the Solar Terms.
	 */
	public String getJieqi() {
		return mJieqi;
	}
	
	/**
	 * @return TRUE if contains Jieqi information.
	 */
	public boolean hasJieqi() {
		return (mJieqi != null);
	}
	
}
