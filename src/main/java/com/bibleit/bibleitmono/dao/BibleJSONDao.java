package com.bibleit.bibleitmono.dao;


import com.bibleit.bibleitmono.pojo.VersesContainer;
import org.json.simple.JSONObject;

public interface BibleJSONDao {
    JSONObject getBible();
    String[] getBooks();
    JSONObject getBook(String bookName);
    VersesContainer getVerse(String book, String chapter, String verse);

    VersesContainer getVerses(String book, String chapter, String startVerse, String endVerse);
}
