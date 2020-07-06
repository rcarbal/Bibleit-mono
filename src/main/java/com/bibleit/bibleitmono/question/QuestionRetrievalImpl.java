package com.bibleit.bibleitmono.question;

import com.bibleit.bibleitmono.doa.QuestionDAO;
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
}
