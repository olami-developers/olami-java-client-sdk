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

public class NewsData {

	@Expose
	@SerializedName("title")
	private String mTitle = null;
	
	/**
	 * @return Title.
	 */
	public String getTitle() {
		return (mTitle == null) ? "" : mTitle;
	}
	
	/**
	 * @return TRUE if contains title.
	 */
	public boolean hasTitle() {
		return (mTitle != null);
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
	@SerializedName("image_url")
	private String mImageURL = null;
	
	/**
	 * @return Image URL.
	 */
	public String getImageURL() {
		return (mImageURL == null) ? "" : mImageURL;
	}
	
	/**
	 * @return TRUE if contains image URL.
	 */
	public boolean hasImageURL() {
		return (mImageURL != null);
	}
	
	@Expose
	@SerializedName("detail")
	private String mDetail = null;
	
	/**
	 * @return News detail.
	 */
	public String getDetail() {
		return (mDetail == null) ? "" : mDetail;
	}
	
	/**
	 * @return TRUE if contains news detail.
	 */
	public boolean hasDetail() {
		return (mDetail != null);
	}
	
	@Expose
	@SerializedName("ref_url")
	private String mRefURL = null;
	
	/**
	 * @return Source URL of the news.
	 */
	public String getSourceURL() {
		return (mRefURL == null) ? "" : mRefURL;
	}
	
	/**
	 * @return TRUE if contains source URL.
	 */
	public boolean hasSourceURL() {
		return (mRefURL != null);
	}
	
	@Expose
	@SerializedName("source")
	private String mSource = null;
	
	/**
	 * @return Name of the source.
	 */
	public String getSourceName() {
		return (mSource == null) ? "" : mSource;
	}
	
	/**
	 * @return TRUE if contains name of the source.
	 */
	public boolean hasSourceName() {
		return (mSource != null);
	}
	
}
