package com.bibleit.bibleitmono.comparer;

import com.bibleit.bibleitmono.enums.QuestionType;
import com.bibleit.bibleitmono.pojo.QuestionAnswer;
import com.bibleit.bibleitmono.pojo.QuestionAnswerImpl;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LevenshteinCompareImpl implements KeywordSimularityMatcher {

    private List<QuestionAnswer> scoredArr;
    private String[] splitFromUser;
    private double currentScore = 0.0;
    private String dataStringFromArr;
    private QuestionAnswerImpl questionTemp = null;
    private String[] splitData;
    private String inputStingToCompare;
    private LevenshteinDistance distance = new LevenshteinDistance();
    private String matchedString;
    @Autowired
    private KeywordSimularityMatcher keywordCompare;


    @Override
    public QuestionAnswer[] getListOfScored(QuestionAnswer[] array, String userInput, QuestionType type) {

        double highestScore = 0.0;
        // set split for user input
        scoredArr = new ArrayList<>();
        splitFromUser = userInput.split("\\s+");

        // loop throught all the data
        for (int i=0; i < array.length; i++){
            matchedString = "";
            currentScore = 0.0;
            // get the current question as an array
            dataStringFromArr = ((QuestionAnswerImpl)array[i]).getQuestion();
            questionTemp = (QuestionAnswerImpl) array[i];

            if (questionTemp.getQuestion() == ""){
                continue;
            }
            splitData = questionTemp.getQuestion().split("\\s+");


            for (int x = 0; x < splitFromUser.length; x++){

                // parsing through all the indexes of users input
                for (int y=0; y < splitData.length; y++){
                    inputStingToCompare = userInput;
                    currentScore +=  getScoreByIndex(x, y);
                }
            }
            // add to scoredArr
            QuestionAnswerImpl question = (QuestionAnswerImpl) array[i];
            question.setScore(currentScore);
            if (currentScore > highestScore){
                highestScore = currentScore;
            }
            if (currentScore > highestScore / 2){
                question.setMatches(matchedString);
                scoredArr.add(question);
            }
        }

        return scoredArr.toArray(new QuestionAnswer[0]);
    }

    private double getScoreByIndex(int x, int y) {
        int returnedScore = 0;

        // remove "?"
        String fromUser = splitFromUser[x].replace("?", "");
        fromUser = fromUser.replace("\"", "");
        fromUser = fromUser.replace(".", "");
        String fromData = splitData[y].replace("?", "");
        fromData = fromData.replace("\"","");
        fromData = fromData.replace(".", "");

        // check uppercase
        boolean checkUser = Character.isUpperCase(fromUser.charAt(0));
        boolean checkData = false;

        try {
            checkData = Character.isUpperCase(fromData.charAt(0));
        }catch (Exception e){
            System.out.println(e);
        }


        // compare index of fromUser to from data
        double score = distance.apply(fromUser.toLowerCase(), fromData.toLowerCase());

        // use this bool to decide wether to add
        boolean matched = false;

        if (score < 1.6){
            if (score == 0.0){

                if (checkUser && checkData){
                    returnedScore += 30;
                }
                returnedScore += 45;
            }
            returnedScore += 2;
            matched = true;
        }

        if (y == x && score <= 1){

            if (y == 0.0 && score < 1){
                returnedScore += 30;
            }
            returnedScore += 5;
            matched = true;

        }
        if (matched){
            matchedString = matchedString + fromUser + ":" + fromData + " " + ":" + returnedScore + " ";
        }
        return returnedScore;
    }

}
