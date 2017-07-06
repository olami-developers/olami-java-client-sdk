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

public class MathData {

	@Expose
	@SerializedName("content")
	private String mContent = null;
	
	/**
	 * @return Reply content.
	 */
	public String getContent() {
		return (mContent == null) ? "" : mContent;
	}
	
	/**
	 * @return TRUE if contains reply content information.
	 */
	public boolean hasContent() {
		return (mContent != null);
	}
	
	@Expose
	@SerializedName("result")
	private String mResult = null;
	
	/**
	 * @return Result.
	 */
	public String getResult() {
		return (mResult == null) ? "" : mResult;
	}
	
	/**
	 * @return TRUE if contains result.
	 */
	public boolean hasResult() {
		return (mResult != null);
	}
	
}
