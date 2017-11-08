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

package ai.olami.nli;

import ai.olami.nli.slot.Slot;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Semantic {

	@Expose
	@SerializedName("modifier")
	private String[] mGlobalModifiers = null;
	
	/**
	 * @return Global modifiers
	 */
	public String[] getGlobalModifiers() {
		return mGlobalModifiers;
	}
	
	/**
	 * @return TRUE if contains GlobalModifier information.
	 */
	public boolean hasGlobalModifiers() {
		return ((mGlobalModifiers != null) && (mGlobalModifiers.length > 0));
	}
	
	@Expose
	@SerializedName("app")
	private String mAppModule = null;
	
	/**
	 * Get the grammar module name. (Usually defined on the NLI system)
	 * 
	 * @return Module name.
	 */
	public String getAppModule() {
		return mAppModule;
	}
	
	/**
	 * @return TRUE if contains AppModule information.
	 */
	public boolean hasAppModule() {
		return ((mAppModule != null) && (!mAppModule.equals("")));
	}
	
	@Expose
	@SerializedName("input")
	private String mInput = null;
		
	/**
	 * @return The original input text.
	 */
	public String getInput() {
		return mInput;
	}

	/**
	 * @return TRUE if contains Input information.
	 */
	public boolean hasInput() {
		return ((mInput != null) && (!mInput.equals("")));
	}

	@Expose
	@SerializedName("slots")
	private Slot[] mSlots = null;
		
	/**
	 * @return Slot array.
	 */
	public Slot[] getSlots() {
		return mSlots;
	}
	
	/**
	 * @return TRUE if contains Slots information.
	 */
	public boolean hasSlots() {
		return ((mSlots != null) && (mSlots.length > 0));
	}
	
}
