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

public class KKBOXData {

	@Expose
	@SerializedName("id")
	private String mID = null;
	
	/**
	 * @return ID.
	 */
	public String getID() {
		return (mID == null) ? "" : mID;
	}
	
	/**
	 * @return TRUE if contains ID.
	 */
	public boolean hasID() {
		return ((mID != null) && (!mID.equals("")));
	}
	
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
	@SerializedName("artist")
	private String mArtist = null;
	
	/**
	 * @return Artist.
	 */
	public String getArtist() {
		return (mArtist == null) ? "" : mArtist;
	}
	
	/**
	 * @return TRUE if contains artist.
	 */
	public boolean hasArtist() {
		return ((mArtist != null) && (!mArtist.equals("")));
	}
	
	@Expose
	@SerializedName("artistId")
	private String mArtistID = null;
	
	/**
	 * @return Artist ID.
	 */
	public String getArtistID() {
		return (mArtistID == null) ? "" : mArtistID;
	}
	
	/**
	 * @return TRUE if contains artist ID.
	 */
	public boolean hasArtistID() {
		return ((mArtistID != null) && (!mArtistID.equals("")));
	}
	
	@Expose
	@SerializedName("album")
	private String mAlbum = null;
	
	/**
	 * @return Album.
	 */
	public String getAlbum() {
		return (mAlbum == null) ? "" : mAlbum;
	}
	
	/**
	 * @return TRUE if contains album.
	 */
	public boolean hasAlbum() {
		return ((mAlbum != null) && (!mAlbum.equals("")));
	}
	
	@Expose
	@SerializedName("albumId")
	private String mAlbumID = null;
	
	/**
	 * @return Album ID.
	 */
	public String getAlbumID() {
		return (mAlbumID == null) ? "" : mAlbumID;
	}
	
	/**
	 * @return TRUE if contains album ID.
	 */
	public boolean hasAlbumID() {
		return ((mAlbumID != null) && (!mAlbumID.equals("")));
	}
	
	@Expose
	@SerializedName("time")
	private String mDurationTime = null;
	
	/**
	 * @return Duration of time in milliseconds.
	 */
	public long getDuration() {
		return (hasDuration() ? Long.parseLong(mDurationTime) : 0);
	}
	
	/**
	 * @return TRUE if contains duration.
	 */
	public boolean hasDuration() {
		return ((mDurationTime != null) && (!mDurationTime.equals("")));
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
	
	@Expose
	@SerializedName("photo")
	private KKBOXDataPhoto[] mKKBOXDataPhotos = null;
	
	/**
	 * @return Photo information array.
	 */
	public KKBOXDataPhoto[] getPhotos() {
		return mKKBOXDataPhotos;
	}
	
	/**
	 * @return TRUE if contains photo information.
	 */
	public boolean hasPhotos() {
		return ((mKKBOXDataPhotos != null) && (mKKBOXDataPhotos.length > 0));
	}
	
}
