package com.bibleit.bibleitmono.doa;


import com.bibleit.bibleitmono.utils.JSONObjectParser;
import org.json.simple.JSONObject;

public interface BibleJSONDao {
    JSONObject getBible();
    String[] getBooks();
    JSONObject getBook(String bookName);
    String getVerse(String book, String chapter, String verse);

    JSONObjectParser getVerses(String book, String chapter, String startVerse, String endVerse);
}
