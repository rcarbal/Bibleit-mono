package com.bibleit.bibleitmono.comparer;

import com.bibleit.bibleitmono.enums.QuestionType;
import com.bibleit.bibleitmono.pojo.CompareData;
import com.bibleit.bibleitmono.pojo.QuestionAnswerImpl;

import java.util.List;

public interface KeywordCompare {
    CompareData getWordScore(String input, String keywords);
    List<QuestionAnswerImpl> getListOfScored(QuestionAnswerImpl[] array, String userInput, QuestionType type);
}
