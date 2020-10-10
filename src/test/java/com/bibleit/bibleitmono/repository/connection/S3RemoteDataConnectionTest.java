package com.bibleit.bibleitmono.repository.connection;


import com.amazonaws.services.s3.model.S3Object;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class S3RemoteDataConnectionTest {

    @Test
    public void getFileFromS3(){
        S3RemoteDataConnection connection = new S3RemoteDataConnection();
        connection.setConnection();
        S3Object waveFile = (S3Object) connection.getRemoteDataObject("sample");

        InputStream sampleAudio = waveFile.getObjectContent();
        assertNotNull(sampleAudio);
    }

    @Test
    public void getSignedUrl(){
        Map<String, String> env = System.getenv();

        S3RemoteDataConnection connection = new S3RemoteDataConnection();
        connection.setConnection();

        String objectKey = "sample.wav";
        String bucketName = env.get("S3_BUCKET_NAME");

        // ten seconds from date time
        Date expirationDate = new Date();
        long expTimeMillis = expirationDate.getTime();

        // add 10 seconds
        expTimeMillis += 1000 * 60;
        expirationDate.setTime(expTimeMillis);

        URL signedURL = connection.getSignedURL(objectKey, bucketName, expirationDate);

        System.out.println(signedURL);

        assertNotNull(signedURL);
    }
}