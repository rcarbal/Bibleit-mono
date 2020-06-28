package com.bibleit.bibleitmono.utils;

import com.bibleit.bibleitmono.enums.BibleBooks;
import com.bibleit.bibleitmono.pojo.Verse;
import com.bibleit.bibleitmono.pojo.VersesContainer;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JSONObjectParserImpl implements JSONObjectParser {

    @Override
    public String[] getBooks() {
        String[] array = new String[66];

        int i = 0;
        for (BibleBooks book : BibleBooks.values()){
            array[i] = book.getBookValue();
            i++;
        }

        return array;
    }

    @Override
    public JSONObject getBook(String book, JSONObject parsedBibleJSON) {
        JSONObject extractedBook = (JSONObject) parsedBibleJSON.get(book);
        return extractedBook;
    }

    @Override
    public VersesContainer getVerse(String book, String chapter, String verse, JSONObject parsedBibleJSON) {
        List<Verse> verseList = new ArrayList<>();
        VersesContainer singleVerse = new VersesContainer();

        try {
            Integer.parseInt(verse);
        }
        catch (NumberFormatException e){
            System.out.println(e);
            System.out.println("Inside getVerse() JSONObjectParserImpl: " + verse);
            return null;
        }

        JSONObject extractedBook = (JSONObject) parsedBibleJSON.get(book);
        JSONObject extractedChapter = (JSONObject) extractedBook.get(chapter);

        // get verse
        String extractedVerse = (String) extractedChapter.get(String.valueOf(verse));
        if (extractedVerse ==  null){
            return null;
        }

        String bibleLocationData = book + " " + chapter + ":" + verse;
        // set the verseInfo data
        Verse verseText = new Verse();
        verseText.setVerse(extractedVerse);
        verseText.setBibleLocation(bibleLocationData);

        // Add the text and bible data Verse POJO to the List<Verse>
        verseList.add(verseText);

        // Add POJO of Verse to the VerseContainer
        singleVerse.setVerses(verseList);

        //return the VerseContainer
        return singleVerse;
    }

    @Override
    public VersesContainer getVerses(String book, String chapter, String startVerse, String endVerse, JSONObject parsedBibleJSON) {

        List<Verse> verses = new ArrayList<>();

        // make sure the verse string is equivalent to Integer
        try {
            Integer.parseInt(startVerse);
        }
        catch (NumberFormatException e){
            System.out.println(e);
            System.out.println("Inside getVerses() JSONObjectParserImpl: "+ startVerse);
            return null;
        }

        try {
            Integer.parseInt(endVerse);
        }
        catch (NumberFormatException e){
            System.out.println(e);
            System.out.println("Inside getVerses() JSONObjectParserImpl: "+ endVerse);
            return null;
        }

        for (int i=Integer.parseInt(startVerse); i <= Integer.parseInt(endVerse); i++ ){
            JSONObject extractedBook = (JSONObject) parsedBibleJSON.get(book);
            JSONObject extractedChapter = (JSONObject) extractedBook.get(chapter);

            // get verse
            String verse = (String) extractedChapter.get(String.valueOf(i));
            if (verse ==  null){
                continue;
            }

            String bibleLocationData = book + " " + chapter + ":" + i;

            // add verse text to container that will hold the verse information
            Verse verseInfo = new Verse();
            verseInfo.setVerse(verse);
            verseInfo.setBibleLocation(bibleLocationData);

            verses.add(verseInfo);
        }

        VersesContainer rangeOfVerses = new VersesContainer();
        rangeOfVerses.setVerses(verses);

        return rangeOfVerses;
    }


}
