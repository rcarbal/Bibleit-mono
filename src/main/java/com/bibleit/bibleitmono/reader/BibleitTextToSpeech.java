package com.bibleit.bibleitmono.reader;

import com.bibleit.bibleitmono.reader.voice.BibleitVoice;

public interface BibleitTextToSpeech {

    BibleitVoice getVoices();
    void runSample();
    void authentication();
}
