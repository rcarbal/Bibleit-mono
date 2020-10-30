package com.bibleit.bibleitmono.service.comparer;

import com.bibleit.bibleitmono.enums.QuestionType;
import com.bibleit.bibleitmono.pojo.QuestionAnswer;
import com.bibleit.bibleitmono.pojo.QuestionAnswerImpl;

public interface KeywordSimilarityMatcher {
    QuestionAnswer[] getListOfScored(QuestionAnswer[] array, String userInput, QuestionType type);

    void getExactMatch(QuestionAnswerImpl[] questions, String userString, QuestionType question);
}
