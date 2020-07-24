package com.bibleit.bibleitmono.utils.dataChecker;

import com.bibleit.bibleitmono.dao.QuestionDAO;
import com.bibleit.bibleitmono.pojo.QuestionAnswer;
import com.bibleit.bibleitmono.pojo.QuestionAnswerImpl;
import com.bibleit.bibleitmono.pojo.Verse;
import com.bibleit.bibleitmono.utils.VerseExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VerseCheckerImpl implements VerseChecker{

    @Autowired
    QuestionDAO questionDAO;
    @Autowired
    private VerseExtractor verseExtractor;

    @Override
    public List<Integer> checkAllVerses() {
        List<Integer> listOfBadVerses = new ArrayList<>();

        // get all verses
        QuestionAnswerImpl[] allQuestion = questionDAO.getAll();

        // loop through all verse and grab locations
        for (QuestionAnswer questionAnswer : allQuestion){

            String[] verses = questionAnswer.getVerses();

            // loop through array of verse locations
            for (String verseLocation : verses){
                Verse versePojo = verseExtractor.getVerseLocationParams(verseLocation);

                if (versePojo.getBook() == null ||
                        versePojo.getChapter() == null ||
                        versePojo.getVerseNumber() == null){
                    listOfBadVerses.add(questionAnswer.getId());
                }
                else {
                    System.out.println();
                }
            }
        }

        return listOfBadVerses;
    }

    @Override
    public List<QuestionAnswer> removeBadVerse(List<QuestionAnswer> questionsWithVerses) {

        List<QuestionAnswer> returnedList = new ArrayList<>();

        for (QuestionAnswer question : questionsWithVerses){
            String[] verses = question.getVerses();

            boolean allGood = false;

            for (String verse : verses){
                Verse verseLocationParams = verseExtractor.getVerseLocationParams(verse);

                allGood = checkVerse(verseLocationParams);
            }
            if (allGood){
                returnedList.add(question);
            }

        }
        return returnedList;
    }

    private boolean checkVerse(Verse verseLocationParams) {
        if (verseLocationParams.getBook() == null){
            return false;
        }
        try {
            Integer.parseInt(verseLocationParams.getChapter());
            Integer.parseInt(verseLocationParams.getVerseNumber());
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
        return true;
    }
}
