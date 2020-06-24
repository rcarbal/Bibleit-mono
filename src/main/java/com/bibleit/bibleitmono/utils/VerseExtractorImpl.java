package com.bibleit.bibleitmono.utils;

import com.bibleit.bibleitmono.doa.BibleJSONDao;
import com.bibleit.bibleitmono.pojo.QuestionAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VerseExtractorImpl implements VerseExtractor{

    @Autowired
    private BibleJSONDao doa;
    private String[] xVerses;
    private String[] versesArrInfo;

    @Override
    public List<QuestionAnswer> getVerses(List<QuestionAnswer> finalQuestions) {

        for (QuestionAnswer question : finalQuestions){
            // get the verses
            xVerses = question.getVerses();
            getVersesInfoFromArray();
            continue;
        }
        return null;
    }

    private Object getVersesInfoFromArray(){

        if (xVerses == null){
            return null;
        }

        //loop through the xVerses array
        for (String x : xVerses){
            if (x.contains("-")){

                String[] arrayOfVerseArr = x.split("-");
                int index = 0;
                String book = null;
                String chapter = null;
                String startVerse = null;
                String endVerse = null;

                // loop through the range of verses EXAMPLE: Book Chapter:Verse-Book Chapter:Verse
                for (String y : arrayOfVerseArr){

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

                // all the information has been extracted add it to the versesArrInfo
                versesArrInfo = new String[4];

                versesArrInfo[0] = book;
                versesArrInfo[1] = chapter;
                versesArrInfo[2] = startVerse;
                versesArrInfo[3] = endVerse;

                // TODO the function ends here, and nothing is fone with the verse String[]
            }
        }

        return null;
    }
}
