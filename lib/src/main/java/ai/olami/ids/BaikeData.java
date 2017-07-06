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

import java.util.HashMap;
import java.util.Map;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaikeData {

	@Expose
	@SerializedName("field_name")
	private String[] mFieldNames = null;

	@Expose
	@SerializedName("field_value")
	private String[] mFieldValues = null;
	
	private Map<String, String> mFieldNameValues = null;
	
	private void initFieldNameValues() {
		if (mFieldNameValues == null) {
			mFieldNameValues = new HashMap<String, String>();
			for (int i = 0; i < mFieldNames.length; i++) {
				mFieldNameValues.put(mFieldNames[i], mFieldValues[i]);
			}
		}
	}
	
	/**
	 * Get number of fields.
	 * 
	 * @return Number of fields.
	 */
	public int getNumberOfFields() {
		initFieldNameValues();
		return mFieldNameValues.size();
	}
	
	/**
	 * Get name-value contents of all fields. 
	 * 
	 * @return The name-value contents.
	 */
	public Map<String, String> getFieldNameValues() {
		initFieldNameValues();
		return mFieldNameValues;
	}
	
	@Expose
	@SerializedName("photo_url")
	private String mPhotoURL = null;
	
	/**
	 * @return Photo URL.
	 */
	public String getPhotoURL() {
		return (mPhotoURL == null) ? "" : mPhotoURL;
	}
	
	/**
	 * @return TRUE if contains photo URL.
	 */
	public boolean hasPhotoURL() {
		return (mPhotoURL != null);
	}
	
	@Expose
	@SerializedName("type")
	private String mType = null;
	
	/**
	 * @return Content type.
	 */
	public String getType() {
		return (mType == null) ? "" : mType;
	}
	
	/**
	 * @return TRUE if contains type information.
	 */
	public boolean hasType() {
		return (mType != null);
	}
	
	@Expose
	@SerializedName("description")
	private String mDescription = null;
	
	/**
	 * @return Content description.
	 */
	public String getDescription() {
		return (mDescription == null) ? "" : mDescription;
	}
	
	/**
	 * @return TRUE if contains description.
	 */
	public boolean hasDescription() {
		return (mDescription != null);
	}
	
	@Expose
	@SerializedName("categorylist")
	private String[] mCategoryList = null;
	
	/**
	 * @return Category list of the result.
	 */
	public String[] getCategoryList() {
		return (mCategoryList == null) ? new String[]{""} : mCategoryList;
	}
	
	/**
	 * @return TRUE if contains category list of the result.
	 */
	public boolean hasCategoryList() {
		return (mCategoryList != null);
	}
	
	@Expose
	@SerializedName("highlight")
	private int[] mHighlights = null;
	
	/**
	 * @return Category list of the result.
	 */
	public int[] getHighlights() {
		return (mHighlights == null) ? new int[]{0} : mHighlights;
	}
	
	/**
	 * @return TRUE if contains category list of the result.
	 */
	public boolean hasHighlights() {
		return (mHighlights != null);
	}
	
}
