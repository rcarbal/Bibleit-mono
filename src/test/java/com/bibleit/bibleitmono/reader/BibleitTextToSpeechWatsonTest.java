package com.bibleit.bibleitmono.reader;


import com.bibleit.bibleitmono.reader.voice.BibleitVoice;
import com.ibm.watson.text_to_speech.v1.TextToSpeech;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class BibleitTextToSpeechWatsonTest {

    @Test
    public void getVoices(){
        BibleitTextToSpeechWatson watson = new BibleitTextToSpeechWatson();
        BibleitVoice voices = watson.getVoices();
        System.out.println();
    }

    @Test
    public void getWatsonTextToSpeechObject(){
        BibleitTextToSpeechWatson watson = new BibleitTextToSpeechWatson();
        TextToSpeech textToSpeech =  (TextToSpeech) watson.getTextToSpeechObject();
        assertNotNull(textToSpeech);
    }
}