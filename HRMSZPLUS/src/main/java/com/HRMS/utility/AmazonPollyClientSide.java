package com.HRMS.utility;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.polly.AmazonPolly;
import com.amazonaws.services.polly.AmazonPollyClientBuilder;
import com.amazonaws.services.polly.model.SynthesizeSpeechRequest;
import com.amazonaws.services.polly.model.SynthesizeSpeechResult;
import com.amazonaws.services.polly.model.OutputFormat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class AmazonPollyClientSide {
    public static void main(String[] args) {
    	String accessKey = "AKIA6KQBFTOBUHSFRX2R";
	    String secretKey = "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";

        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);

        AmazonPolly polly = AmazonPollyClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(Regions.US_EAST_1) // Change this to your desired region
                .build();

        String textToSynthesize = "Please Enter OTP Sent To Your Mail Address";

        SynthesizeSpeechRequest synthesizeSpeechRequest = new SynthesizeSpeechRequest()
                .withText(textToSynthesize)
                .withVoiceId("Joanna") // Change this to the desired voice
                .withOutputFormat(OutputFormat.Mp3);

        SynthesizeSpeechResult synthesizeSpeechResult = polly.synthesizeSpeech(synthesizeSpeechRequest);

        try (InputStream audioStream = synthesizeSpeechResult.getAudioStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;

            try (FileOutputStream outputStream = new FileOutputStream(new File("output.mp3"))) {
                while ((bytesRead = audioStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }

            System.out.println("Speech synthesis completed.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
