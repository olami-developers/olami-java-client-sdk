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

import java.util.ArrayList;
import java.util.Map;

import ai.olami.ids.CookingData;
import ai.olami.ids.ExchangeRateData;
import ai.olami.ids.IDSResult;
import ai.olami.ids.JokeData;
import ai.olami.ids.MathData;
import ai.olami.ids.MusicControlData;
import ai.olami.ids.NewsData;
import ai.olami.ids.KKBOXData;
import ai.olami.ids.KKBOXDataPhoto;
import ai.olami.ids.OpenWebData;
import ai.olami.ids.PoemData;
import ai.olami.ids.StockMarketData;
import ai.olami.ids.TVProgramData;
import ai.olami.ids.UnitConvertData;
import ai.olami.ids.WeatherData;
import ai.olami.ids.BaikeData;
import ai.olami.nli.NLIResult;

public class DumpIDSDataExample {

	public static void dumpIDSData(NLIResult nliResult) {
		if (nliResult == null) {
			System.out.println("* [ERROR] NLI Result cannot be null! *");
			return;
		}
		
		System.out.println("|--[Example to Dump IDS Info & Data]----------- ");

		// ********************************************************************
		// * WEATHER EXAMPLE
		// *-------------------------------------------------------------------
		// * Simplified Chinese (China): 上海的天气怎么样
		// * Traditional Chinese (Taiwan): 台北的天氣怎麼樣
		// ********************************************************************
		// * Check to see if contains IDS weather data
		if (nliResult.getType().equals(IDSResult.Types.WEATHER.getName())) {
			// Get all of WeatherData into ArrayList.
			ArrayList<WeatherData> dataArray = nliResult.getDataObjects();
			for (int x = 0; x < dataArray.size(); x++) {
				System.out.format("|\t- IDS Weather Data[%s] %s :\n", x,
						(dataArray.get(x).isQueryTarget() ? "*TARGET*" : ""));
				System.out.format("|\t\t- Date: %s\n",
						dataArray.get(x).getDate());
				System.out.format("|\t\t- City: %s\n",
						dataArray.get(x).getCity());
				System.out.format("|\t\t- Weather Type: %s\n",
						dataArray.get(x).getWeatherEnd());
				System.out.format("|\t\t- Description: %s\n",
						dataArray.get(x).getDescription());
				System.out.format("|\t\t- Wind: %s\n",
						dataArray.get(x).getWind());
				System.out.format("|\t\t- Temperature: %s to %s\n",
						dataArray.get(x).getMinTemperature(),
						dataArray.get(x).getMaxTemperature());
			}
		}
		
		// ********************************************************************
		// * BAIKE EXAMPLE
		// *-------------------------------------------------------------------
		// * Simplified Chinese (China): 姚明的简介
		// * Traditional Chinese (Taiwan): 郭台銘的簡介
		// ********************************************************************
		// * Check to see if contains IDS baike data
		if (nliResult.getType().equals(IDSResult.Types.BAIKE.getName())) {
			// Get all of BaikeData into ArrayList.
			ArrayList<BaikeData> dataArray = nliResult.getDataObjects();
			for (int x = 0; x < dataArray.size(); x++) {
				System.out.format("|\t- IDS Baike Data[%s] :\n", x);
				System.out.format("|\t\t- Type: %s\n",
						dataArray.get(x).getType());
				System.out.format("|\t\t- Description: %s\n",
						dataArray.get(x).getDescription());
				System.out.format("|\t\t- Photo: %s\n",
						dataArray.get(x).getPhotoURL());
				// List categories
				if (dataArray.get(x).hasCategoryList()) {
					String[] cateArray = dataArray.get(x).getCategoryList();
					for (int i = 0; i < cateArray.length; i++) {
						System.out.format("|\t\t- Categories[%s]: %s\n",
								i, cateArray[i]);
					}	
				}
				// List detailed information
				int fieldSize = dataArray.get(x).getNumberOfFields();
				if (fieldSize > 0) {
					System.out.println("|\t\t- Fields: " + fieldSize);
					Map<String, String> fields = dataArray.get(x).getFieldNameValues();
					for (Map.Entry<String, String> entry : fields.entrySet()) {
						entry.getKey();
						System.out.format("|\t\t   - %s: %s\n", 
								entry.getKey(), entry.getValue());
					}
				}
			}
		}
		
		// ********************************************************************
		// * NEWS EXAMPLE
		// *-------------------------------------------------------------------
		// * Simplified Chinese (China): 今日新闻
		// * Traditional Chinese (Taiwan): 今日新聞
		// ********************************************************************
		// * Check 'the TYPE of nliResult' if contains IDS news data.
		// * And also check 'the TYPE OF DescObject' if it is a SELECTION mode.
		if (nliResult.getType().equals(IDSResult.Types.NEWS.getName())
				|| nliResult.getDescObject().getType().equals(IDSResult.Types.NEWS.getName())) {
			// Get all of NewsData into ArrayList.
			ArrayList<NewsData> dataArray = nliResult.getDataObjects();
			for (int x = 0; x < dataArray.size(); x++) {
				System.out.format("|\t- IDS News Data[%s] :\n", x);
				System.out.format("|\t\t- Title: %s\n",
						dataArray.get(x).getTitle());
				System.out.format("|\t\t- Time: %s\n",
						dataArray.get(x).getTime());
				System.out.format("|\t\t- URL: %s\n",
						dataArray.get(x).getSourceURL());
				System.out.format("|\t\t- Details: %s\n",
						dataArray.get(x).getDetail());
				System.out.format("|\t\t- Source: %s\n",
						dataArray.get(x).getSourceName());
				System.out.format("|\t\t- Image: %s\n",
						dataArray.get(x).getImageURL());
			}
		}
		
		// ********************************************************************
		// * KKBOX EXAMPLE
		// *-------------------------------------------------------------------
		// * Simplified Chinese (China): ** 简体中文服务地区尚不支持此模块 **
		// * Traditional Chinese (Taiwan): 我要聽音樂
		// ********************************************************************
		// * Check to see if contains IDS KKBOX data
		if (nliResult.getType().equals(IDSResult.Types.KKBOX.getName())) {
			// Get all of KKBOXData into ArrayList.
			ArrayList<KKBOXData> dataArray = nliResult.getDataObjects();
			for (int x = 0; x < dataArray.size(); x++) {
				System.out.format("|\t- IDS KKBOX Data[%s] :\n", x);
				System.out.format("|\t\t- ID: %s\n",
						dataArray.get(x).getID());
				System.out.format("|\t\t- Duration: %s\n",
						dataArray.get(x).getDuration());
				System.out.format("|\t\t- Title: %s\n",
						dataArray.get(x).getTitle());
				System.out.format("|\t\t- Artist: [ID=%s], %s\n",
						dataArray.get(x).getArtistID(), dataArray.get(x).getArtist());
				System.out.format("|\t\t- Album: [ID=%s], %s\n",
						dataArray.get(x).getAlbumID(), dataArray.get(x).getAlbum());
				System.out.format("|\t\t- URL: %s\n",
						dataArray.get(x).getURL());
				// List photos
				if (dataArray.get(x).hasPhotos()) {
					KKBOXDataPhoto[] photoArray = dataArray.get(x).getPhotos();
					for (int i = 0; i < photoArray.length; i++) {
						System.out.format("|\t\t- Photo[%s]:\n", i);
						System.out.format("|\t\t\t- Width:%s , Hight:%s\n", 
								photoArray[i].getWidth(), photoArray[i].getHeight());
						System.out.format("|\t\t\t- URL:%s\n",
								photoArray[i].getURL());
					}	
				}
			}
		}
		
		// ********************************************************************
		// * MUSIC CONTROL EXAMPLE
		// *-------------------------------------------------------------------
		// * Simplified Chinese (China): 下一首
		// * Traditional Chinese (Taiwan): 下一首
		// ********************************************************************
		// * Check to see if contains IDS MusicControl data
		if (nliResult.getType().equals(IDSResult.Types.MUSIC_CONTROL.getName())) {
			// Get all of MusicControlData into ArrayList.
			ArrayList<MusicControlData> dataArray = nliResult.getDataObjects();
			for (int x = 0; x < dataArray.size(); x++) {
				System.out.format("|\t- IDS MusicControl Data[%s] :\n", x);
				System.out.format("|\t\t- Index: %s\n",
						(dataArray.get(x).hasIndex() ? dataArray.get(x).getIndex() : "NO INDEX, [It means based on current/previous index] "));
				System.out.format("|\t\t- Command: %s \n",
						dataArray.get(x).getCommand());
				if (dataArray.get(x).getCommand().equals(MusicControlData.NEXT)) {
					System.out.println("|\t\t <播放下一首 / 播放下一首>");
				}
				if (dataArray.get(x).getCommand().equals(MusicControlData.PREVIOUS)) {
					System.out.println("|\t\t <播放上一首 / 播放上一首>");
				}
				if (dataArray.get(x).getCommand().equals(MusicControlData.PAUSE)) {
					System.out.println("|\t\t <暂停播放 / 暫停播放>");
				}
				if (dataArray.get(x).getCommand().equals(MusicControlData.PLAY)) {
					System.out.println("|\t\t <开始播放 / 開始播放>");
				}
				if (dataArray.get(x).getCommand().equals(MusicControlData.RANDOM)) {
					System.out.println("|\t\t <随机播放模式 / 隨機播放模式>");
				}
				if (dataArray.get(x).getCommand().equals(MusicControlData.LOOP)) {
					System.out.println("|\t\t <循环播放模式 / 循環播放模式>");
				}
				if (dataArray.get(x).getCommand().equals(MusicControlData.ORDER)) {
					System.out.println("|\t\t <顺序播放模式 / 順序播放模式>");
				}
				if (dataArray.get(x).getCommand().equals(MusicControlData.MUTE)) {
					System.out.println("|\t\t <设置为静音 / 設置為靜音>");
				}
				if (dataArray.get(x).getCommand().equals(MusicControlData.VOLUME_UP)) {
					System.out.println("|\t\t <音量增大 / 音量增大>");
				}
				if (dataArray.get(x).getCommand().equals(MusicControlData.VOLUME_DOWN)) {
					System.out.println("|\t\t <音量减小 / 音量減小>");
				}
			}
		}
		
		// ********************************************************************
		// * TV PROGRAM EXAMPLE
		// *-------------------------------------------------------------------
		// * Simplified Chinese (China): 今晚8点后的东方卫视节目
		// * Traditional Chinese (Taiwan): 今晚8點後的公視電視節目
		// ********************************************************************
		// * Check 'the TYPE of nliResult' if contains IDS TV program data.
		// * And also check 'the TYPE OF DescObject' if it is a QUESTION mode.
		if (nliResult.getType().equals(IDSResult.Types.TV_PROGRAM.getName())
				|| nliResult.getDescObject().getType().equals(IDSResult.Types.TV_PROGRAM.getName())) {
			
			// Extended fields for this module:
			System.out.format("|\t- /////////////////////////////////////\n");
			// Try to get URL from the DescObject.
			if (nliResult.getDescObject().hasURL()) {
				System.out.format("|\t- URL: %s\n",
						nliResult.getDescObject().getURL());	
			}
			System.out.format("|\t- /////////////////////////////////////\n");
			
			// Get all of TVProgramData into ArrayList.
			ArrayList<TVProgramData> dataArray = nliResult.getDataObjects();
			for (int x = 0; x < dataArray.size(); x++) {
				System.out.format("|\t- IDS TV Program Data[%s] %s :\n", x,
						(dataArray.get(x).isHighlight() ? "*TARGET*" : ""));
				System.out.format("|\t\t- Name: %s\n",
						dataArray.get(x).getName());
				System.out.format("|\t\t- Time: %s\n",
						dataArray.get(x).getTime());
			}
		}
		
		// ********************************************************************
		// * POEM EXAMPLE
		// *-------------------------------------------------------------------
		// * Simplified Chinese (China): 我要听长恨歌
		// * Traditional Chinese (Taiwan): 我要聽長恨歌
		// ********************************************************************
		// * Check 'the TYPE of nliResult' if contains IDS poem data.
		// * And also check 'the TYPE OF DescObject' if it is a SELECTION mode 
		// * or CONFIRMATION mode.
		if (nliResult.getType().equals(IDSResult.Types.POEM.getName())
				|| nliResult.getDescObject().getType().equals(IDSResult.Types.POEM.getName())) {
			// Get all of PoemData into ArrayList.
			ArrayList<PoemData> dataArray = nliResult.getDataObjects();
			for (int x = 0; x < dataArray.size(); x++) {
				System.out.format("|\t- IDS Poem Data[%s] :\n", x);
				System.out.format("|\t\t- Author: %s\n",
						dataArray.get(x).getAuthor());
				System.out.format("|\t\t- Name: %s\n",
						dataArray.get(x).getPoemName());
				System.out.format("|\t\t- Title: %s\n",
						dataArray.get(x).getTitle());
				System.out.format("|\t\t- Content: %s\n",
						dataArray.get(x).getContent());
			}
		}
		
		// ********************************************************************
		// * JOKE/STORY EXAMPLE
		// *-------------------------------------------------------------------
		// * Simplified Chinese (China) 1: 讲个笑话
		// * Simplified Chinese (China) 2: 讲个故事
		// * Traditional Chinese (Taiwan) 1: 講個笑話
		// * Traditional Chinese (Taiwan) 2: 講個故事
		// ********************************************************************
		// * Check 'the TYPE of nliResult' if contains IDS joke data.
		if (nliResult.getType().equals(IDSResult.Types.JOKE.getName())) {
			
			// Extended fields for this module:
			System.out.format("|\t- /////////////////////////////////////\n");
			// Try to get Joke/Story name from the DescObject.
			// And also check 'the TYPE OF DescObject' to get content type.
			if (nliResult.getDescObject().hasName()) {
				System.out.format("|\t- %s-name: %s\n",
						nliResult.getDescObject().getType(),
						nliResult.getDescObject().getName());	
			}
			System.out.format("|\t- /////////////////////////////////////\n");
			
			// Get all of JokeData into ArrayList.
			ArrayList<JokeData> dataArray = nliResult.getDataObjects();
			for (int x = 0; x < dataArray.size(); x++) {
				System.out.format("|\t- IDS %s Data[%s] :\n", 
						nliResult.getDescObject().getType(), x);
				System.out.format("|\t\t- Content: %s\n",
						dataArray.get(x).getContent());
			}
		}
		
		// ********************************************************************
		// * STOCK MARKET EXAMPLE
		// *-------------------------------------------------------------------
		// * Simplified Chinese (China): 中国石油的股票
		// * Traditional Chinese (Taiwan): 台積電的股票
		// ********************************************************************
		// * Check 'the TYPE of nliResult' if contains IDS stock market data.
		// * And also check 'the TYPE OF DescObject' if it is a QUESTION mode.
		if (nliResult.getType().equals(IDSResult.Types.STOCK_MARKET.getName())
				|| nliResult.getDescObject().getType().equals(IDSResult.Types.STOCK_MARKET.getName())) {
			// Get all of StockMarketData into ArrayList.
			ArrayList<StockMarketData> dataArray = nliResult.getDataObjects();
			for (int x = 0; x < dataArray.size(); x++) {
				System.out.format("|\t- IDS Stock Market Data[%s] :\n", x);
				System.out.format("|\t\t- History: %s\n", 
						dataArray.get(x).isHistory() ? "YES" : "NO");
				System.out.format("|\t\t- Stock: %s\n",
						dataArray.get(x).getID());
				System.out.format("|\t\t- Name: %s\n",
						dataArray.get(x).getName());
				System.out.format("|\t\t- Current Price: %s\n",
						dataArray.get(x).getCurrentPrice());
				System.out.format("|\t\t- Opening Price: %s\n",
						dataArray.get(x).getOpeningPrice());
				System.out.format("|\t\t- Closing Price: %s\n",
						dataArray.get(x).getClosingPrice());
				System.out.format("|\t\t- Highest Price: %s\n",
						dataArray.get(x).getHighestPrice());
				System.out.format("|\t\t- Lowest Price: %s\n",
						dataArray.get(x).getLowestPrice());
				System.out.format("|\t\t- Change Rate: %s\n",
						dataArray.get(x).getChangeRate());
				System.out.format("|\t\t- Change Amount: %s\n",
						dataArray.get(x).getChangeAmount());
				System.out.format("|\t\t- Volume: %s\n",
						dataArray.get(x).getVolume());
				System.out.format("|\t\t- Amount: %s\n",
						dataArray.get(x).getAmount());
				System.out.format("|\t\t- Intent Info.: %s\n",
						dataArray.get(x).getIntentInfo());
				System.out.format("|\t\t- Time: %s\n",
						dataArray.get(x).getTime());
				System.out.format("|\t\t- Favorite: %s\n", 
						dataArray.get(x).isFavorite() ? "YES" : "NO");
			}
		}
		
		// ********************************************************************
		// * MATH EXAMPLE
		// *-------------------------------------------------------------------
		// * Simplified Chinese (China): 2乘4等于几
		// * Traditional Chinese (Taiwan): 2乘4等於幾
		// ********************************************************************
		// * Check 'the TYPE of nliResult' if contains IDS math data.
		if (nliResult.getType().equals(IDSResult.Types.MATH.getName())) {
			// Get all of MathData into ArrayList.
			ArrayList<MathData> dataArray = nliResult.getDataObjects();
			for (int x = 0; x < dataArray.size(); x++) {
				System.out.format("|\t- IDS Math Data[%s] :\n", x);
				System.out.format("|\t\t- Reply: %s\n",
						dataArray.get(x).getContent());
				System.out.format("|\t\t- Result: %s\n",
						dataArray.get(x).getResult());
			}
		}	
		
		// ********************************************************************
		// * UNIT CONVERT EXAMPLE
		// *-------------------------------------------------------------------
		// * Simplified Chinese (China): 0.2千米是多少米
		// * Traditional Chinese (Taiwan): 0.2公尺是多少公分
		// ********************************************************************
		// * Check 'the TYPE of nliResult' if contains IDS unit convert data.
		if (nliResult.getType().equals(IDSResult.Types.UNIT_CONVERT.getName())) {
			// Get all of UnitConvertData into ArrayList.
			ArrayList<UnitConvertData> dataArray = nliResult.getDataObjects();
			for (int x = 0; x < dataArray.size(); x++) {
				System.out.format("|\t- IDS Unit Convert Data[%s] :\n", x);
				System.out.format("|\t\t- Reply: %s\n",
						dataArray.get(x).getContent());
				System.out.format("|\t\t- From: %s %s\n",
						dataArray.get(x).getSourceValue(),
						dataArray.get(x).getSourceUnit());
				System.out.format("|\t\t- Convert To: %s %s\n",
						dataArray.get(x).getDestinationValue(),
						dataArray.get(x).getDestinationUnit());
			}
		}	
		
		// ********************************************************************
		// * EXCHANGE RATE EXAMPLE
		// *-------------------------------------------------------------------
		// * Simplified Chinese (China): 美元汇率
		// * Traditional Chinese (Taiwan): 美元匯率
		// ********************************************************************
		// * Check 'the TYPE of nliResult' if contains IDS exchange rate data.
		if (nliResult.getType().equals(IDSResult.Types.EXCHANGE_RATE.getName())) {
			
			// Extended fields for this module:
			System.out.format("|\t- /////////////////////////////////////\n");
			// Try to get source currency from the DescObject.
			if (nliResult.getDescObject().hasSourceCurrency()) {
				System.out.format("|\t- Source Currency: %s\n",
						nliResult.getDescObject().getSourceCurrency());	
			}
			System.out.format("|\t- /////////////////////////////////////\n");
			
			// Get all of ExchangeRateData into ArrayList.
			ArrayList<ExchangeRateData> dataArray = nliResult.getDataObjects();
			for (int x = 0; x < dataArray.size(); x++) {
				System.out.format("|\t- IDS Exchange Rate Data[%s] :\n", x);
				System.out.format("|\t\t- Target: %s\n",
						dataArray.get(x).getTargetCurrency());
			}
		}	
		
		// ********************************************************************
		// * COOKING EXAMPLE
		// *-------------------------------------------------------------------
		// * Simplified Chinese (China): 红烧肉怎么做
		// * Traditional Chinese (Taiwan): 紅燒肉怎麼做
		// ********************************************************************
		// * Check 'the TYPE of nliResult' if contains IDS cooking data.
		// * And also check 'the TYPE OF DescObject' if it is a SELECTION mode.
		if (nliResult.getType().equals(IDSResult.Types.COOKING.getName())
				|| nliResult.getDescObject().getType().equals(IDSResult.Types.COOKING.getName())) {
			// Get all of CookingData into ArrayList.
			ArrayList<CookingData> dataArray = nliResult.getDataObjects();
			for (int x = 0; x < dataArray.size(); x++) {
				System.out.format("|\t- IDS Cooking Data[%s] :\n", x);
				System.out.format("|\t\t- Name: %s\n",
						dataArray.get(x).getName());
				System.out.format("|\t\t- Content: %s\n",
						dataArray.get(x).getContent());
			}
		}
		
		// ********************************************************************
		// * OPEN WEB EXAMPLE
		// *-------------------------------------------------------------------
		// * Simplified Chinese (China): 我要买手机
		// * Traditional Chinese (Taiwan): 我要買手機
		// ********************************************************************
		// * Check 'the TYPE of nliResult' if contains IDS open-web data.
		// * And also check 'the TYPE OF DescObject' if it is a SELECTION mode.
		if (nliResult.getType().equals(IDSResult.Types.OPEN_WEB.getName())) {
			// Get all of OpenWebData into ArrayList.
			ArrayList<OpenWebData> dataArray = nliResult.getDataObjects();
			for (int x = 0; x < dataArray.size(); x++) {
				// And also check 'the TYPE OF DescObject' to get action type.
				System.out.format("|\t- IDS Open Web for %s Data[%s] :\n", 
						nliResult.getDescObject().getType(), x);
				System.out.format("|\t\t- URL: %s\n",
						dataArray.get(x).getURL());
			}
		}

		System.out.println("|---------------------------------------------- ");
	}
	
}
