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

import ai.olami.util.HttpClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.xiph.speex.SpeexEncoder;

public class SpeechRecognizer extends APIRequestBase {

	public static final int AUDIO_TYPE_PCM_RAW = 0;
	public static final int AUDIO_TYPE_PCM_WAVE = 1;
	
	public static final int AUDIO_LENGTH_MILLISECONDS_PER_FRAME = 10;
	public static final int AUDIO_SAMPLE_RATE = 16000;
	public static final int AUDIO_BITS_PER_SAMPLE = 16;
	public static final int AUDIO_CHANNELS = 1;
	
	private static final String SEQ_TYPE_SEG = "seg";
	private static final String SEQ_TYPE_NLI = "nli";
	private static final String SEQ_TYPE_ALL = "nli,seg";
	
	private static final int WAVE_HEADER_SIZE = 44;
	private static final int AUDIO_FRAME_SIZE = (int) (
		(
			AUDIO_SAMPLE_RATE 
			* AUDIO_BITS_PER_SAMPLE 
			* AUDIO_CHANNELS
			* ((float) AUDIO_LENGTH_MILLISECONDS_PER_FRAME / 1000)
		) / 8
	);
	
	private static final String EXMSG_AUDIO_TYPE_NOT_SET = "Audio type has not been set! You must to specify the audio type by setAudioType(int audioType) before doing this.";
	private static final String EXMSG_INVALID_AUDIO_TYPE = "Invalid audio type!";
	
	private String mApiName = APIConfiguration.API_NAME_ASR;
	private String mDefaultSeqType = SEQ_TYPE_SEG;
	
	private int mAudioType = -1;
	private boolean mEncodeToSpeex = true;
	private SpeexEncoder mSpeexEncoder = null;
	private int mSpeexProcessFrames = 2;
	private int mSpeexProcessSize = 0;
	
	private int mAudioBufferListMaxSize = 0;
	private int mAudioBufferListMinSize = 0;
	private int mAudioBufferListCurrentSize = 0;
	private int mAudioBufferListAppendedSize = 0;
	private LinkedList<byte[]> mAudioBufferList = new LinkedList<byte[]>();
	
	/**
	 * Speech Recognizer to issue Cloud Speech Recognition API requests.
	 * 
	 * @param configuration - API configurations.
	 */
	public SpeechRecognizer(APIConfiguration configuration) {
		super(configuration);
		
		if (mEncodeToSpeex) {
			initSpeexEncoder();
		}
		
		mAudioBufferListMinSize = mSpeexProcessFrames * getAudioFrameSize();
		mAudioBufferListMaxSize = (
			(30 * 1000) / AUDIO_LENGTH_MILLISECONDS_PER_FRAME
		) * getAudioFrameSize();
	}
	
	/**
	 * Get audio frame size used by the speech recognizer.
	 * 
	 * @return Frame size in bytes.
	 */
	public int getAudioFrameSize() {
		if (mEncodeToSpeex && (mSpeexEncoder != null)) {
			return mSpeexEncoder.getFrameSize();
		}
		return AUDIO_FRAME_SIZE;
	}

	/**
	 * Request to upload the specified audio for speech recognition. 
	 * You must to specify the audio type by setAudioType(int audioType) before using this method. 
	 * 
	 * @param identifier - Identifier CookieSet.
	 * @param filePath - The path of the audio file.
	 *                   Wave Header is required if it is a Wave audio.
	 * @param isFinalAudio - TRUE if this is the last audio of a speech input.
	 * @return API response with the audio uploading status.
	 * @throws IOException File handling or HTTP connection failed, or other exceptions.
	 * @throws NoSuchAlgorithmException Failed to create signature.
	 */
	public APIResponse uploadAudio(
			CookieSet identifier,
			String filePath,
			boolean isFinalAudio
	) throws IOException, NoSuchAlgorithmException {
		
		if (mAudioType == -1) {
			throw new UnsupportedOperationException(EXMSG_AUDIO_TYPE_NOT_SET);
		}
		
		return uploadAudio(identifier, filePath, mAudioType, isFinalAudio);
		
	}
	
	/**
	 * Request to upload the specified audio for speech recognition.
	 * 
	 * @param identifier - Identifier CookieSet.
	 * @param filePath - The path of the audio file.
	 *                   Wave Header is required if it is a Wave audio.
	 * @param audioType - Audio type: 
	 *                    AUDIO_TYPE_PCM_RAW for PCM raw data.
	 *                    AUDIO_TYPE_PCM_WAVE for Wave audio.
	 * @param isFinalAudio - TRUE if this is the last audio of a speech input.
	 * @return API response with the audio uploading status.
	 * @throws IOException File handling or HTTP connection failed, or other exceptions.
	 * @throws NoSuchAlgorithmException Failed to create signature.
	 */
	public APIResponse uploadAudio(
			CookieSet identifier,
			String filePath,
			int audioType,
			boolean isFinalAudio
	) throws IOException, NoSuchAlgorithmException {
		
		File file = new File(filePath);
		
		if (file == null || (!file.exists())) {
			throw new FileNotFoundException("File not found: " + filePath);
		}
		
		FileInputStream fileIn = new FileInputStream(file);
		byte[] fileData = new byte[(int) file.length()];
		fileIn.read(fileData);
		fileIn.close();
		
		return uploadAudio(identifier, fileData, audioType, isFinalAudio);
		
	}
	
	/**
	 * Request to upload the specified audio for speech recognition. 
	 * You must to specify the audio type by setAudioType(int audioType) before using this method. 
	 * 
	 * @param identifier - Identifier CookieSet.
	 * @param audioData - The audio data.
	 *                    Wave Header is required if it is a Wave audio.
	 * @param isFinalAudio - TRUE if this is the last audio of a speech input.
	 * @return API response with the audio uploading status.
	 * @throws IOException HTTP connection failed, or other exceptions.
	 * @throws NoSuchAlgorithmException Failed to create signature.
	 */
	public APIResponse uploadAudio(
			CookieSet identifier,
			byte[] audioData,
			boolean isFinalAudio
	) throws IOException, NoSuchAlgorithmException {
		
		if (mAudioType == -1) {
			throw new UnsupportedOperationException(EXMSG_AUDIO_TYPE_NOT_SET);
		}
		
		return uploadAudio(identifier, audioData, mAudioType, isFinalAudio);	
	}
	
	/**
	 * Request to upload the specified audio for speech recognition.
	 * 
	 * @param identifier - Identifier CookieSet.
	 * @param audioData - The audio data.
	 *                    Wave Header is required if it is a Wave audio.
	 * @param audioType - Audio type: 
	 *                    AUDIO_TYPE_PCM_RAW for PCM raw data.
	 *                    AUDIO_TYPE_PCM_WAVE for Wave audio.
	 * @param isFinalAudio - TRUE if this is the last audio of a speech input.
	 * @return API response with the audio uploading status.
	 * @throws IOException HTTP connection failed, or other exceptions.
	 * @throws NoSuchAlgorithmException Failed to create signature.
	 */
	public APIResponse uploadAudio(
			CookieSet identifier,
			byte[] audioData,
			int audioType,
			boolean isFinalAudio
	) throws IOException, NoSuchAlgorithmException {
		
		byte[] audioBuffer = null;
		int audioSize = audioData.length;
		
		switch (audioType) {
			case AUDIO_TYPE_PCM_RAW:
				if (mEncodeToSpeex) {
					audioBuffer = audioData;
					audioSize = speexEncodeRawWavePCM(audioBuffer);
				} else {
					if (!containsWaveHeader(audioData)) {
						audioBuffer = getWithWaveHeader(audioData, false);
						audioSize = audioBuffer.length;
					}
				}
				break;
				
			case AUDIO_TYPE_PCM_WAVE:
				if (mEncodeToSpeex) {
					if (containsWaveHeader(audioData)) {
						audioBuffer = getWithoutWaveHeader(audioData, false);
					}
					audioSize = speexEncodeRawWavePCM(audioBuffer);
				}
				break;
				
			default:
				throw new IllegalArgumentException(EXMSG_INVALID_AUDIO_TYPE);
		}
		
		return uploadAudioData(identifier, audioBuffer, audioSize, isFinalAudio);
		
	}
	
	/**
	 * Set and specify the type of the speech audio.
	 * 
	 * @param audioType - Audio type: 
	 *                    AUDIO_TYPE_PCM_RAW for PCM raw data.
	 *                    AUDIO_TYPE_PCM_WAVE for Wave audio.
	 */
	public void setAudioType(int audioType) {
		switch (audioType) {
			case AUDIO_TYPE_PCM_RAW:
				break;
			case AUDIO_TYPE_PCM_WAVE:
				break;
			default:
				throw new IllegalArgumentException(EXMSG_INVALID_AUDIO_TYPE);
		}
		
		mAudioType = audioType;
	}
	
	/**
	 * Get the maximum size in bytes of the audio buffer to append audio frames data.
	 * 
	 * @return The size in bytes.
	 */
	public int getAudioBufferMaxSize() {
		return mAudioBufferListMaxSize;
	}
	
	/**
	 * Get the minimum size in bytes of the audio buffer to append audio frames data.
	 * 
	 * @return The size in bytes.
	 */
	public int getAudioBufferMinSize() {
		return mAudioBufferListMinSize;
	}
	
	/**
	 * Append audio data contains N frames to wait the upload for speech recognition.
	 * The size of data in bytes must be a multiple of the size getAudioBufferMinSize() provides.
	 * The total size of all of appended data must be less than or equal to the size getAudioBufferMaxSize() provides.
	 * 
	 * @param audioFramesData - The audio frames data. (Contains N frames) 
	 * @return Total size of all of appended audio data.
	 */
	public int appendAudioFramesData(byte[] audioFramesData) {
		
		if (mAudioType == -1) {
			throw new UnsupportedOperationException(EXMSG_AUDIO_TYPE_NOT_SET);
		}
		
		if (mAudioBufferListAppendedSize > mAudioBufferListMaxSize) {
			throw new IllegalStateException("The total size of append buffers is greater than the limited size (" + mAudioBufferListMaxSize + "). You have to flush for upload.");
		}
		
		synchronized (mAudioBufferList) {
			int audioSize = audioFramesData.length;
			byte[] tempData = new byte[audioSize]; 
			System.arraycopy(audioFramesData, 0, tempData, 0, audioSize);
			
			if (mEncodeToSpeex) {
				if ((audioSize < mAudioBufferListMinSize) 
						|| ((audioSize % mSpeexProcessSize) != 0)) {
					throw new IllegalArgumentException("The size of input data must be greater than " + mAudioBufferListMinSize + " (Bytes), and it must be a multiple of " + mSpeexProcessSize + " (Bytes).");
				}
				audioSize = speexEncodeRawWavePCM(tempData);
			}
			
			byte[] appendData = new byte[audioSize];
			System.arraycopy(tempData, 0, appendData, 0, audioSize);
			Arrays.fill(tempData, (byte) 0);
		
			mAudioBufferList.offer(appendData);
			mAudioBufferListCurrentSize += audioSize;	
			mAudioBufferListAppendedSize += audioFramesData.length;
		}
		
		return mAudioBufferListAppendedSize;
	}
	
	/**
	 * Flush all of appended audio data to upload and request for speech recognition.
	 * 
	 * @param identifier - Identifier CookieSet.
	 * @param isFinalAudio - TRUE if this is the last audio of a speech input.
	 * @return API response with the audio uploading status.
	 * @throws IOException HTTP connection failed, or other exceptions.
	 * @throws NoSuchAlgorithmException Failed to create signature.
	 */
	public APIResponse flushToUploadAudio(
			CookieSet identifier,
			boolean isFinalAudio
	) throws IOException, NoSuchAlgorithmException {
		
		if (mAudioType == -1) {
			throw new UnsupportedOperationException(EXMSG_AUDIO_TYPE_NOT_SET);
		}
		
		byte[] buffer = null;
				
		synchronized (mAudioBufferList) {
			if (mAudioBufferList.size() == 0) {
				throw new IllegalStateException("There are no appended buffers.");
			}
			
			buffer = new byte[mAudioBufferListCurrentSize];
			byte[] data = null;
			int currentPos = 0;
			while ((data = mAudioBufferList.poll()) != null) {
				System.arraycopy(data, 0, buffer, currentPos, data.length);
				currentPos += data.length;
			}
			
			mAudioBufferListCurrentSize = 0;
			mAudioBufferListAppendedSize = 0;
		}
		
		if (!mEncodeToSpeex) {
			// When the user uses the batch upload, we do not know whether 
			// the user is correctly handling the cutting of the audio data. 
			// So, we may need to remove the original wave header.
			if (containsWaveHeader(buffer)) { 
				buffer = getWithoutWaveHeader(buffer, true);
			}
			// And then create a new header to ensure each batch upload 
			// contains a wave header with the correct header information.
			buffer = getWithWaveHeader(buffer, true);
		}
		
		return uploadAudioData(identifier, buffer, buffer.length, isFinalAudio);
	}
	
	/**
	 * Release all of appended audio data.
	 */
	public void releaseAppendedAudio() {
		synchronized (mAudioBufferList) {
			mAudioBufferList.clear();
			mAudioBufferListCurrentSize = 0;
			mAudioBufferListAppendedSize = 0;
		}
	}
	
	/**
	 * Get size of all appended audio data.
	 * 
	 * @return Appended audio size in bytes.
	 */
	public int getAppendedAudioSize() {
		return mAudioBufferListAppendedSize;
	}
	
	/**
	 * Get size of the buffered data from the appended audio.
	 * 
	 * @return Size of the buffered data from the appended audio.
	 */
	public int getBufferedDataSize() {
		return mAudioBufferListCurrentSize;
	}
	
	/**
	 * Check if there are still buffered data that has not been flushed yet.
	 * 
	 * @return TRUE if buffered data exists.
	 */
	public boolean hasBufferedData() {
		return (!mAudioBufferList.isEmpty());
	}
	
	/**
	 * Request to get speech recognition results by specified task identifier.
	 * Before you call this method, you must to upload audio by related methods first.
	 * 
	 * @param identifier - Identifier CookieSet.
	 * @return API response with speech recognition results.
	 * @throws IllegalArgumentException Invalid contents of the CookieSet.
	 * @throws IOException HTTP connection failed, or other exceptions.
	 * @throws NoSuchAlgorithmException Failed to create signature.
	 */
	public APIResponse requestRecognition(CookieSet identifier) 
			throws IllegalArgumentException, IOException, NoSuchAlgorithmException {
		return sendGetResultsRequest(identifier, SEQ_TYPE_SEG);
	}
	
	/**
	 * Request to get speech recognition results by specified task identifier.
	 * Before you call this method, you must to upload audio by related methods first.
	 * 
	 * @param identifier - Identifier CookieSet.
	 * @return API response with speech recognition results and NLI results.
	 * @throws IllegalArgumentException Invalid contents of the CookieSet.
	 * @throws IOException HTTP connection failed, or other exceptions.
	 * @throws NoSuchAlgorithmException Failed to create signature.
	 */
	public APIResponse requestRecognitionWithNLI(CookieSet identifier) 
			throws IllegalArgumentException, IOException, NoSuchAlgorithmException {
		return sendGetResultsRequest(identifier, SEQ_TYPE_NLI);
	}
	

	/**
	 * Request to get speech recognition results by specified task identifier.
	 * Before you call this method, you must to upload audio by related methods first.
	 * 
	 * @param identifier - Identifier CookieSet.
	 * @return API response with all kinds of recognition results.
	 * @throws IllegalArgumentException Invalid contents of the CookieSet.
	 * @throws IOException HTTP connection failed, or other exceptions.
	 * @throws NoSuchAlgorithmException Failed to create signature.
	 */
	public APIResponse requestRecognitionWithAll(CookieSet identifier) 
			throws IllegalArgumentException, IOException, NoSuchAlgorithmException {
		return sendGetResultsRequest(identifier, SEQ_TYPE_ALL);
	}
	
	private APIResponse uploadAudioData(
			CookieSet identifier,
			byte[] audioData,
			int audioSize,
			boolean isFinalAudio
	) throws IOException, NoSuchAlgorithmException {
				
		final Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("compress", (mEncodeToSpeex ? "1" : "0"));
		queryParams.put("seq", mDefaultSeqType);
		queryParams.put("stop", (isFinalAudio ? "1" : "0"));
		
		String cookies = identifier.getContents().toString();
		final URL url = new URL(getConfiguration().getBaseRequestURL(mApiName, queryParams));
		final HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
		httpConnection.setRequestMethod("POST");
		if ((cookies != null) && (!cookies.equals(""))) {
			httpConnection.setRequestProperty("Cookie", cookies);
		}
		httpConnection.setConnectTimeout(getTimeout());
		
		HttpClient httpClient = null;
		String response = "";

		try {
			httpClient = new HttpClient(httpConnection);
			httpClient.octetStreamConnect(audioData, audioSize);
			if(httpClient.getResponseCode() == HttpURLConnection.HTTP_OK) {
				response = httpClient.getResponseContent();
				// Get cookie to setup speech task identifier.
				identifier.setContents(httpClient.getCookies());
				if (identifier.getContents() == null) {
					throw new IOException("Failed to get cookie from server.");
				}
			} else {
				throw new IOException(httpClient.getResponseMessage());
			}
		} finally {
			httpClient.close();
		}
		
		return APIResponseBuilder.create(response);
		
	}
	
	private APIResponse sendGetResultsRequest(
			CookieSet identifier, 
			String seqType
	) throws IllegalArgumentException, IOException, NoSuchAlgorithmException {	
		
		String cookies = identifier.getContents().toString();
		if (cookies == null) {
			throw new IllegalArgumentException("Invalid contents of the CookieSet.");
		}
		
		final Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("compress", (mEncodeToSpeex ? "1" : "0"));
		queryParams.put("seq", seqType);
		
		final URL url = new URL(getConfiguration().getBaseRequestURL(mApiName, queryParams));
		final HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
		httpConnection.setRequestMethod("GET");
		httpConnection.setRequestProperty("Cookie", cookies);
		httpConnection.setConnectTimeout(getTimeout());
		
		HttpClient httpClient = null;
		String response = null;
		
		try {
			httpClient = new HttpClient(httpConnection);
			httpClient.normalConnect();
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
	
	private void initSpeexEncoder() {
		int mode = 1;
		int quality = 10;
		if (mSpeexEncoder == null) {
			mSpeexEncoder = new SpeexEncoder();	
			mSpeexEncoder.init(mode, quality, AUDIO_SAMPLE_RATE, AUDIO_CHANNELS);
		}
		mSpeexProcessSize = mSpeexProcessFrames * AUDIO_CHANNELS * mSpeexEncoder.getFrameSize();
	}
	
	private int speexEncodeRawWavePCM(byte[] audioData) {	
		
		initSpeexEncoder();
		
		int blockSize = mSpeexProcessSize;
		byte[] data = new byte[blockSize];
		int encodeSize = 0;
		int encodedBytes = 0;
		int processedOffset = 0;
		
		while ((processedOffset + blockSize) <= audioData.length) {
			System.arraycopy(audioData, processedOffset, data, 0, blockSize);
			processedOffset += blockSize;
			mSpeexEncoder.processData(data, 0, mSpeexProcessSize);
			encodeSize = mSpeexEncoder.getProcessedData(data, 0);
			if (encodeSize > 0) {
				if (encodeSize > audioData.length) {
					encodeSize = audioData.length;
				}
				System.arraycopy(data, 0, audioData, encodedBytes, encodeSize);
				encodedBytes += encodeSize;
			}
			Arrays.fill(data, (byte) 0);
		}
		
		if (encodedBytes < audioData.length) {
			Arrays.fill(audioData, encodedBytes, (audioData.length - 1), (byte) 0);
		}
		
		return encodedBytes;
		
	}
	
	private boolean containsWaveHeader(byte[] audioData) {
		return (audioData.length > WAVE_HEADER_SIZE
				&& audioData[0] == 'R'
				&& audioData[1] == 'I' 
				&& audioData[2] == 'F' 
				&& audioData[3] == 'F'
				&& audioData[8] == 'W' 
				&& audioData[9] == 'A'
				&& audioData[10] == 'V' 
				&& audioData[11] == 'E');
	}
	
	private byte[] getWithoutWaveHeader(
			byte[] soureData,
			boolean cleanSourceData
	) {
		int newSize = soureData.length - WAVE_HEADER_SIZE;
		byte[] newData = new byte[newSize];
		System.arraycopy(soureData, WAVE_HEADER_SIZE, newData, 0, newSize);
		
		if (cleanSourceData) {
			Arrays.fill(soureData, (byte) 0);
		}
		
		return newData;	
	} 

	private byte[] getWithWaveHeader(
			byte[] soureData,
			boolean cleanSourceData
	) {

		int audioDataSize = soureData.length;
		int totalLength = soureData.length;
		int byteRate = AUDIO_BITS_PER_SAMPLE * AUDIO_SAMPLE_RATE * 1 / 8;
		
		byte[] newData = new byte[audioDataSize + WAVE_HEADER_SIZE];
	
		newData[0] = 'R'; // RIFF/WAVE header
		newData[1] = 'I';
		newData[2] = 'F';
		newData[3] = 'F';
		newData[4] = (byte) (audioDataSize & 0xff);
		newData[5] = (byte) ((audioDataSize >> 8) & 0xff);
		newData[6] = (byte) ((audioDataSize >> 16) & 0xff);
		newData[7] = (byte) ((audioDataSize >> 24) & 0xff);
		newData[8] = 'W';
		newData[9] = 'A';
		newData[10] = 'V';
		newData[11] = 'E';
		newData[12] = 'f'; // 'fmt ' chunk
		newData[13] = 'm';
		newData[14] = 't';
		newData[15] = ' ';
		newData[16] = 16; // 4 bytes: size of 'fmt ' chunk
		newData[17] = 0;
		newData[18] = 0;
		newData[19] = 0;
		newData[20] = 1; // format = 1
		newData[21] = 0;
		newData[22] = (byte) AUDIO_CHANNELS;
		newData[23] = 0;
		newData[24] = (byte) (AUDIO_SAMPLE_RATE & 0xff);
		newData[25] = (byte) ((AUDIO_SAMPLE_RATE >> 8) & 0xff);
		newData[26] = (byte) ((AUDIO_SAMPLE_RATE >> 16) & 0xff);
		newData[27] = (byte) ((AUDIO_SAMPLE_RATE >> 24) & 0xff);
		newData[28] = (byte) (byteRate & 0xff);
		newData[29] = (byte) ((byteRate >> 8) & 0xff);
		newData[30] = (byte) ((byteRate >> 16) & 0xff);
		newData[31] = (byte) ((byteRate >> 24) & 0xff);
		newData[32] = (byte) 2; // block align
		newData[33] = 0;
		newData[34] = AUDIO_BITS_PER_SAMPLE;
		newData[35] = 0;
		newData[36] = 'd';
		newData[37] = 'a';
		newData[38] = 't';
		newData[39] = 'a';
		newData[40] = (byte) (totalLength & 0xff);
		newData[41] = (byte) ((totalLength >> 8) & 0xff);
		newData[42] = (byte) ((totalLength >> 16) & 0xff);
		newData[43] = (byte) ((totalLength >> 24) & 0xff);
		
		System.arraycopy(soureData, 0, newData, WAVE_HEADER_SIZE, audioDataSize);
		
		if (cleanSourceData) {
			Arrays.fill(soureData, (byte) 0);
		}
		
		return newData;
		
	}

}
