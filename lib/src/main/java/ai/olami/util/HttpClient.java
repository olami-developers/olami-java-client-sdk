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

package ai.olami.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;

public class HttpClient {

	private final HttpURLConnection mHttpConnection;
	private OutputStream mOutStream = null;
	
	/**
	 * HTTP connection utility.
	 * 
	 * @param httpConnection - HttpURLConnection target.
	 */
	public HttpClient(final HttpURLConnection httpConnection) {
		
		if (httpConnection == null) {
			throw new IllegalArgumentException("HttpURLConnection is null.");
		}
		
		mHttpConnection = httpConnection;
		
	}
	
	/**
	 * Connect without stream data output.
	 * 
	 * @throws IOException HTTP connection failed.
	 */
	public void normalConnect() throws IOException {
		mHttpConnection.connect();
	}

	/**
	 * Connect with stream data output and send queries by a string.
	 * 
	 * @param queryParams - A string that contains query parameters.
	 * @throws IOException HTTP connection failed.
	 */
	public void postQueryConnect(final String queryParams) throws IOException {
		
		mHttpConnection.setDoOutput(true);
		
		mOutStream = mHttpConnection.getOutputStream();
		if (!queryParams.startsWith("&")) {
			mOutStream.write("&".getBytes("utf-8"));
		}
		mOutStream.write(queryParams.getBytes("utf-8"));
		mOutStream.flush();
		mOutStream.close();
		
	}
	
	/**
	 * Connect with stream data output and send queries.
	 * 
	 * @param queryParams - Key-Value query parameters.
	 * @throws IOException HTTP connection failed.
	 */
	public void postQueryConnect(Map<String, String> queryParams) 
			throws IOException {
		
		StringBuffer queryParamsStringBuffer = new StringBuffer();
		if (queryParams != null) {
			for (Object key : queryParams.keySet()) {
				queryParamsStringBuffer.append("&");
				queryParamsStringBuffer.append(key.toString());
				queryParamsStringBuffer.append("=");
				queryParamsStringBuffer.append(queryParams.get(key));
			}
		}
		
		postQueryConnect(queryParamsStringBuffer.toString());
		
	}
	
	/**
	 * Connect and send data by "Content-Type: application/octet-stream".
	 * 
	 * @param streamBuffer - The buffer that contains data you want to send.
	 * @param writeBytes - How many bytes you want to send in this buffer.
	 * @throws IOException HTTP connection failed.
	 */
	public void octetStreamConnect(
			final byte[] streamBuffer,
			final int writeBytes
	) throws IOException {

		mHttpConnection.setDoOutput(true);
		mHttpConnection.setRequestProperty("Connection", "Keep-Alive");
		mHttpConnection.setRequestProperty("Content-Type", "application/octet-stream");
		
		mOutStream = mHttpConnection.getOutputStream();
		mOutStream.write(streamBuffer, 0, writeBytes);
		mOutStream.flush();
		mOutStream.close();
		
	}
	
	/**
	 * Get the response content after the connection.
	 * 
	 * @return Response content.
	 * @throws IOException - HTTP connection failed.
	 */
	public String getResponseContent() throws IOException {
		
		BufferedReader inputReader = null;
		StringBuffer inputStringBuffer = new StringBuffer();
		
		try {
			inputReader = new BufferedReader(new InputStreamReader(mHttpConnection.getInputStream()));
			String inputLine;
			while ((inputLine = inputReader.readLine()) != null) {
				inputStringBuffer.append(inputLine);
			}
			inputReader.close();
		} finally {
			if (inputReader != null) {
				inputReader.close();
			}
		}
		
		return inputStringBuffer.toString();
		
	}

	/**
	 * Get the response code after the connection.
	 * 
	 * @return Response Code.
	 * @throws IOException - HTTP connection failed.
	 */
	public int getResponseCode() throws IOException {
		return mHttpConnection.getResponseCode();
	}
	
	/**
	 * Get the response message after the connection.
	 * 
	 * @return Response message.
	 * @throws IOException - HTTP connection failed.
	 */
	public String getResponseMessage() throws IOException {
		return mHttpConnection.getResponseMessage();
	}
	
	/**
	 * Get the Cookies after the connection.
	 * 
	 * @return Cookies.
	 */
	public List<String> getCookies() {
		return mHttpConnection.getHeaderFields().get("Set-Cookie");
	}
	
	/**
	 * Close connection and all possible resources.
	 * 
	 * @throws IOException - HTTP connection failed.
	 */
	public void close() throws IOException {
		
		if (mOutStream != null) {
			mOutStream.close();
		}
		
		if (mHttpConnection != null) {
			mHttpConnection.disconnect();
		}
		
	}
	
}
