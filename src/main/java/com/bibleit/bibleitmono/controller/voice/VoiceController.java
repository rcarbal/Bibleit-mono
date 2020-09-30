package com.bibleit.bibleitmono.controller.voice;

import com.bibleit.bibleitmono.connection.RemoteDataConnection;
import com.bibleit.bibleitmono.reader.TextToSpeechService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class VoiceController {

    @Autowired
    private RemoteDataConnection dataConnection;

    @Autowired
    private TextToSpeechService voiceService;

    @GetMapping("/voice/getAudio")
    public String getVoiceAudio(@RequestParam String audioId){

        // need to check if audio file is in S3
        Object answerAudioFile = dataConnection.getRemoteDataObject(audioId);


        return audioId;
    }
}
