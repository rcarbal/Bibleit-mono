package com.bibleit.bibleitmono.utils;

import com.bibleit.bibleitmono.doa.BibleJSONDao;
import com.bibleit.bibleitmono.pojo.QuestionAnswer;
import com.bibleit.bibleitmono.pojo.Verses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VerseExtractorImpl implements VerseExtractor{

    @Autowired
    private BibleJSONDao doa;
    private String[] versesArrInfo;

    @Override
    public List<QuestionAnswer> getVerses(List<QuestionAnswer> finalQuestions) {

        for (QuestionAnswer question : finalQuestions){
            // loop through all the verse
            List<Verses> verses =  getVersesInfoFromArray(question.getVerses());
            question.setVersesList(verses);

        }

        // return new List with all the verses already available.
        return finalQuestions;
    }

    private List<Verses> getVersesInfoFromArray(String[] questions){

        if (questions == null){
            return null;
        }

        // the main returned List<Verses>
        List<Verses> mainArr = new ArrayList<>();

        //loop through the xVerses array
        for (String x : questions){

            // check if there is a range of verses in one verse string.
            if (x.contains("-")){

                // create an array for the range of verses
                String[] splitSingleVersesStringArr = x.split("-");
                int index = 0;
                String book = null;
                String chapter = null;
                String startVerse = null;
                String endVerse = null;

                // loop through the range of verses EXAMPLE: Book Chapter:Verse-Book Chapter:Verse
                for (String y : splitSingleVersesStringArr){

                    String trimmed = y.trim();
                    if (index == 0){
                        book = trimmed.split(" ")[0];
                        char chapterChar = trimmed.split(" ")[1].charAt(0);
                        chapter = Character.toString(chapterChar);
                        startVerse = trimmed.split(" ")[1].split(":")[1];
                    }
                    else {
                        endVerse = trimmed.split(" ")[1].split(":")[1];
                    }

                    index++;
                }

                // get the verses of a range
                Verses rangeOfVerse = doa.getVerses(book, chapter, startVerse, endVerse);

                // add the rangeOfVerses to List<Verses>
                mainArr.add(rangeOfVerse);
            }
        }

        return mainArr;
    }
}
