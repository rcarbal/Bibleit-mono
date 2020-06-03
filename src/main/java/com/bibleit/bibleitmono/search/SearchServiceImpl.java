package com.bibleit.bibleitmono.search;

import com.bibleit.bibleitmono.enums.QuestionType;
import com.bibleit.bibleitmono.pojo.QuestionAnswer;
import com.bibleit.bibleitmono.pojo.QuestionAnswerImpl;
import com.bibleit.bibleitmono.question.QuestionRetrievalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class SearchServiceImpl implements SearchService {

    @Autowired
    private QuestionRetrievalService questions;

    @Override
    public List<QuestionAnswer> getBestMatched(String userInput, QuestionType answer) {
        // get questions
        QuestionAnswerImpl[] questionList = questions.getAll();
        //sort
        return Arrays.asList(questionList);
    }
}
