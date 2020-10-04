package com.bibleit.bibleitmono.controller.voice;

import com.bibleit.bibleitmono.connection.RemoteDataConnection;
import com.bibleit.bibleitmono.enums.ExpirationTime;
import com.bibleit.bibleitmono.reader.TextToSpeechService;
import com.bibleit.bibleitmono.utils.GenerateExpDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URL;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class VoiceController {

    private Map<String, String> env = System.getenv();

    @Autowired
    private RemoteDataConnection dataConnection;

    @Autowired
    private TextToSpeechService voiceService;

    @GetMapping("/voice/getAudio")
    public String getVoiceAudio(@RequestParam String audioId){

        audioId = audioId + ".wav";

        // check if object exists
        Object remoteDataObject = dataConnection.getRemoteDataObject(audioId);

        if (remoteDataObject != null){
            String bucketLocation = env.get("S3_BUCKET_NAME");
            long timeUntilExp = ExpirationTime.FIFTEEN_MINUTES.getExpirationTime();

            Date expDate = GenerateExpDate.getExpirationDate(audioId, bucketLocation, timeUntilExp);
            URL signedURL = dataConnection.getSignedURL(audioId, bucketLocation, expDate);

            return signedURL.toString();
        }

        return "null";
    }
}
