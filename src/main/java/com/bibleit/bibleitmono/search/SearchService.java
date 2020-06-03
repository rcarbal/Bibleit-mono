package com.bibleit.bibleitmono.search;

import com.bibleit.bibleitmono.enums.QuestionType;
import com.bibleit.bibleitmono.pojo.QuestionAnswer;

import java.util.List;

public interface SearchService {
    List<QuestionAnswer> getBestMatched(String userInput, QuestionType type);
}
