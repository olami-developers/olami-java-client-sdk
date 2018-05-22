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

public class MusicControlData {
	
	public static final String NEXT = "next";
	public static final String PREVIOUS = "prev";
	public static final String PAUSE = "pause";
	public static final String PLAY = "play";
	public static final String RANDOM = "random";
	public static final String LOOP = "loop";
	public static final String ORDER = "order";
	public static final String MUTE = "mute";
	public static final String VOLUME_DOWN = "volume_down";
	public static final String VOLUME_UP = "volume_up";

	@Expose
	@SerializedName("index")
	private String mIndex = null;
	
	/**
	 * @return Index.
	 */
	public String getIndex() {
		return (mIndex == null) ? "" : mIndex;
	}
	
	/**
	 * @return TRUE if contains Index.
	 */
	public boolean hasIndex() {
		return ((mIndex != null) && (!mIndex.equals("")));
	}
	
	@Expose
	@SerializedName("command")
	private String mCommand = null;
	
	/**
	 * @return Command.
	 */
	public String getCommand() {
		return (mCommand == null) ? "" : mCommand;
	}
	
	/**
	 * @return TRUE if contains command.
	 */
	public boolean hasCommand() {
		return ((mCommand != null) && (!mCommand.equals("")));
	}
	
}
