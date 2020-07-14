package com.bibleit.bibleitmono.pojo;

public class PaymentResponseImpl implements PaymentResponse {
    private String clientSecretId;


    @Override
    public void addClientId(String id) {
        clientSecretId = id;
    }

    public String getClientSecretId() {
        return clientSecretId;
    }
}
