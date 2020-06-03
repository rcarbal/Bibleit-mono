package com.bibleit.bibleitmono.test.connection;

import com.bibleit.bibleitmono.connection.RemoteDataConnection;
import com.bibleit.bibleitmono.connection.S3RemoteDataConnection;
import com.bibleit.bibleitmono.pojo.QuestionAnswerImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class S3RemoteDataConnectionTest {

    @Test
    public void getAllTheQuestionsFromBucket(){
        RemoteDataConnection connection = new S3RemoteDataConnection();
        QuestionAnswerImpl[] allQuestions = connection.getAllResponse();

        assertTrue(allQuestions.length > 0);
    }
}