package com.bibleit.bibleitmono.pojo;

import java.util.ArrayList;
import java.util.List;

public class VersesContainer {

    List<Verse> verses = new ArrayList<>();

    public List<Verse> getVerses() {
        return verses;
    }

    public void setVerses(List<Verse> verses) {
        this.verses = verses;
    }

    @Override
    public String toString() {
        return "Verses{" +
                "verses=" + verses +
                '}';
    }
}
