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

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import ai.olami.util.GsonFactory;

public class NLIConfig {

	@Expose(serialize = false, deserialize = false)
	private Gson mGson = GsonFactory.getNormalGson();
	
	// Members ------------------------------------------------------------------------------------------
	
	@Expose
	@SerializedName("slotname")
	private String mSlotName = null;

	/**
	 * Set slot name.
	 */
	public void setSlotName(String name) {
		mSlotName = name;
	}
	
	/**
	 * @return Slot Name.
	 */
	public String getSlotName() {
		return mSlotName;
	}
	
	/**
	 * @return TRUE if contains slot name.
	 */
	public boolean hasSlotName() {
		return ((mSlotName != null) && (!mSlotName.equals("")));
	}
	
	// Common Methods ------------------------------------------------------------------------------------
	
	/**
	 * Reset all members.
	 */
	public void reset() {
		mSlotName = null;
	}
	
	/**
	 * @return GSON Json Element.
	 */
	public JsonElement toJsonElement() {
		return mGson.fromJson(toString(), JsonElement.class);
	}
	
	@Override
	public String toString() {
		return mGson.toJson(this);
	}
	
}
