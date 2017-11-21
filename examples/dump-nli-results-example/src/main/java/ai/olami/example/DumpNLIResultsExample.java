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

package ai.olami.example;

import ai.olami.nli.DescObject;
import ai.olami.nli.NLIResult;
import ai.olami.nli.Semantic;
import ai.olami.nli.slot.DateTime;
import ai.olami.nli.slot.DateTimeData;
import ai.olami.nli.slot.NumDetail;
import ai.olami.nli.slot.Slot;
import ai.olami.nli.slot.TimeStruct;

public class DumpNLIResultsExample {
	
	public static void dumpNLIResults(NLIResult[] nliResults) {
		if (nliResults == null) {
			System.out.println("* [ERROR] NLI Results cannot be null! *");
			return;
		}
		
		System.out.println("\n+-+-+-+- [Example to Dump NLI Results] -+-+-+-+");
		
		for (int i = 0; i < nliResults.length; i++) {
			System.out.format("\nNLIResult[%s] :\n", i);
			// * Get the result type information.
			System.out.format("|- Type: %s\n", nliResults[i].getType());			
			// * Get the conversation reply (answer).
			DescObject nliDescObj = nliResults[i].getDescObject();
			System.out.format("|- DescObject: \n");
			System.out.format("|\t- Type: %s\n", nliDescObj.getType());
			System.out.format("|\t- Reply: %s\n", nliDescObj.getReplyAnswer());
			System.out.format("|\t- Status: %s\n", nliDescObj.getStatus());
			
			// * Also check to see if it is automatically generated by the IDS.
			if (nliResults[i].hasType() && nliResults[i].isFromIDS()) {
				// * Now we know that the result is a auto-reply from IDS.
				// * So ... 1. Maybe we can get some IDS module information.
				// * And .. 2. we can also get some IDS content data.
				if (nliResults[i].hasDataObjects()) {
					System.out.format("|- DataObjects: \n");
					DumpIDSDataExample.dumpIDSData(nliResults[i]);
				}
			}
			
			// * Get semantics (if this is NOT a auto-reply from the IDS)
			if (nliResults[i].hasSemantics()) {
				Semantic[] semantic = nliResults[i].getSemantics();
				for (int x = 0; x < semantic.length; x++) {
					System.out.format("|- Semantic[%s] : \n", x);
					// * Get input text
					System.out.format("|\t- Input Text: %s\n",
							semantic[x].getInput());
					// * Get NLI module name
					System.out.format("|\t- NLI Module: %s\n",
							semantic[x].getAppModule());
					// * Get global modifier
					if (semantic[x].hasGlobalModifiers()) {
						String[] gm = semantic[x].getGlobalModifiers();
						for (int a = 0; a < gm.length; a++) {
							System.out.format("|\t- Global-Modifier[%s]: %s\n",
									a, gm[a]);
						}
					}
					
					// * Get slots
					if (semantic[x].hasSlots()) {
						Slot[] slots = semantic[x].getSlots();
						for (int y = 0; y < slots.length; y++) {
							System.out.format("|\t- Slot[%s]\n", y);
							// * Get slot name
							System.out.format("|\t\t- Name: %s\n",
									slots[y].getName());
							// * Get slot value
							System.out.format("|\t\t- Value: %s\n",
									slots[y].getValue());
							// * Get slot modifier
							if (slots[y].hasModifiers()) {
								String[] sm = slots[y].getModifiers();
								for (int a = 0; a < sm.length; a++) {
									System.out.format(
										"|\t\t- Solt-Modifier[%s]: %s\n",
										a, sm[a]);
								}
							}
							// * Get datetime
							if (slots[y].hasDateTime()) {
								// ai.olami.nli.slot.DateTime
								DateTime datetime = slots[y].getDateTime();
								System.out.format("|\t\t- datetime: \n");
								System.out.format("|\t\t   - type: %s\n",
										datetime.getType());
								// ai.olami.nli.slot.DateTimeData
								DateTimeData dtd = datetime.getData();
								System.out.format("|\t\t   - data:\n");
								System.out.format("|\t\t   \t- starttime: %s\n",
										dtd.getStartTime());
								System.out.format("|\t\t   \t- endtime: %s\n",
										dtd.getEndTime());
								if (dtd.hasSubType()) {
									System.out.format(
										"|\t\t   \t- sub_type: %s\n",
										dtd.getSubType());
								}
								if (dtd.hasTimeStruct()) {
									// ai.olami.nli.slot.TimeStruct
									TimeStruct ts = dtd.getTimeStruct();
									System.out.format(
											"|\t\t   \t- time_struct: \n");
									System.out.format(
											"|\t\t   \t   - Year: %s\n",
											ts.getYear());
									System.out.format(
											"|\t\t   \t   - Month: %s\n",
											ts.getMinute());
									System.out.format(
											"|\t\t   \t   - Week: %s\n",
											ts.getWeek());
									System.out.format(
											"|\t\t   \t   - Day: %s\n",
											ts.getDay());
									System.out.format(
											"|\t\t   \t   - Hour: %s\n",
											ts.getHour());
									System.out.format(
											"|\t\t   \t   - Minute: %s\n",
											ts.getMinute());
									System.out.format(
											"|\t\t   \t   - Second: %s\n",
											ts.getSecond());
									if (ts.hasRepeatRule()) {
										System.out.format(
												"|\t\t   \t   - repeat_rule: \n");
										System.out.format(
												"|\t\t   \t   \t- IntervalUnit: %s\n",
												ts.getRepeatRule().getIntervalUnit());
										System.out.format(
												"|\t\t   \t   \t- IntervalValue: %s\n",
												ts.getRepeatRule().getIntervalValue());
									}
									if (ts.hasExtendInfo()) {
										System.out.format(
												"|\t\t   \t   - extend_info: \n");
										System.out.format(
												"|\t\t   \t   \t- Month: %s\n",
												ts.getExtendInfo().getMonth());
										System.out.format( 
												"|\t\t   \t   \t- Day: %s\n",
												ts.getExtendInfo().getDay());
										System.out.format( 
												"|\t\t   \t   \t- Hour: %s\n",
												ts.getExtendInfo().getHour());
										System.out.format( 
												"|\t\t   \t   \t- Minute: %s\n",
												ts.getExtendInfo().getMinute());
										System.out.format( 
												"|\t\t   \t   \t- Second: %s\n",
												ts.getExtendInfo().getSecond());
										System.out.format( 
												"|\t\t   \t   \t- DayOfWeek: %s\n",
												ts.getExtendInfo().getDayOfWeek());
										System.out.format( 
												"|\t\t   \t   \t- festival: %s\n",
												ts.getExtendInfo().getFestival());
										System.out.format( 
												"|\t\t   \t   \t- jieqi: %s\n",
												ts.getExtendInfo().getJieqi());
									}
								}
							}
							// * Get num_detail
							if (slots[y].hasNumDetail()) {
								// ai.olami.nli.slot.NumDetail
								NumDetail nd = slots[y].getNumDetail();
								System.out.format("|\t\t- num_detail: \n");
								System.out.format(
										"|\t\t   - recommend_value: %s\n",
										nd.getRecommendValue());
								System.out.format(
										"|\t\t   - type: %s\n",
										nd.getType());
							}
						}	
					}
				}	
			}
		}
		
		System.out.println("\n\n++++++++++++++++++++++++++++++++++++++++++++\n");
	}
	
}
