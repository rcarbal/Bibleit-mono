package com.bibleit.bibleitmono.enums;

public enum ExpirationTime {

    ONE_MINUTE(6000L),
    FIFTEEN_MINUTES(900000L),
    THIRTY_MINUTES(1800000L);

    private long expirationTime;

    private ExpirationTime(long expirationTime){
        this.expirationTime = expirationTime;
    }

    public long getExpirationTime(){
        return expirationTime;
    }
}
