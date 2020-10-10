package com.bibleit.bibleitmono.repository.connection;

import com.bibleit.bibleitmono.pojo.QuestionAnswerImpl;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Date;

@Component
public interface RemoteDataConnection {
    QuestionAnswerImpl[] getAllResponse();

    boolean setConnection();

    Object getRemoteDataObject(String questinId);

    URL getSignedURL(String objectKey, String storageLocationName, Date expirationDate);
}
