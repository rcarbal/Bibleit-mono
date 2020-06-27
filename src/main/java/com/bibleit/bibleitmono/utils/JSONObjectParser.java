package com.bibleit.bibleitmono.utils;

import org.json.simple.JSONObject;

public interface JSONObjectParser {
    
    String[] getBooks();
    JSONObject getBook(String book, JSONObject parsedBibleJSON);
    String getVerse(String book, String chapter, String verse, JSONObject parsedBibleJSON);

    JSONObjectParser getVerses(String book, String chapter, String startVerse, String endVerse, JSONObject parsedBibleJSON);
}
