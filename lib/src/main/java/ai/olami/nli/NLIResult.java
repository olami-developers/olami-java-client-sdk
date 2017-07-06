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

package ai.olami.nli;

import java.util.ArrayList;

import ai.olami.ids.IDSResult;
import ai.olami.util.GsonFactory;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

public class NLIResult {
	
	private Gson mGson = GsonFactory.getNormalGson();

	@Expose
	@SerializedName("desc_obj")
	private DescObject mDescObject = null;

	/**
	 * @return Description of the analysis results.
	 */
	public DescObject getDescObject() {
		return mDescObject;
	}
	
	/**
	 * @return TRUE if contains description information.
	 */
	public boolean hasDescObject() {
		return (mDescObject != null);
	}
	
	@Expose
	@SerializedName("semantic")
	private Semantic[] mSemantics = null;
	
	/**
	 * Get the semantics of input text.
	 * 
	 * @return Semantic array
	 */
	public Semantic[] getSemantics() {
		return mSemantics;
	}
	
	/**
	 * @return TRUE if contains semantics information.
	 */
	public boolean hasSemantics() {
		return (mSemantics != null);
	}
	
	@Expose
	@SerializedName("type")
	private String mType = null;
	
	/**
	 * @return Type information.
	 */
	public String getType() {
		return mType;
	}
	
	/**
	 * @return TRUE if contains type information.
	 */
	public boolean hasType() {
		return (mType != null);
	}
	
	/**
	 * Check if this result is actually a IDS response.
	 * 
	 * @return TRUE if this result is provided by the IDS.
	 */
	public boolean isFromIDS() {
		return (IDSResult.Types.contains(mType));
	}	

	@Expose
	@SerializedName("data_obj")
	private JsonElement mDataObjs = null;
	
	/**
	 * @return List of data object
	 *         or list of JsonElement if the object type is not defined.
	 */
	public <T> ArrayList<T> getDataObjects() {	
		if (isFromIDS()) {
			String typeName = mType;
			switch (IDSResult.Types.getByName(mType)) {
				case QUESTION:
					typeName = this.getDescObject().getType();
					break;
				case CONFIRMATION:
					typeName = this.getDescObject().getType();
					break;
				case SELECTION:
					typeName = this.getDescObject().getType();
					break;
				default:
					break;
			}
			return mGson.fromJson(mDataObjs, 
					IDSResult.Types.getDataArrayListType(typeName));
		} else if (hasDataObjects()) {
			return mGson.fromJson(mDataObjs, 
					new TypeToken<ArrayList<JsonElement>>() {}.getType());
		}
		return null;
	}
	
	/**
	 * @return TRUE if contains data contents.
	 */
	public boolean hasDataObjects() {
		return (mDataObjs != null);
	}
	
}
