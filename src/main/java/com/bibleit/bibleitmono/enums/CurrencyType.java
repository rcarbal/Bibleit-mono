package com.bibleit.bibleitmono.enums;

public enum CurrencyType {
    USD("usd");

    private String currencyType;

    private CurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    public String getValue(){
        return currencyType;
    }
}
