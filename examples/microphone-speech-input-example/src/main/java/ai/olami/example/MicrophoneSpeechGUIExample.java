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

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;

import ai.olami.cloudService.APIConfiguration;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class MicrophoneSpeechGUIExample {
	
	private String[] mServerListText = {"Simplified Chinese【 China  】", "Traditional Chinese【 Taiwan  】"};
	private String[] mRecStateText = {"Press to【 start 】, then speaking!", "Press to【 stop 】", "Please Wait ..."};
	
	private boolean mIsRecording = false;
	
	private MicrophoneSpeechRecognizerExample mMicrophoneSpeechRecognizer = null;
	private APIConfiguration mAPIConfig = null;
	
	private JFrame mFrame = new JFrame();
	private JComboBox mServerListBox = new JComboBox(mServerListText);
	private JTextField mAppKeyTextBox = new JTextField();
	private JTextField mAppSecretTextBox = new JTextField();
	private JTextArea mTextArea = new JTextArea();
	private JButton mRecordButton = new JButton(mRecStateText[0]);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		System.setProperty("file.encoding", "UTF-8");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new MicrophoneSpeechGUIExample();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MicrophoneSpeechGUIExample() {
		// Initialize  microphone recorder recognizer and set callback.
		mMicrophoneSpeechRecognizer = new MicrophoneSpeechRecognizerExample(new ISpeechRecognizerListenerExample() {
			// Do something when the recognition is completed.
			public void onRecognizeComplete() {
				mRecordButton.setText(mRecStateText[0]);
				mRecordButton.setEnabled(true);
			}
			// Do something when we get recognition results.
			public void onRecognizeResultChange(String recognitionTextResult) {
				mTextArea.setText(recognitionTextResult);
			}
			
			// Do something when error occurs.
			public void onError(String errorMessage) {
				cancelRecognizing();
				JOptionPane.showMessageDialog(mFrame, errorMessage, "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		});
		
		// Initialize GUI
		initialize();
		
		// Ready
		mRecordButton.setEnabled(true);
		mFrame.setVisible(true);
		mFrame.setResizable(true);
	}
	
	/**
	 * Start audio recording and the speech recognition.
	 */
	private void startRecording() {
		try {
			mRecordButton.setText(mRecStateText[2]);
			mRecordButton.setEnabled(false);
			mAPIConfig = new APIConfiguration(mAppKeyTextBox.getText(), mAppSecretTextBox.getText(), mServerListBox.getSelectedIndex());
			mMicrophoneSpeechRecognizer.setAPIConfig(mAPIConfig);
			mMicrophoneSpeechRecognizer.start();
			mRecordButton.setText(mRecStateText[1]);
			mRecordButton.setEnabled(true);
			mIsRecording = true;
		} catch (Exception ex) {
			cancelRecognizing();
			JOptionPane.showMessageDialog(mFrame, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Stop audio recording and the speech recognition.
	 */
	private void stopRecording() {
		mRecordButton.setText(mRecStateText[2]);
		mRecordButton.setEnabled(false);
		mMicrophoneSpeechRecognizer.stop();
		mIsRecording = false;
	}
	
	/**
	 * Cancel all tasks.
	 */
	private void cancelRecognizing() {
		mMicrophoneSpeechRecognizer.cancel();
		mRecordButton.setText(mRecStateText[0]);
		mRecordButton.setEnabled(true);
		mIsRecording = false;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int windowWidth = (int) (screenSize.width * 0.7);
		int windowHeight = (int) (screenSize.height * 0.7);
		int windowX = (screenSize.width - windowWidth) / 2;
		int windowY = (screenSize.height - windowHeight) / 2;
		
		mFrame.setBounds(0, 0, 500, 300);
		mFrame.setSize(windowWidth, windowHeight);
		mFrame.setLocation(windowX, windowY);
		mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mFrame.setTitle("OLAMI Speech Recognition Example");
		
		mFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				cancelRecognizing();
				System.exit(0);
			}
		});
		
		JPanel framePanel = new JPanel();
		mFrame.getContentPane().add(framePanel);
		framePanel.setLayout(new BorderLayout(0, 0));
		framePanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		GridBagConstraints gbConstraints = null;
		
		// Top
		JPanel topPanel = new JPanel();
		framePanel.add(topPanel, BorderLayout.NORTH);
		topPanel.setLayout(new GridBagLayout());
		topPanel.setBorder(new EmptyBorder(5, 10, 10, 10));
		
		gbConstraints = new GridBagConstraints();
		gbConstraints.fill = GridBagConstraints.HORIZONTAL;
		gbConstraints.weightx = 0.2;
		gbConstraints.gridx = 0;
		gbConstraints.gridy = 0;
		topPanel.add(new JLabel("Server : "), gbConstraints);
		
		gbConstraints = new GridBagConstraints();
		gbConstraints.fill = GridBagConstraints.HORIZONTAL;
		gbConstraints.weightx = 0.8;
		gbConstraints.gridx = 1;
		gbConstraints.gridy = 0;
		topPanel.add(mServerListBox, gbConstraints);
		mServerListBox.setFont(new Font("Arial Unicode MS", Font.PLAIN, 12));
		mServerListBox.setSelectedIndex(mMicrophoneSpeechRecognizer.getAPIConfig().getLocalizeOption());
		
		gbConstraints = new GridBagConstraints();
		gbConstraints.fill = GridBagConstraints.HORIZONTAL;
		gbConstraints.gridx = 0;
		gbConstraints.gridy = 1;
		topPanel.add(new JLabel("App Key : "), gbConstraints);
		
		gbConstraints = new GridBagConstraints();
		gbConstraints.fill = GridBagConstraints.HORIZONTAL;
		gbConstraints.gridx = 1;
		gbConstraints.gridy = 1;
		topPanel.add(mAppKeyTextBox, gbConstraints);
		mAppKeyTextBox.setText(mMicrophoneSpeechRecognizer.getAPIConfig().getAppKey());
		
		gbConstraints = new GridBagConstraints();
		gbConstraints.fill = GridBagConstraints.HORIZONTAL;
		gbConstraints.gridx = 0;
		gbConstraints.gridy = 2;
		topPanel.add(new JLabel("App Secret : "), gbConstraints);
		
		gbConstraints = new GridBagConstraints();
		gbConstraints.fill = GridBagConstraints.HORIZONTAL;
		gbConstraints.gridx = 1;
		gbConstraints.gridy = 2;
		topPanel.add(mAppSecretTextBox, gbConstraints);
		mAppSecretTextBox.setText(mMicrophoneSpeechRecognizer.getAPIConfig().getAppSecret());
		
		// Center
		JScrollPane scrollPane = new JScrollPane();
		framePanel.add(scrollPane, BorderLayout.CENTER);
		mTextArea.setBorder(new EmptyBorder(5, 5, 5, 5));
		mTextArea.setFont(new Font("Arial Unicode MS", Font.PLAIN, 16));
		mTextArea.setLineWrap(true);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setViewportView(mTextArea);
		
		// Bottom
		JPanel bottomPanel = new JPanel();
		framePanel.add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.setLayout(new GridBagLayout());
		bottomPanel.setBorder(new EmptyBorder(5, 0, 5, 0));
		
		mRecordButton.setPreferredSize(new Dimension(mRecordButton.getWidth(), 50));
		mRecordButton.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		mRecordButton.setEnabled(false);
		mRecordButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!mIsRecording) {
					startRecording();
				} else {
					stopRecording();
				}
			}
		});
		
		gbConstraints = new GridBagConstraints();
		gbConstraints.fill = GridBagConstraints.BOTH;
		gbConstraints.weightx = 0.8;
		gbConstraints.gridx = 0;
		gbConstraints.gridy = 0;
		bottomPanel.add(mRecordButton, gbConstraints);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setPreferredSize(new Dimension(cancelButton.getWidth(), 50));
		cancelButton.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelRecognizing();
			}
		});
		
		gbConstraints = new GridBagConstraints();
		gbConstraints.fill = GridBagConstraints.BOTH;
		gbConstraints.weightx = 0.2;
		gbConstraints.gridx = 1;
		gbConstraints.gridy = 0;
		bottomPanel.add(cancelButton, gbConstraints);
		
	}

}
