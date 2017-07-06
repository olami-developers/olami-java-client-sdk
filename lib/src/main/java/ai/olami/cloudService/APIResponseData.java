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

import ai.olami.nli.NLIResult;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class APIResponseData {
	
	@Expose
	@SerializedName("seg")
	private String mSegResult = null;

	@Expose
	@SerializedName("nli")
	private NLIResult[] mNLIResults = null;
	
	@Expose
	@SerializedName("asr")
	private SpeechResult mSpeechResult = null;
	
	/**
	 * Get word segments from the word segmentation analyzer.
	 * 
	 * @return Word segments array.
	 */
	public String[] getWordSegmentation() {
		return mSegResult.split("\\s+");
	}
	
	/**
	 * @return TRUE if contains word segmentation information.
	 */
	public boolean hasWordSegmentation() {
		return (mSegResult != null);
	}
	
	/**
	 * Get word segmentation results from the Natural Language Understanding API.
	 * 
	 * @return Word segments string, separated by empty space.
	 */
	public String getWordSegmentationSingleString() {
		return mSegResult;
	}
	
	/**
	 * Get NLI result from the Natural Language Understanding API.
	 * 
	 * @return NLI result containers array. 
	 */
	public NLIResult[] getNLIResults() {
		return mNLIResults;
	}
	
	/**
	 * @return TRUE if contains NLIResult information.
	 */
	public boolean hasNLIResults() {
		return (mNLIResults != null);
	}
	
	/**
	 * Get the Speech-To-Text results from the Cloud Speech Recognition API.
	 * 
	 * @return Speech-To-Text container.
	 */
	public SpeechResult getSpeechResult() {
		return mSpeechResult;
	}
	
	/**
	 * @return TRUE if contains SpeechResult information.
	 */
	public boolean hasSpeechResult() {
		return (mSpeechResult != null);
	}
	
}
