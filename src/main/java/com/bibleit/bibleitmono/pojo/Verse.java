package com.bibleit.bibleitmono.pojo;

public class Verse {
    private String verse;
    private String bibleLocation;

    public String getVerse() {
        return verse;
    }

    public void setVerse(String verse) {
        this.verse = verse;
    }

    public String getBibleLocation() {
        return bibleLocation;
    }

    public void setBibleLocation(String bibleLocation) {
        this.bibleLocation = bibleLocation;
    }

    @Override
    public String toString() {
        return "Verse{" +
                "verse='" + verse + '\'' +
                ", bibleLocation='" + bibleLocation + '\'' +
                '}';
    }
}
