/*
	Copyright 2018, VIA Technologies, Inc. & OLAMI Team.
	
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

public class KKBOXDataPhoto {

	@Expose
	@SerializedName("width")
	private int mWidth = 0;
	
	/**
	 * @return Width.
	 */
	public int getWidth() {
		return mWidth;
	}
	
	@Expose
	@SerializedName("height")
	private int mHeight = 0;
	
	/**
	 * @return Height.
	 */
	public int getHeight() {
		return mHeight;
	}
	
	@Expose
	@SerializedName("url")
	private String mURL = null;
	
	/**
	 * @return URL.
	 */
	public String getURL() {
		return (mURL == null) ? "" : mURL;
	}
	
	/**
	 * @return TRUE if contains URL.
	 */
	public boolean hasURL() {
		return ((mURL != null) && (!mURL.equals("")));
	}
	
}
