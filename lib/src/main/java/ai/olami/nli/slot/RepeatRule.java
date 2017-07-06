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

public class RepeatRule {
	
	public static final String INTERVAL_UNIT_YEAR = "Year";
	public static final String INTERVAL_UNIT_MONTH = "Month";
	public static final String INTERVAL_UNIT_DAY = "Day";
	public static final String INTERVAL_UNIT_HOUR = "Hour";
	public static final String INTERVAL_UNIT_MINUTE = "Minute";
	public static final String INTERVAL_UNIT_SECOND = "Second";
	public static final String INTERVAL_UNIT_WEEK = "Week";
	public static final String INTERVAL_UNIT_INVALID = "Invalid";
	
	@Expose
	@SerializedName("IntervalUnit")
	private String mIntervalUnit = null;

	/**
	 * @return The unit of the repetition.
	 */
	public String getIntervalUnit() {
		return mIntervalUnit;
	}
	
	/**
	 * @return TRUE if contains IntervalUnit information.
	 */
	public boolean hasIntervalUnit() {
		return (mIntervalUnit != null);
	}
	
	@Expose
	@SerializedName("IntervalValue")
	private int mIntervalValue = -1;
	
	/**
	 * @return The value of the repetition.
	 */
	public int getIntervalValue() {
		return mIntervalValue;
	}
	
}
