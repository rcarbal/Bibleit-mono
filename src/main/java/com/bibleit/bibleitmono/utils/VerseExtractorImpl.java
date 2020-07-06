package com.bibleit.bibleitmono.utils;

import com.bibleit.bibleitmono.doa.BibleJSONDao;
import com.bibleit.bibleitmono.pojo.QuestionAnswer;
import com.bibleit.bibleitmono.pojo.Verse;
import com.bibleit.bibleitmono.pojo.VersesContainer;
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
            List<VersesContainer> verses =  getVersesInfoFromArray(question.getVerses());
            question.setVersesList(verses);
        }
        // return new List with all the verses already available.
        return finalQuestions;
    }

    @Override
    public Verse getVerseLocationParams(String verseLocation) {

        Verse returnedVerse = new Verse();

        String book = null;
        String chapter = null;
        String startVerse = null;
        String endVerse = null;

        if (verseLocation.contains("-")){

            // create an array for the range of verses
            String[] splitSingleVersesStringArr = verseLocation.split("-");
            int index = 0;

            // loop through the range of verses EXAMPLE: Book Chapter:Verse-Book Chapter:Verse
            for (String y : splitSingleVersesStringArr){

                String trimmed = y.trim();
                if (index == 0){

                    try {
                        book = trimmed.split(" ")[0];
                        char chapterChar = trimmed.split(" ")[1].charAt(0);
                        chapter = Character.toString(chapterChar);
                        startVerse = trimmed.split(" ")[1].split(":")[1];
                    }
                    catch (Exception e){
                        System.out.println(e);
                        System.out.println("Inside getVersesInfoFromArray() VerseExtractorImpl: book, chapter, " +
                                "startVerse");
                        continue;
                    }

                }
                else {
                    try {
                        endVerse = trimmed.split(" ")[1].split(":")[1];
                    }
                    catch (Exception e){
                        System.out.println(e);
                        System.out.println("Inside getVersesInfoFromArray() VerseExtractorImpl: "+ endVerse);
                        continue;
                    }

                }

                index++;
            }

            returnedVerse.setBook(book);
            returnedVerse.setChapter(chapter);
            returnedVerse.setVerseNumber(startVerse);
        }
        // else if not range of verses
        else {
            String trimmedX = verseLocation.trim();
            try {
                book = trimmedX.split(" ")[0];
                char chapterChar = trimmedX.split(" ")[1].charAt(0);
                chapter = Character.toString(chapterChar);
                startVerse = trimmedX.split(" ")[1].split(":")[1];
            }
            catch (Exception e){
                System.out.println(e);
                System.out.println("Inside getVersesInfoFromArray() VerseExtractorImpl: book, chapter, " +
                        "startVerse for none range verse : else {}");
                System.out.println(trimmedX);
                System.out.println("Book: " + book);
                System.out.println("Chapter:" + chapter);
                System.out.println("Verse" + startVerse);
            }
            returnedVerse.setBook(book);
            returnedVerse.setChapter(chapter);
            returnedVerse.setVerseNumber(startVerse);
        }
        return returnedVerse;
    }

    @Override
    public List<QuestionAnswer> removeBadVerse(List<QuestionAnswer> questionsWithVerses) {
        return null;
    }

    @Override
    public QuestionAnswer getVerseFromQuestion(QuestionAnswer questionById) {
        String[] verses = questionById.getVerses();

        List<VersesContainer> versesInfoFromArray = getVersesInfoFromArray(verses);
        if (versesInfoFromArray == null ){
            return null;
        }

        questionById.setVersesList(versesInfoFromArray);
        return questionById;
    }

    private List<VersesContainer> getVersesInfoFromArray(String[] questions){

        if (questions == null){
            return null;
        }

        // the main returned List<Verses>
        List<VersesContainer> mainArr = new ArrayList<>();

        //loop through the xVerses array
        for (String x : questions){

            String book = null;
            String chapter = null;
            String startVerse = null;
            String endVerse = null;

            // check if there is a range of verses in one verse string.
            if (x.contains("-")){

                // create an array for the range of verses
                String[] splitSingleVersesStringArr = x.split("-");
                int index = 0;

                // loop through the range of verses EXAMPLE: Book Chapter:Verse-Book Chapter:Verse
                for (String y : splitSingleVersesStringArr){

                    String trimmed = y.trim();
                    if (index == 0){

                        try {
                            book = trimmed.split(" ")[0];
                            char chapterChar = trimmed.split(" ")[1].charAt(0);
                            chapter = Character.toString(chapterChar);
                            startVerse = trimmed.split(" ")[1].split(":")[1];
                        }
                        catch (Exception e){
                            System.out.println(e);
                            System.out.println("Inside getVersesInfoFromArray() VerseExtractorImpl: book, chapter, " +
                                    "startVerse");
                            continue;
                        }

                    }
                    else {
                        try {
                            endVerse = trimmed.split(" ")[1].split(":")[1];
                        }
                        catch (Exception e){
                            System.out.println(e);
                            System.out.println("Inside getVersesInfoFromArray() VerseExtractorImpl: "+ endVerse);
                            continue;
                        }

                    }

                    index++;
                }

                // get the verses of a range
                VersesContainer rangeOfVerse = doa.getVerses(book, chapter, startVerse, endVerse);

                // add the rangeOfVerses to List<Verses>
                mainArr.add(rangeOfVerse);
            }
            // else if not range of verses
            else {
                String trimmedX = x.trim();
                try {
                    book = trimmedX.split(" ")[0];
                    char chapterChar = trimmedX.split(" ")[1].charAt(0);
                    chapter = Character.toString(chapterChar);
                    startVerse = trimmedX.split(" ")[1].split(":")[1];
                }
                catch (Exception e){
                    System.out.println(e);
                    System.out.println("Inside getVersesInfoFromArray() VerseExtractorImpl: book, chapter, " +
                            "startVerse for none range verse : else {}");
                    System.out.println(trimmedX);
                    System.out.println("Book: " + book);
                    System.out.println("Chapter:" + chapter);
                    System.out.println("Verse" + startVerse);
                    continue;
                }
                VersesContainer singleVerse = doa.getVerse(book, chapter, startVerse);
                mainArr.add(singleVerse);

            }
        }

        return mainArr;
    }
}
