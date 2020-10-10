package com.bibleit.bibleitmono.service.reader;

import com.bibleit.bibleitmono.service.reader.voice.BibleitVoice;

public interface TextToSpeechService {

    BibleitVoice getVoices();
    boolean runSample();
    Object getTextToSpeechObject();

    boolean generateAudioFile(String questionId, String answerText, String voiceRate);
}
