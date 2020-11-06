package com.bibleit.bibleitmono.service.generator;

import com.google.gson.JsonArray;

import java.util.List;
import java.util.Map;

public interface SentenceGeneratorService {
    List<String> getSentences(Map<String, JsonArray> sentenceSynonyms);
}
