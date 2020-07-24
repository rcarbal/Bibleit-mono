package com.bibleit.bibleitmono.dao;

import com.bibleit.bibleitmono.pojo.QuestionAnswer;
import com.bibleit.bibleitmono.pojo.QuestionAnswerImpl;

public interface QuestionDAO {

    QuestionAnswerImpl[] getAll();

    QuestionAnswer getById(String id);

    boolean refreshData();
}
