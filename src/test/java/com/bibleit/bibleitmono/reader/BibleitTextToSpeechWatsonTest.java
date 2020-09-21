package com.bibleit.bibleitmono.reader;


import com.bibleit.bibleitmono.reader.voice.BibleitVoice;
import org.junit.jupiter.api.Test;

class BibleitTextToSpeechWatsonTest {

    @Test
    public void getVoices(){
        BibleitTextToSpeechWatson watson = new BibleitTextToSpeechWatson();
        BibleitVoice voices = watson.getVoices();
        System.out.println();
    }

    @Test
    public void testAuthentication(){
        BibleitTextToSpeechWatson watson = new BibleitTextToSpeechWatson();
        watson.sdkManagedTokenAuthentication();
    }
}