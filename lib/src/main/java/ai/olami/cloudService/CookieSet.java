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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CookieSet {
	
	private String mUniqueId = UUID.randomUUID().toString();
	private List<String> mMainCookies = null;
	
	/**
	 * List of cookies.
	 */
	public CookieSet() {
		mMainCookies = new ArrayList<String>();
	}
	
	/**
	 * Get unique ID.
	 * 
	 * @return The unique ID of this CookieSet.
	 */
	public String getUniqueID() {
		return mUniqueId;
	}
	
	/**
	 * Set cookies
	 * 
	 * @param contents - Cookies.
	 */
	public void setContents(List<String> contents) {
		mMainCookies = contents;
	}
	
	/**
	 * Get cookies
	 * 
	 * @return Cookies.
	 */
	public List<String> getContents() {
		return mMainCookies;
	}
	
}
