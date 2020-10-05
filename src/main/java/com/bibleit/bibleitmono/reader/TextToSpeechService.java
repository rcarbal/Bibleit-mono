package com.bibleit.bibleitmono.reader;

import com.bibleit.bibleitmono.reader.voice.BibleitVoice;

public interface TextToSpeechService {

    BibleitVoice getVoices();
    boolean runSample();
    Object getTextToSpeechObject();

    boolean generateAudioFile(String questionId, String answerText, String voiceRate);
}
