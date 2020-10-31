package com.bibleit.bibleitmono.dao.thesaurus;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.Map;

public interface ThesaurusReaderDAO {

    Map<String, JsonObject> getAll();
    void getWordInfo();
    JsonArray getAllSynonymsOfWord(String word);
    JsonArray getAllSynonymsOfWord(String word, String synonymFormat);

}
