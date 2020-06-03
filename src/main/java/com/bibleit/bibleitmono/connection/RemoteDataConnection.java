package com.bibleit.bibleitmono.connection;

import com.bibleit.bibleitmono.pojo.QuestionAnswerImpl;
import org.springframework.stereotype.Component;

@Component
public interface RemoteDataConnection {
    QuestionAnswerImpl[] getAllResponse();
}
