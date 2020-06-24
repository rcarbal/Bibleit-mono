package com.bibleit.bibleitmono.utils;

import com.bibleit.bibleitmono.pojo.QuestionAnswer;

import java.util.List;

public interface VerseExtractor {
    List<QuestionAnswer> getVerses(List<QuestionAnswer> finalQuestions);
}
