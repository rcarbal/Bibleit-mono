package com.bibleit.bibleitmono.doa;

import com.bibleit.bibleitmono.connection.RemoteDataConnection;
import com.bibleit.bibleitmono.pojo.QuestionAnswer;
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

    @Override
    public QuestionAnswer getById(String id) {
        QuestionAnswerImpl[] allQuestions = remoteConnection.getAllResponse();

        for (QuestionAnswer questionAnswer: allQuestions){
            if (questionAnswer.getId() == Integer.parseInt(id)){
                return questionAnswer;
            }
        }
        return null;
    }

    @Override
    public boolean refreshData() {
        return remoteConnection.setConnection();
    }
}
