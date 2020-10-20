package com.bibleit.bibleitmono.service.thesaurus;

import com.google.gson.JsonObject;

import java.util.Map;

public interface ThesaurusReaderDAO {

    Map<String, JsonObject> getAll();
    void getWordInfo();
    void getWordSynonyms();

}
