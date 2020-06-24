package com.bibleit.bibleitmono.pojo;

import java.util.ArrayList;
import java.util.List;

public class Verses {

    List<String> verses = new ArrayList<>();

    public List<String> getVerses() {
        return verses;
    }

    public void setVerses(List<String> verses) {
        this.verses = verses;
    }

    @Override
    public String toString() {
        return "Verses{" +
                "verses=" + verses +
                '}';
    }
}
