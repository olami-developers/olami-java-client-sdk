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

public class PoemData {

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
		return ((mTitle != null) && (!mTitle.equals("")));
	}
	
	@Expose
	@SerializedName("author")
	private String mAuthor = null;
	
	/**
	 * @return Author.
	 */
	public String getAuthor() {
		return (mAuthor == null) ? "" : mAuthor;
	}
	
	/**
	 * @return TRUE if contains author information.
	 */
	public boolean hasAuthor() {
		return ((mAuthor != null) && (!mAuthor.equals("")));
	}
	
	@Expose
	@SerializedName("content")
	private String mContent = null;
	
	/**
	 * @return Content.
	 */
	public String getContent() {
		return (mContent == null) ? "" : mContent;
	}
	
	/**
	 * @return TRUE if contains content.
	 */
	public boolean hasContent() {
		return ((mContent != null) && (!mContent.equals("")));
	}
	
	@Expose
	@SerializedName("poem_name")
	private String mPoemName = null;
	
	/**
	 * @return Poem name.
	 */
	public String getPoemName() {
		return (mPoemName == null) ? "" : mPoemName;
	}
	
	/**
	 * @return TRUE if contains poem name.
	 */
	public boolean hasPoemName() {
		return ((mPoemName != null) && (!mPoemName.equals("")));
	}
	
}
