package com.bibleit.bibleitmono.question;

import com.bibleit.bibleitmono.pojo.QuestionAnswer;
import com.bibleit.bibleitmono.pojo.QuestionAnswerImpl;

public interface QuestionRetrievalService {

    QuestionAnswerImpl[] getAll();
    QuestionAnswer getQuestionById(String id);
}
