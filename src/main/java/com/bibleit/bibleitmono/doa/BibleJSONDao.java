package com.bibleit.bibleitmono.doa;


import com.bibleit.bibleitmono.pojo.Verses;
import org.json.simple.JSONObject;

public interface BibleJSONDao {
    JSONObject getBible();
    String[] getBooks();
    JSONObject getBook(String bookName);
    Verses getVerse(String book, String chapter, String verse);

    Verses getVerses(String book, String chapter, String startVerse, String endVerse);
}
