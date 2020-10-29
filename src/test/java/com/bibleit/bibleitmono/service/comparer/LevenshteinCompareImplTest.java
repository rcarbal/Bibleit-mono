package com.bibleit.bibleitmono.service.comparer;

import com.bibleit.bibleitmono.pojo.QuestionAnswerImpl;
import com.bibleit.bibleitmono.service.question.QuestionRetrievalService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LevenshteinCompareImplTest {

    @Autowired
    QuestionRetrievalService question;

    @Test
    public void get100percentOfWordsMatched(){

        QuestionAnswerImpl[] all = question.getAll();


        LevenshteinCompareImpl algo = new LevenshteinCompareImpl();
        System.out.println();
    }

}