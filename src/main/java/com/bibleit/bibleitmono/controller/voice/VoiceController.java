package com.bibleit.bibleitmono.controller.voice;

import com.bibleit.bibleitmono.reader.TextToSpeechService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/service")
public class VoiceController {

    @Autowired
    private TextToSpeechService voiceService;

    @GetMapping("/voice")
    public String getVoiceAudio(@RequestParam String answerString){
        return answerString;
    }
}
