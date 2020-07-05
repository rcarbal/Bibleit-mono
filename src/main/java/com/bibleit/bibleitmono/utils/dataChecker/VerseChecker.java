package com.bibleit.bibleitmono.utils.dataChecker;

import com.bibleit.bibleitmono.pojo.QuestionAnswer;

import java.util.List;

public interface VerseChecker {
    List<Integer> checkAllVerses();

    List<QuestionAnswer> removeBadVerse(List<QuestionAnswer> questionsWithVerses);
}
