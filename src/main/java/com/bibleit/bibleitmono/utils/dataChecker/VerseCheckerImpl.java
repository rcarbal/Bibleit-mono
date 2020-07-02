package com.bibleit.bibleitmono.utils.dataChecker;

import com.bibleit.bibleitmono.doa.QuestionDAO;
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
            }
        }

        return listOfBadVerses;
    }
}
