package com.bibleit.bibleitmono.pojo;

public class Verse {
    private String verse;
    private String book;
    private String chapter;
    private String verseNumber;

    public String getVerse() {
        return verse;
    }

    public void setVerse(String verse) {
        this.verse = verse;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public String getVerseNumber() {
        return verseNumber;
    }

    public void setVerseNumber(String verseNumber) {
        this.verseNumber = verseNumber;
    }

    @Override
    public String toString() {
        return "Verse{" +
                "verse='" + verse + '\'' +
                ", book='" + book + '\'' +
                ", chapter='" + chapter + '\'' +
                ", verseNumber='" + verseNumber + '\'' +
                '}';
    }
}
