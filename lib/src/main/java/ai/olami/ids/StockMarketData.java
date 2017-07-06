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

public class StockMarketData {

	@Expose
	@SerializedName("is_history")
	private int mIsHistory = 0;

	/**
	 * @return TRUE if this is history data.
	 */
	public boolean isHistory() {
		return (mIsHistory == 1);
	}
	
	@Expose
	@SerializedName("id")
	private String mID = null;
	
	/**
	 * @return Stock ID.
	 */
	public String getID() {
		return (mID == null) ? "" : mID;
	}
	
	/**
	 * @return TRUE if contains stock ID.
	 */
	public boolean hasID() {
		return (mID != null);
	}
	
	@Expose
	@SerializedName("name")
	private String mName = null;
	
	/**
	 * @return Stock name.
	 */
	public String getName() {
		return (mName == null) ? "" : mName;
	}
	
	/**
	 * @return TRUE if contains stock name.
	 */
	public boolean hasName() {
		return (mName != null);
	}
	
	@Expose
	@SerializedName("cur_price")
	private String mCurrentPrice = null;
	
	/**
	 * @return Current price.
	 */
	public String getCurrentPrice() {
		return (mCurrentPrice == null) ? "" : mCurrentPrice;
	}
	
	/**
	 * @return TRUE if contains current price information.
	 */
	public boolean hasCurrentPrice() {
		return (mCurrentPrice != null);
	}
	
	@Expose
	@SerializedName("price_start")
	private String mOpeningPrice = null;
	
	/**
	 * @return Opening price.
	 */
	public String getOpeningPrice() {
		return (mOpeningPrice == null) ? "" : mOpeningPrice;
	}
	
	/**
	 * @return TRUE if contains opening price information.
	 */
	public boolean hasOpeningPrice() {
		return (mOpeningPrice != null);
	}
	
	@Expose
	@SerializedName("price_end")
	private String mClosingPrice = null;
	
	/**
	 * @return Closing price.
	 */
	public String getClosingPrice() {
		return (mClosingPrice == null) ? "" : mClosingPrice;
	}
	
	/**
	 * @return TRUE if contains closing price information.
	 */
	public boolean hasClosingPrice() {
		return (mClosingPrice != null);
	}
	
	@Expose
	@SerializedName("price_high")
	private String mHighestPrice = null;
	
	/**
	 * @return The highest price.
	 */
	public String getHighestPrice() {
		return (mHighestPrice == null) ? "" : mHighestPrice;
	}
	
	/**
	 * @return TRUE if contains the highest price information.
	 */
	public boolean hasHighestPrice() {
		return (mHighestPrice != null);
	}
	
	@Expose
	@SerializedName("price_low")
	private String mLowestPrice = null;
	
	/**
	 * @return The lowest price.
	 */
	public String getLowestPrice() {
		return (mLowestPrice == null) ? "" : mLowestPrice;
	}
	
	/**
	 * @return TRUE if contains the lowest price information.
	 */
	public boolean hasLowestPrice() {
		return (mLowestPrice != null);
	}
	
	@Expose
	@SerializedName("change_rate")
	private String mChangeRate = null;
	
	/**
	 * @return The change rate.
	 */
	public String getChangeRate() {
		return (mChangeRate == null) ? "" : mChangeRate;
	}
	
	/**
	 * @return TRUE if contains the change rate information.
	 */
	public boolean hasChangeRate() {
		return (mChangeRate != null);
	}
	
	@Expose
	@SerializedName("change_amount")
	private String mChangeAmount = null;
	
	/**
	 * @return The change amount.
	 */
	public String getChangeAmount() {
		return (mChangeAmount == null) ? "" : mChangeAmount;
	}
	
	/**
	 * @return TRUE if contains the change amount information.
	 */
	public boolean hasChangeAmount() {
		return (mChangeAmount != null);
	}
	
	@Expose
	@SerializedName("volume")
	private String mVolume = null;
	
	/**
	 * @return The volume.
	 */
	public String getVolume() {
		return (mVolume == null) ? "" : mVolume;
	}
	
	/**
	 * @return TRUE if contains the volume information.
	 */
	public boolean hasVolume() {
		return (mVolume != null);
	}
	
	@Expose
	@SerializedName("amount")
	private String mAmount = null;
	
	/**
	 * @return Amount.
	 */
	public String getAmount() {
		return (mAmount == null) ? "" : mAmount;
	}
	
	/**
	 * @return TRUE if contains amount information.
	 */
	public boolean hasAmount() {
		return (mAmount != null);
	}
	
	@Expose
	@SerializedName("intent")
	private String mIntent = null;
	
	/**
	 * @return Intent information.
	 */
	public String getIntentInfo() {
		return (mIntent == null) ? "" : mIntent;
	}
	
	/**
	 * @return TRUE if contains intent information.
	 */
	public boolean hasIntentInfo() {
		return (mIntent != null);
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
	@SerializedName("favorite")
	private String mFavorite = "";
	
	/**
	 * @return TRUE if it is in the favorites list.
	 */
	public boolean isFavorite() {
		return mFavorite.equals("1");
	}
	
}
