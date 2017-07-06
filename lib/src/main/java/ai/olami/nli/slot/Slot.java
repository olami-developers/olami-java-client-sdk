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

public class Slot {

	@Expose
	@SerializedName("name")
	private String mName = null;
	
	/**
	 * @return Slot name.
	 */
	public String getName() {
		return mName;
	}
	
	/**
	 * @return TRUE if contains Name information.
	 */
	public boolean hasName() {
		return (mName != null);
	}

	@Expose
	@SerializedName("value")
	private String mValue = null;
	
	/**
	 * @return Slot value.
	 */
	public String getValue() {
		return mValue;
	}
	
	/**
	 * @return TRUE if contains Value information.
	 */
	public boolean hasValue() {
		return (mValue != null);
	}

	@Expose
	@SerializedName("modifier")
	private String[] mModifiers = null;
	
	/**
	 * @return Slot modifier.
	 */
	public String[] getModifiers() {
		return mModifiers;
	}
	
	/**
	 * @return TRUE if contains Modifier information.
	 */
	public boolean hasModifiers() {
		return (mModifiers != null);
	}

	@Expose
	@SerializedName("datetime")
	private DateTime mDateTime = null;
	
	/**
	 * @return Date time analysis results.
	 */
	public DateTime getDateTime() {
		return mDateTime;
	}
	
	/**
	 * @return TRUE if contains DateTime information.
	 */
	public boolean hasDateTime() {
		return (mDateTime != null);
	}

	@Expose
	@SerializedName("num_detail")
	private NumDetail mNumDetail = null;

	/**
	 * @return The detailed information of the number slot.
	 */
	public NumDetail getNumDetail() {
		return mNumDetail;
	}
	
	/**
	 * @return TRUE if contains NumDetail information.
	 */
	public boolean hasNumDetail() {
		return (mNumDetail != null);
	}
	
}
