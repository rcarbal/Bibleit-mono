package com.bibleit.bibleitmono.utils;

import org.json.simple.JSONObject;

public interface JSONObjectParser {
    
    String[] getBooks();
    JSONObject getBook(String book, JSONObject parsedBibleJSON);
}
