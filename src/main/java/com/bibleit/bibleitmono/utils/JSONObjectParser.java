package com.bibleit.bibleitmono.utils;

import com.bibleit.bibleitmono.pojo.VersesContainer;
import org.json.simple.JSONObject;

public interface JSONObjectParser {
    
    String[] getBooks();
    JSONObject getBook(String book, JSONObject parsedBibleJSON);
    VersesContainer getVerse(String book, String chapter, String verse, JSONObject parsedBibleJSON);

    VersesContainer getVerses(String book, String chapter, String startVerse, String endVerse, JSONObject parsedBibleJSON);
}
