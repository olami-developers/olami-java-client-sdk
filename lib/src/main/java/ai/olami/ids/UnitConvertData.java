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

public class UnitConvertData {

	@Expose
	@SerializedName("content")
	private String mContent = null;
	
	/**
	 * @return Reply content.
	 */
	public String getContent() {
		return (mContent == null) ? "" : mContent;
	}
	
	/**
	 * @return TRUE if contains reply content information.
	 */
	public boolean hasContent() {
		return (mContent != null);
	}
	
	@Expose
	@SerializedName("src_value")
	private String mSourceValue = null;
	
	/**
	 * @return Source value.
	 */
	public String getSourceValue() {
		return (mSourceValue == null) ? "" : mSourceValue;
	}
	
	/**
	 * @return TRUE if contains source value.
	 */
	public boolean hasSourceValue() {
		return (mSourceValue != null);
	}
	
	@Expose
	@SerializedName("src_unit")
	private String mSourceUnit = null;
	
	/**
	 * @return Source unit.
	 */
	public String getSourceUnit() {
		return (mSourceUnit == null) ? "" : mSourceUnit;
	}
	
	/**
	 * @return TRUE if contains source unit.
	 */
	public boolean hasSourceUnit() {
		return (mSourceUnit != null);
	}
	
	@Expose
	@SerializedName("dst_value")
	private String mDestinationValue = null;
	
	/**
	 * @return Destination value.
	 */
	public String getDestinationValue() {
		return (mDestinationValue == null) ? "" : mDestinationValue;
	}
	
	/**
	 * @return TRUE if contains destination value.
	 */
	public boolean hasDestinationValue() {
		return (mDestinationValue != null);
	}
	
	@Expose
	@SerializedName("dst_unit")
	private String mDestinationUnit = null;
	
	/**
	 * @return Destination unit.
	 */
	public String getDestinationUnit() {
		return (mDestinationUnit == null) ? "" : mDestinationUnit;
	}
	
	/**
	 * @return TRUE if contains destination unit.
	 */
	public boolean hasDestinationUnit() {
		return (mDestinationUnit != null);
	}
	
}
