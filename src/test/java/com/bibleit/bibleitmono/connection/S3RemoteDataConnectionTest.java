package com.bibleit.bibleitmono.connection;


import com.amazonaws.services.s3.model.S3Object;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

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
}