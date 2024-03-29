package com.bibleit.bibleitmono.service.question;

import com.bibleit.bibleitmono.dao.QuestionDAO;
import com.bibleit.bibleitmono.pojo.QuestionAnswer;
import com.bibleit.bibleitmono.pojo.QuestionAnswerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuestionRetrievalImpl implements QuestionRetrievalService{

    @Autowired
    private QuestionDAO dao;

    @Override
    public QuestionAnswerImpl[] getAll() {
        return dao.getAll();
    }

    @Override
    public QuestionAnswer getQuestionById(String id) {
        return dao.getById(id);
    }

    @Override
    public boolean refreshQuestions() {
        return dao.refreshData();
    }
}
