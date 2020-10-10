package com.bibleit.bibleitmono.service.reader;


import com.bibleit.bibleitmono.service.reader.voice.BibleitVoice;
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

    @Test
    public void generateQuestionAnswerAudioFile(){

        TextToSpeechServiceWatsonImpl watson = new TextToSpeechServiceWatsonImpl();
        String questionId = "611";
        String answerText ="Do not judge based on appearances, but judge things based on righteousness. ";

        // default
        // 10% speeds it up 10%
        // -10% reduces speed 10%
        String voiceRate = "default";
        boolean returnedBoolean = watson.generateAudioFile(questionId, answerText, voiceRate);

        assertTrue(returnedBoolean);

    }
}