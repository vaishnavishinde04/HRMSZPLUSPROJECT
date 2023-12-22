package com.HRMS.utility;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.polly.AmazonPolly;
import com.amazonaws.services.polly.AmazonPollyClientBuilder;
import com.amazonaws.services.polly.model.SynthesizeSpeechRequest;
import com.amazonaws.services.polly.model.SynthesizeSpeechResult;
import com.amazonaws.services.polly.model.OutputFormat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AmazonPollyServerSide {
	
	public byte[] Speak()
	{
		String accessKey = "AKIA6KQBFTOBUHSFRX2R";
	    String secretKey = "EMUhv3qgaVWdd2zRxUdlvtihFnrVU505Ew+RgYeI";

	    BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);

	    AmazonPolly polly = AmazonPollyClientBuilder.standard()
	            .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
	            .withRegion(Regions.US_EAST_1) // Change this to your desired region
	            .build();

	    String textToSynthesize = "Welcome TO Human Resource Management System";

	    SynthesizeSpeechRequest synthesizeSpeechRequest = new SynthesizeSpeechRequest()
	            .withText(textToSynthesize)
	            .withVoiceId("Joanna") // Change this to the desired voice
	            .withOutputFormat(OutputFormat.Mp3);

	    SynthesizeSpeechResult synthesizeSpeechResult = polly.synthesizeSpeech(synthesizeSpeechRequest);

	    try (InputStream audioStream = synthesizeSpeechResult.getAudioStream()) {
	        // Convert the MP3 audio to PCM format
	        AudioInputStream mp3Stream = AudioSystem.getAudioInputStream(audioStream);
	        AudioFormat mp3Format = mp3Stream.getFormat();

	        AudioFormat pcmFormat = new AudioFormat(
	                AudioFormat.Encoding.PCM_SIGNED,
	                mp3Format.getSampleRate(),
	                16,
	                mp3Format.getChannels(),
	                mp3Format.getChannels() * 2,
	                mp3Format.getSampleRate(),
	                false
	        );

	        AudioInputStream pcmStream = AudioSystem.getAudioInputStream(pcmFormat, mp3Stream);

	        // Play the PCM audio
//	        DataLine.Info info = new DataLine.Info(SourceDataLine.class, pcmFormat);
//	        try (SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info)) {
//	            line.open(pcmFormat);
//	            line.start();
//
//	            byte[] buffer = new byte[4096];
//	            int bytesRead;
//
//	            while ((bytesRead = pcmStream.read(buffer)) != -1) {
//	                line.write(buffer, 0, bytesRead);
//	            }
//	            
//
//	            line.drain();
//	            line.stop();
//	        }
//
//	        System.out.println("Speech synthesis completed.");
//	    } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
//	        e.printStackTrace();
//	    }
	        DataLine.Info info = new DataLine.Info(SourceDataLine.class, pcmFormat);
	        try (SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info)) {
	            line.open(pcmFormat);
	            line.start();

	            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	            byte[] buffer = new byte[4096];
	            int bytesRead;

	            while ((bytesRead = pcmStream.read(buffer)) != -1) {
	                outputStream.write(buffer, 0, bytesRead);
	            }

	            line.drain();
	            line.stop();

	            System.out.println("Speech synthesis completed.");

	            return outputStream.toByteArray();
	        }

	    } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
    public static void main(String[] args) {
        
    }
}
