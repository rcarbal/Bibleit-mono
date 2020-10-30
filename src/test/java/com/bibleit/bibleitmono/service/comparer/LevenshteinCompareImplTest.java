package com.bibleit.bibleitmono.service.comparer;

import com.bibleit.bibleitmono.enums.QuestionType;
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

        String userString = "what happens if I believe in Jesus Christ?";


        algo.getExactMatch(all, userString, QuestionType.QUESTION);
        System.out.println();
    }

}