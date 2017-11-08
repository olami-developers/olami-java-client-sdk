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

public class WeatherData {

	@Expose
	@SerializedName("city")
	private String mCity = null;
		
	/**
	 * @return City name.
	 */
	public String getCity() {
		return (mCity == null) ? "" : mCity;
	}
	
	/**
	 * @return TRUE if contains city name.
	 */
	public boolean hasCity() {
		return ((mCity != null) && (!mCity.equals("")));
	}
	
	@Expose
	@SerializedName("real_date")
	private long mRealDate = -1;
	
	/**
	 * @return Date since 1970.
	 */
	public long getRealDate() {
		return mRealDate;
	}
	
	@Expose
	@SerializedName("date")
	private int mDate = -1;
	
	/**
	 * Date description.
	 * 
	 * @return 0 means the day, 1 means tomorrow, and so on.
	 */
	public int getDate() {
		return mDate;
	}
	
	@Expose
	@SerializedName("weather_start")
	private int mWeatherStart = -1;	
	
	/**
	 * Get the weather type code for the morning.
	 * 
	 * @return Weather type code.
	 */
	public int getWeatherStart() {
		return mWeatherStart;
	}
	
	@Expose
	@SerializedName("weather_end")
	private int mWeatherEnd = -1;	
	
	/**
	 * Get the weather type code for the evening.
	 * 
	 * @return Weather type code.
	 */
	public int getWeatherEnd() {
		return mWeatherEnd;
	}

	@Expose
	@SerializedName("wind")
	private String mWind = null;
		
	/**
	 * @return Wind direction.
	 */
	public String getWind() {
		return (mWind == null) ? "" : mWind;
	}
	
	/**
	 * @return TRUE if contains wind direction.
	 */
	public boolean hasWind() {
		return ((mWind != null) && (!mWind.equals("")));
	}

	@Expose
	@SerializedName("temperature_high")
	private String mTemperatureHigh = null;
		
	/**
	 * @return Maximum temperature.
	 */
	public String getMaxTemperature() {
		return (mTemperatureHigh == null) ? "" : mTemperatureHigh;
	}
	
	/**
	 * @return TRUE if contains maximum temperature.
	 */
	public boolean hasMaxTemperature() {
		return ((mTemperatureHigh != null) && (!mTemperatureHigh.equals("")));
	}

	@Expose
	@SerializedName("temperature_low")
	private String mTemperatureLow = null;
		
	/**
	 * @return Minimum temperature.
	 */
	public String getMinTemperature() {
		return (mTemperatureLow == null) ? "" : mTemperatureLow;
	}
	
	/**
	 * @return TRUE if contains minimum temperature.
	 */
	public boolean hasMinTemperature() {
		return ((mTemperatureLow != null) && (!mTemperatureLow.equals("")));
	}

	@Expose
	@SerializedName("description")
	private String mDescription = null;
		
	/**
	 * @return Description.
	 */
	public String getDescription() {
		return (mDescription == null) ? "" : mDescription;
	}
	
	/**
	 * @return TRUE if contains description.
	 */
	public boolean hasDescription() {
		return ((mDescription != null) && (!mDescription.equals("")));
	}

	@Expose
	@SerializedName("exponent_type")
	private String[] mExponentType = null;
		
	/**
	 * @return Exponent type.
	 */
	public String[] getExponentType() {
		return (mExponentType == null) ? new String[]{""} : mExponentType;
	}
	
	/**
	 * @return TRUE if contains exponent type.
	 */
	public boolean hasExponentType() {
		return ((mExponentType != null) && (mExponentType.length > 0));
	}

	@Expose
	@SerializedName("exponent_value")
	private String[] mExponentValue = null;
		
	/**
	 * @return Exponent value.
	 */
	public String[] getExponentValue() {
		return (mExponentValue == null) ? new String[]{""} : mExponentValue;
	}
	
	/**
	 * @return TRUE if contains exponent value.
	 */
	public boolean hasExponentValue() {
		return ((mExponentValue != null) && (mExponentValue.length > 0));
	}

	@Expose
	@SerializedName("is_querying")
	private int mIsQuering = 0;	
		
	/**
	 * @return TRUE if this data is for the main query target.
	 */
	public boolean isQueryTarget() {
		return (mIsQuering == 1);
	}

	@Expose
	@SerializedName("pm25")
	private int mPM25 = -1;	

	/**
	 * @return PM 2.5.
	 */
	public int getPM25() {
		return mPM25;
	}
	
}
