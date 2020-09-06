package com.bibleit.bibleitmono.enums;

public enum CookieReader {
    NO("no"),
    YES("yes");

    private String pendingValue;

    private CookieReader(String pendingValue) {
        this.pendingValue = pendingValue;
    }

    public String getValue() {
        return pendingValue;
    }
}
