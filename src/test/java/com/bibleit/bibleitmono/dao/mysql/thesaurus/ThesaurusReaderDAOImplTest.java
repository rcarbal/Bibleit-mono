package com.bibleit.bibleitmono.dao.mysql.thesaurus;


import com.bibleit.bibleitmono.dao.thesaurus.ThesaurusReaderDAOImpl;
import com.bibleit.bibleitmono.enums.WordPos;
import com.google.gson.JsonArray;
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

    @Test
    public void getWordSynonyms(){
        ThesaurusReaderDAOImpl thesaurus = new ThesaurusReaderDAOImpl();
        String word = "the";
        JsonArray arrayOfSynonyms = thesaurus.getAllSynonymsOfWord(word);

        assertTrue(arrayOfSynonyms.size() > 0);
    }

    @Test
    public void getWordSynonymsSingleType(){
        ThesaurusReaderDAOImpl thesaurus = new ThesaurusReaderDAOImpl();
        String word = "sin";
        String synonymType = WordPos.SINGLE.getPosValue();
        JsonArray arrayOfSynonyms = thesaurus.getAllSynonymsOfWord(word, synonymType);

        System.out.println(arrayOfSynonyms);

        assertTrue(arrayOfSynonyms.size() > 0);
    }

    @Test
    public void generateSentencesWithSynonyms(){

    }


}