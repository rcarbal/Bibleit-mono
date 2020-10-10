package com.bibleit.bibleitmono.service.comparer;

import com.bibleit.bibleitmono.enums.QuestionType;
import com.bibleit.bibleitmono.pojo.QuestionAnswer;

public interface KeywordSimilarityMatcher {
    QuestionAnswer[] getListOfScored(QuestionAnswer[] array, String userInput, QuestionType type);
}
