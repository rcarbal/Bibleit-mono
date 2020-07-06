package com.bibleit.bibleitmono.utils;

import com.bibleit.bibleitmono.pojo.QuestionAnswer;
import com.bibleit.bibleitmono.pojo.Verse;

import java.util.List;

public interface VerseExtractor {
    List<QuestionAnswer> getVerses(List<QuestionAnswer> finalQuestions);

    Verse getVerseLocationParams(String verseLocation);

    List<QuestionAnswer> removeBadVerse(List<QuestionAnswer> questionsWithVerses);

    QuestionAnswer getVerseFromQuestion(QuestionAnswer questionById);
}
