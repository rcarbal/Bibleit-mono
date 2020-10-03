package com.bibleit.bibleitmono.utils;

import java.util.Date;

public class GenerateExpDate {


    public static Date getExpirationDate(String audioId, String bucketLocation, long timeUntilExp) {

        // get values
        Date expDate = new Date();
        long currentTimeMillis = expDate.getTime();

        // alter values
        currentTimeMillis += timeUntilExp;
        expDate.setTime(currentTimeMillis);

        return expDate;
    }
}
