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

import ai.olami.util.GsonFactory;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class APIResponse {
	
	public static final String STATUS_OK = "ok";
	public static final String STATUS_ERROR = "error";
	
	public static final int ERROR_CODE_INVALID_CONTENT = -1000000;
	
	@Expose(serialize = false, deserialize = false)
	@SerializedName("original_json_string_for_debug")
	private String mSourceJsonString = null;
	
	@Expose(serialize = false, deserialize = false)
	private Gson mGson = GsonFactory.getNormalGson();
	
	@Expose
	@SerializedName("status")
	private String mStatus = STATUS_OK;
	
	@Expose
	@SerializedName("code")
	private int mCode;
	
	@Expose
	@SerializedName("msg")
	private String mMessage;
	
	@Expose
	@SerializedName("data")
	private APIResponseData mResponseData = null;	
	
	@Override
	public String toString() {
		
		if (mSourceJsonString == null) {
			return mGson.toJson(this);
		} else {
			return mSourceJsonString;
		}
		
	}
	
	/**
	 * Set the original JSON string.
	 * This is helpful for debugging or to get other undefined members of this JSON string.
	 * Use toString() method to get the original JSON string.
	 * 
	 * @param jsonString - The original JSON string.
	 */
	public void setJsonStringSource(String jsonString) {
		mSourceJsonString = jsonString;
	}
	
	/**
	 * Set as a invalid response.
	 * 
	 * @param message - Error message to explain why this is an invalid result.
	 */
	public void setInvalid(String message) {
		setError(ERROR_CODE_INVALID_CONTENT, message);
	}
	
	/**
	 * Set as a error response.
	 * 
	 * @param errorCode - The error code.
	 * @param errorMessage - Error message.
	 */
	public void setError(int errorCode, String errorMessage) {
		setStatus(STATUS_ERROR);
		setErrorCode(errorCode);
		setErrorMessage(errorMessage);
	}
	
	/**
	 * Check whether the response status is OK (= STATUS_OK) or not.
	 * 
	 * @return TRUE if the response status is OK.
	 */
	public boolean ok() {
		return getStatus().equals(STATUS_OK);
	}
	
	/**
	 * Check whether the response status is OK (= STATUS_OK) or not.
	 * 
	 * @return TRUE if something wrong, or FALSE if the response status is OK.
	 */
	public boolean hasError() {
		return (!ok());
	}

	/**
	 * @return Status
	 */
	public String getStatus() {
		return mStatus;
	}

	/**
	 * Set status value.
	 * 
	 * @param status - Status value string.
	 */
	public void setStatus(String status) {
		mStatus = status;
	}

	/**
	 * @return Error code.
	 */
	public int getErrorCode() {
		return mCode;
	}

	/**
	 * Set error code.
	 * 
	 * @param errorEode - Error code.
	 */
	public void setErrorCode(int errorEode) {
		mCode = errorEode;
	}

	/**
	 * @return Error message.
	 */
	public String getErrorMessage() {
		return mMessage;
	}

	/**
	 * Set error message.
	 * 
	 * @param errorMessage - Error message.
	 */
	public void setErrorMessage(String errorMessage) {
		mMessage = errorMessage;
	}

	/**
	 * Get the response data. 
	 * Map to the member "data" of the JSON string.
	 * 
	 * @return The response data object.
	 */
	public APIResponseData getData() {
		return mResponseData;
	}
	
	/**
	 * @return TRUE if contains Data information.
	 */
	public boolean hasData() {
		return (mResponseData != null);
	}
	
}
