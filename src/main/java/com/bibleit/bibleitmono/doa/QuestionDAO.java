package com.bibleit.bibleitmono.doa;

import com.bibleit.bibleitmono.pojo.QuestionAnswer;
import com.bibleit.bibleitmono.pojo.QuestionAnswerImpl;

public interface QuestionDAO {

    QuestionAnswerImpl[] getAll();

    QuestionAnswer getById(String id);
}
