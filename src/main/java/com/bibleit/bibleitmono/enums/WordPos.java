package com.bibleit.bibleitmono.enums;

public enum WordPos {
    NOUN("noun"),
    VERB("verb"),
    ADJECTIVE("adj"),
    SYNONYMS("synonyms");

    private String pendingValue;

    private WordPos(String pendingValue){
        this.pendingValue = pendingValue;
    }

    public String getPosValue(){
        return pendingValue;
    }

}
