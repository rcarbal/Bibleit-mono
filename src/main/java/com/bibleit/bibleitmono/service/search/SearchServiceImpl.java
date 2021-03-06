package com.bibleit.bibleitmono.service.search;

import com.bibleit.bibleitmono.service.comparer.KeywordSimilarityMatcher;
import com.bibleit.bibleitmono.enums.QuestionType;
import com.bibleit.bibleitmono.pojo.QuestionAnswer;
import com.bibleit.bibleitmono.pojo.QuestionAnswerImpl;
import com.bibleit.bibleitmono.service.question.QuestionRetrievalService;
import com.bibleit.bibleitmono.service.remover.ElementRemover;
import com.bibleit.bibleitmono.service.sorting.AlgorithmService;
import com.bibleit.bibleitmono.utils.VerseExtractor;
import com.bibleit.bibleitmono.utils.dataChecker.VerseChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class SearchServiceImpl implements SearchService {

    @Autowired
    private QuestionRetrievalService questionsRetrieveService;
    @Autowired
    private KeywordSimilarityMatcher similarityMatcherService;
    @Autowired
    private AlgorithmService algoService;
    @Autowired
    private ElementRemover remover;
    @Autowired
    private VerseExtractor verseExtractor;
    @Autowired
    private VerseChecker verseChecker;

    @Override
    public List<QuestionAnswer> getBestMatched(String userInput, QuestionType type) {

        // get questions
        QuestionAnswerImpl[] allQuestionList = questionsRetrieveService.getAll();
        //compare similar keywords
        QuestionAnswer[] scoredQuestions = similarityMatcherService.getListOfScored(allQuestionList, userInput, type);
        // Find Score for types of words
        QuestionAnswer[] sortedArr = algoService.sortUsingInput(scoredQuestions);

        // return 10 questions
        int removeAmount = 10;
        List<QuestionAnswer> finalQuestions = remover.removeFromList(Arrays.asList(sortedArr), removeAmount);

        // add verses to the QuestionAnswer POJO
        List<QuestionAnswer> questionsWithVerses = verseExtractor.getVerses(finalQuestions);
        List<QuestionAnswer> questionRemovedBad = verseChecker.removeBadVerse(questionsWithVerses);

        return questionRemovedBad;
    }
}
