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

import java.util.UUID;

public abstract class APIRequestBase {
	
	private APIConfiguration mConfiguration = null;
	private String mCusId = "Undefined";
	private int mConnectionTimeoutMilliseconds = 5000;

	/**
	 * A base class to issue OLAMI HTTP API requests.
	 * 
	 * @param configuration - API configurations.
	 */
	public APIRequestBase(APIConfiguration configuration) {
		mConfiguration = configuration;
		mCusId = UUID.randomUUID().toString();
	}
	
	/**
	 * @param configuration - API configurations.
	 */
	public void setConfiguration(APIConfiguration configuration) {
		mConfiguration = configuration;
	}
	
	/**
	 * @return The given API configurations
	 */
	public APIConfiguration getConfiguration() {
		return mConfiguration;
	}
	
	/**
	 * Set the identification to identify the End-user.
	 * This is helpful in some of NLU/NLI functions, such as context support.
	 * 
	 * @param cusId - End-user identifier.
	 */
	public void setEndUserIdentifier(final String cusId) {
		mCusId = cusId;
	}
	
	/**
	 * @return The given End-user identifier.
	 */
	public String getEndUserIdentifier() {
		return mCusId;
	}
	
	/**
	 * Get the timeout setting of the HTTP API request.
	 * 
	 * @return milliseconds.
	 */
	public int getTimeout() {
		return mConnectionTimeoutMilliseconds;
	}

	/**
	 * Set timeout in milliseconds to the HTTP API request.
	 * 
	 * @param milliseconds - Timeout in milliseconds.
	 */
	public void setTimeout(int milliseconds) {
		mConnectionTimeoutMilliseconds = milliseconds;
	}
	
}
