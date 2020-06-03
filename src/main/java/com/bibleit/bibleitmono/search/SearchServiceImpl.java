package com.bibleit.bibleitmono.search;

import com.bibleit.bibleitmono.comparer.KeywordCompare;
import com.bibleit.bibleitmono.enums.QuestionType;
import com.bibleit.bibleitmono.pojo.QuestionAnswer;
import com.bibleit.bibleitmono.pojo.QuestionAnswerImpl;
import com.bibleit.bibleitmono.question.QuestionRetrievalService;
import com.bibleit.bibleitmono.remover.ElementRemover;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class SearchServiceImpl implements SearchService {

    @Autowired
    private QuestionRetrievalService questions;
    @Autowired
    private KeywordCompare keywordCompare;
    @Autowired
    private ElementRemover remover;

    @Override
    public List<QuestionAnswer> getBestMatched(String userInput, QuestionType type) {
        // get questions
        QuestionAnswerImpl[] questionList = questions.getAll();
        //compare similar keywords
        List<QuestionAnswerImpl> scoredQuestions = keywordCompare.getListOfScored(questionList, userInput, type);

        Collections.sort(scoredQuestions);

        // return 10 questions
        int removeAmount = 10;
        List<QuestionAnswer> finalQuestions = remover.removeFromList(scoredQuestions, removeAmount);

        return finalQuestions;
    }
}
