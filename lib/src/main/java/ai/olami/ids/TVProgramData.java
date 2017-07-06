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

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TVProgramData {

	@Expose
	@SerializedName("name")
	private String mName = null;
	
	/**
	 * @return TV program name.
	 */
	public String getName() {
		return (mName == null) ? "" : mName;
	}
	
	/**
	 * @return TRUE if contains TV program name.
	 */
	public boolean hasName() {
		return (mName != null);
	}
	
	@Expose
	@SerializedName("time")
	private String mTime = null;
	
	/**
	 * @return Date-time information.
	 */
	public String getTime() {
		return (mTime == null) ? "" : mTime;
	}
	
	/**
	 * @return TRUE if contains date-time information.
	 */
	public boolean hasTime() {
		return (mTime != null);
	}
	
	@Expose
	@SerializedName("highlight")
	private int mHighlight = 0;
	
	/**
	 * @return TRUE if this content is matches the search condition.
	 */
	public boolean isHighlight() {
		return (mHighlight == 1);
	}
	
}
