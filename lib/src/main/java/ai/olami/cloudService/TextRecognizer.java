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
import ai.olami.util.HttpClient;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.NoSuchAlgorithmException;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class TextRecognizer extends APIRequestBase {
	
	public static final String RQ_DATA_TYPE_STT = "stt";
	
	public static final int RQ_DATA_INPUT_TYPE_FROM_SPEECH = 0;
	public static final int RQ_DATA_INPUT_TYPE_FROM_TEXT = 1;
	
	private int mRqDataInputType = RQ_DATA_INPUT_TYPE_FROM_SPEECH;
	private Gson mGson = GsonFactory.getNormalGson();
	
	/**
	 * Text Recognizer to issue Natural Language Understanding API requests.
	 * 
	 * @param configuration - API configurations.
	 */
	public TextRecognizer(APIConfiguration configuration) {
		super(configuration);
	}
	
	/**
	 * Set NLI data input type.
	 * 
	 * @param inputType 0 for speech source, or 1 for text source.
	 */
	public void setNLIDataInputType(int inputType) {
		mRqDataInputType = inputType;
	}
	
	/**
	 * Request word segmentation analyze service by specified input text.
	 * 
	 * @param text - The text to be analyzed.
	 * @return API response with analysis results.
	 * @throws NoSuchAlgorithmException Failed to create signature.
	 * @throws IOException HTTP connection failed, or other exceptions.
	 */
	public APIResponse requestWordSegmentation(String text) 
			throws NoSuchAlgorithmException, IOException {
		return sendRequest(APIConfiguration.API_NAME_SEG, text);
	}
	
	/**
	 * Request Natural Language Interaction service by specified input text.
	 * 
	 * @param text - The text to be recognized.
	 * @return API response with Natural Language Interaction results.
	 * @throws NoSuchAlgorithmException Failed to create signature.
	 * @throws IOException HTTP connection failed, or other exceptions.
	 */
	public APIResponse requestNLI(String text) 
			throws NoSuchAlgorithmException, IOException {
		return sendRequest(APIConfiguration.API_NAME_NLI, text);
	}
	
	private APIResponse sendRequest(
			String apiName,
			String text
	) throws NoSuchAlgorithmException, IOException {
		
		StringBuffer httpQueryStringBuffer = new StringBuffer();
		
		if (apiName == APIConfiguration.API_NAME_SEG) {
			httpQueryStringBuffer.append("rq=");
			httpQueryStringBuffer.append(text);
		} else if (apiName == APIConfiguration.API_NAME_NLI) {
			JsonObject data = new JsonObject();
			data.addProperty("input_type", mRqDataInputType);
			data.addProperty("text", text);
			JsonObject rq = new JsonObject();
			rq.addProperty("data_type", "stt");
			rq.add("data", data);
			httpQueryStringBuffer.append("rq=");
			httpQueryStringBuffer.append(mGson.toJson(rq));
		}
		
		final URL url = new URL(getConfiguration().getBaseRequestURL(apiName));
		final HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
		httpConnection.setRequestMethod("POST");
		httpConnection.setConnectTimeout(getTimeout());
		
		HttpClient httpClient = null;
		String response = null;
		
		try {			
			httpClient = new HttpClient(httpConnection);
			httpClient.postQueryConnect(httpQueryStringBuffer.toString());
			if(httpClient.getResponseCode() == HttpURLConnection.HTTP_OK) {
				response = httpClient.getResponseContent();
			} else {
				throw new IOException(httpClient.getResponseMessage());
			}
		} finally {
			httpClient.close();
		}
		
		return APIResponseBuilder.create(response);
	}
	
}
