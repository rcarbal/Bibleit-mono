package com.bibleit.bibleitmono.enums;

public enum PaymentStatus {
    PENDING("pending"),
    COMPLETE("complete");

    private String pendingValue;

    private PaymentStatus(String pendingValue) {
        this.pendingValue = pendingValue;
    }

    public String getStatusValue(){
        return pendingValue;
    }
}
