package com.bibleit.bibleitmono.comparer;

import com.bibleit.bibleitmono.pojo.QuestionAnswer;

public interface ScoreWordTypeService {

    QuestionAnswer[] getScore(QuestionAnswer[] fromData, String fromUser);
}
