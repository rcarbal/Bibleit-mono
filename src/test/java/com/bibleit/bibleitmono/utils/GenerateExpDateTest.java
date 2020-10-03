package com.bibleit.bibleitmono.utils;

import com.bibleit.bibleitmono.enums.ExpirationTime;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class GenerateExpDateTest {

    @Test
    public void generateExpirationDate(){
        Map<String, String> env = System.getenv();

        String id = "sample.wav";
        String bucketLocation = env.get("S3_BUCKET_NAME");
        long timeUntilExp = ExpirationTime.FIFTEEN_MINUTES.getExpirationTime();

        Date expirationDate = GenerateExpDate.getExpirationDate(id, bucketLocation, timeUntilExp);

        assertNotNull(expirationDate);
    }

}