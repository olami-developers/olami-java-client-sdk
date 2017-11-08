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

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class APIConfiguration {
	
	public static final int LOCALIZE_OPTION_SIMPLIFIED_CHINESE = 0;
	public static final int LOCALIZE_OPTION_TRADITIONAL_CHINESE = 1;
	
	public static final String API_NAME_SEG = "seg";
	public static final String API_NAME_NLI = "nli";
	public static final String API_NAME_ASR = "asr";
	
	protected static final String API_DOMAIN_NAME_CN = "https://cn.olami.ai";
	protected static final String API_DOMAIN_NAME_TW = "https://tw.olami.ai";
	protected static final String API_BASE_URL = "/cloudservice/api";
	
	private String mAppKey = null;
	private String mAppSecret = null;
	private String mApiDomainName = null;
	private String mSdkType = null;
	private int mLocalizeOption = -1;
	private Map<String, String> mSignatureMap;
	private Map<String, Long> mTimestampMap;
	
	private static final long TIMESTAMP_RANGE = (59 * 60 * 1000);
	
	/**
	 * Configure to issue OLAMI HTTP API requests.
	 * 
	 * @param appKey - The 'APP KEY' you have, provided by OLAMI developer service.  
	 * @param appSecret - The 'APP SECRET' you have, provided by OLAMI developer service.
	 * @param localizeOption - Select the location and language of the OLAMI service you want to use
	 *                         (0 for Simplified Chinese in China, 1 for Traditional Chinese in Taiwan)
	 */
	public APIConfiguration(
			final String appKey,
			final String appSecret,
			final int localizeOption
	) {
		
		if (appKey == null) {
			throw new IllegalArgumentException("appKey is required!");
		}
		
		if (appSecret == null) {
			throw new IllegalArgumentException("appSecret is required!");
		}
		
		initLocalization(localizeOption);
		
		mSdkType = "java";
		mAppKey = appKey;
		mAppSecret = appSecret;
		mTimestampMap = new HashMap<String, Long>();
		mSignatureMap = new HashMap<String, String>();
		
	}
	
	/**
	 * @param type - SDK type.
	 */
	public void setSdkType(String type) {
		mSdkType = type.toLowerCase();
	}
	
	/**
	 * Not recommended for use. 
	 * Normally this is for commercial use, so you probably don't need to use this method.
	 * 
	 * @param domain - API server domain name
	 */
	public void setApiServerDomain(String domain) {
		mApiDomainName = domain;
	}
	
	/**
	 * @return The given SDK type string.
	 */
	public String getSdkType() {
		return mSdkType;
	}

	/**
	 * @return The given 'APP KEY'.
	 */
	public String getAppKey() {
		return mAppKey;
	}

	/**
	 * @return The given 'APP SECRET'.
	 */
	public String getAppSecret() {
		return mAppSecret;
	}

	/**
	 * @return The localize option you selected.
	 */
	public int getLocalizeOption() {
		return mLocalizeOption;
	}
	
	/**
	 * Get the generated timestamp value by the given API name.
	 * 
	 * @param apiName - API name.
	 * @return Timestamp in milliseconds.
	 */
	public long getTimestamp(final String apiName) {
		return mTimestampMap.get(apiName);
	}	
	
	/**
	 * Get the signature by the given API name and the generated timestamp.
	 * 
	 * @param apiName - API name.
	 * @return MD5 signature.
	 */
	public String getSignature(final String apiName) {
		return mSignatureMap.get(apiName);
	}
	
	/**
	 * Get the base API end point URL for the given API name.
	 * 
	 * @param apiName - API name.
	 * @return URL of the specified API.
	 * @throws NoSuchAlgorithmException Filed to create signature.
	 */
	public String getBaseRequestURL(final String apiName) 
			throws NoSuchAlgorithmException {
		return getBaseRequestURL(apiName, null);
	}
	
	/**
	 * Get the base API end point URL for the given API name.
	 * 
	 * @param apiName - API name.
	 * @param queryParams - Query parameters by name-value collection.
	 * @return URL of the specified API.
	 * @throws NoSuchAlgorithmException Filed to create signature.
	 */
	public String getBaseRequestURL(
			final String apiName,
			final Map<String, String> queryParams
	) throws NoSuchAlgorithmException {

		generateSignature(apiName);
		
		StringBuffer urlStringBuffer = new StringBuffer();
		urlStringBuffer.append(mApiDomainName);
		urlStringBuffer.append(API_BASE_URL);
		urlStringBuffer.append("?_from=");
		urlStringBuffer.append(mSdkType);
		urlStringBuffer.append("&appkey=");
		urlStringBuffer.append(mAppKey);
		urlStringBuffer.append("&api=");
		urlStringBuffer.append(apiName);
		urlStringBuffer.append("&timestamp=");
		urlStringBuffer.append(getTimestamp(apiName));
		urlStringBuffer.append("&sign=");
		urlStringBuffer.append(getSignature(apiName));
		
		if (queryParams != null) {
			for (Object key : queryParams.keySet()) {
				urlStringBuffer.append("&");
				urlStringBuffer.append(key.toString());
				urlStringBuffer.append("=");
				urlStringBuffer.append(queryParams.get(key));
			}
		}
		
		return urlStringBuffer.toString();
		
	}
	
	protected void generateSignature(final String apiName) 
			throws NoSuchAlgorithmException {
	
		long current = System.currentTimeMillis();
		long expired = 0;
		
		if (!mTimestampMap.containsKey(apiName)) {
			mTimestampMap.put(apiName, current);
		}
		
		// Check the timestamp valid period.
		expired = mTimestampMap.get(apiName) + TIMESTAMP_RANGE;
		if ((current > expired) || (!mSignatureMap.containsKey(apiName))) {
			// Reset timestamp and signature when the old one has expired.
			mTimestampMap.put(apiName, current);
			
			// Prepare signature message.
			StringBuffer signStringBuffer = new StringBuffer();
			signStringBuffer.append(mAppSecret);
			signStringBuffer.append("api=");
			signStringBuffer.append(apiName);
			signStringBuffer.append("appkey=");
			signStringBuffer.append(mAppKey);
			signStringBuffer.append("timestamp=");
			signStringBuffer.append(mTimestampMap.get(apiName));
			signStringBuffer.append(mAppSecret);
			
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(signStringBuffer.toString().getBytes());
			StringBuffer signature = new StringBuffer();
			signature.append(new BigInteger(1, md.digest()).toString(16));
			if (signature.toString().length() < 32) {
				signature.insert(0, "0");
			}
			
			mSignatureMap.put(apiName, signature.toString());
		}
		
	}
	
	protected void initLocalization(final int localizeOption) {
		
		switch (localizeOption) {
			case LOCALIZE_OPTION_SIMPLIFIED_CHINESE:
				mApiDomainName = API_DOMAIN_NAME_CN;
				break;
			case LOCALIZE_OPTION_TRADITIONAL_CHINESE:
				mApiDomainName = API_DOMAIN_NAME_TW;
				break;
			default:
				throw new IllegalArgumentException("Illegal localizeation option.");
		}
		
		mLocalizeOption = localizeOption;
		
	}
	
}