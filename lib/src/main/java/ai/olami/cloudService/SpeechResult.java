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

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SpeechResult {
	
	public static final int STATUS_RECOGNIZE_OK = 0;
	public static final int STATUS_RESULT_NOT_CHANGE = 1;
	
	public static final int SPEECH_STATUS_GOOD_QUALITY = 0;
	public static final int SPEECH_STATUS_WITH_NOISE = 1;

	@Expose
	@SerializedName("result")
	private String mResult = null;
	
	@Expose
	@SerializedName("speech_status")
	private int mSpeechStatus = -1;
	
	@Expose
	@SerializedName("final")
	private boolean mIsFinalResult = false;
	
	@Expose
	@SerializedName("status")
	private int mStatus = -1;
	
	/**
	 * Get Speech-To-Text recognition result.
	 * 
	 * @return Text result.
	 */
	public String getResult() {
		return (mResult == null) ? "" : mResult;
	}
	
	/**
	 * Get quality and status of the speech source.
	 * 
	 * @return 0 for good, or others for noise and issues.
	 */
	public int getSpeechStatus() {
		return mSpeechStatus;
	}
	
	/**
	 * Check whether the recognition is completed.
	 * 
	 * @return TRUE if recognition is completed, or FALSE means this is not final result. 
	 */
	public boolean complete() {
		return mIsFinalResult;
	}

	/**
	 * Get the processing status of audio uploading or recognition. 
	 * 
	 * @return 0 for the processing is fine, or others for something wrong.
	 */
	public int getStatus() {
		return mStatus;
	}
	
}
