package com.bibleit.bibleitmono.service.thesaurus;


import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;


class ThesaurusReaderDAOImplTest {

    @Test
    public void getAllWordsFromThesaurus(){

        ThesaurusReaderDAOImpl thesaurus = new ThesaurusReaderDAOImpl();
        Map<String, JsonObject> allWords = thesaurus.getAll();

        assertTrue(allWords.size() > 0);
    }


}