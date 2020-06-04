package com.bibleit.bibleitmono.search;

import com.bibleit.bibleitmono.comparer.KeywordSimularityMatcher;
import com.bibleit.bibleitmono.enums.QuestionType;
import com.bibleit.bibleitmono.pojo.QuestionAnswer;
import com.bibleit.bibleitmono.pojo.QuestionAnswerImpl;
import com.bibleit.bibleitmono.question.QuestionRetrievalService;
import com.bibleit.bibleitmono.remover.ElementRemover;
import com.bibleit.bibleitmono.sorting.AlgorithmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class SearchServiceImpl implements SearchService {

    @Autowired
    private QuestionRetrievalService questionsRetrieveService;
    @Autowired
    private KeywordSimularityMatcher similarityMatcherService;
    @Autowired
    private AlgorithmService algoService;
    @Autowired
    private ElementRemover remover;

    @Override
    public List<QuestionAnswer> getBestMatched(String userInput, QuestionType type) {
        // get questions
        QuestionAnswerImpl[] questionList = questionsRetrieveService.getAll();
        //compare similar keywords
        QuestionAnswer[] scoredQuestions = similarityMatcherService.getListOfScored(questionList, userInput, type);
        // Find Score for types of words
        QuestionAnswer[] sortedArr = algoService.sortUsingInput(scoredQuestions);

        // return 10 questions
        int removeAmount = 10;
        List<QuestionAnswer> finalQuestions = remover.removeFromList(Arrays.asList(sortedArr), removeAmount);

        return finalQuestions;
    }
}