package com.bibleit.bibleitmono.reader;


import com.bibleit.bibleitmono.reader.voice.BibleitVoice;
import com.ibm.watson.text_to_speech.v1.TextToSpeech;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BibleitTextToSpeechWatsonTest {

    @Test
    public void getVoices(){
        TextToSpeechServiceWatsonImpl watson = new TextToSpeechServiceWatsonImpl();
        BibleitVoice voices = watson.getVoices();
        System.out.println();
    }

    @Test
    public void getWatsonTextToSpeechObject(){
        TextToSpeechServiceWatsonImpl watson = new TextToSpeechServiceWatsonImpl();
        TextToSpeech textToSpeech =  (TextToSpeech) watson.getTextToSpeechObject();
        assertNotNull(textToSpeech);
    }

    @Test
    public void runSampleHelloWorldCode(){
        TextToSpeechServiceWatsonImpl watson = new TextToSpeechServiceWatsonImpl();
        boolean returnedBoolean = watson.runSample();

        assertTrue(returnedBoolean);
    }
}