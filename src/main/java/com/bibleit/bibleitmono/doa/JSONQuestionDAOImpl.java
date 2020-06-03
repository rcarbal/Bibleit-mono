package com.bibleit.bibleitmono.doa;

import com.bibleit.bibleitmono.connection.RemoteDataConnection;
import com.bibleit.bibleitmono.pojo.QuestionAnswerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JSONQuestionDAOImpl implements QuestionDAO{
    @Autowired
    private RemoteDataConnection remoteConnection;

    @Override
    public QuestionAnswerImpl[] getAll() {
        QuestionAnswerImpl[] allQuestions = remoteConnection.getAllResponse();

        return allQuestions;
    }
}
