package com.bibleit.bibleitmono.utils;

import com.bibleit.bibleitmono.enums.BibleBooks;
import com.bibleit.bibleitmono.pojo.Verses;
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
    public String getVerse(String book, String chapter, String verse, JSONObject parsedBibleJSON) {
        JSONObject extractedBook = (JSONObject) parsedBibleJSON.get(book);
        Object extractedChapter = parsedBibleJSON.get(chapter);
        return null;
    }

    @Override
    public Verses getVerses(String book, String chapter, String startVerse, String endVerse, JSONObject parsedBibleJSON) {

        List<String> verses = new ArrayList<>();

        // make sure the verse string is equivalent to Integer
        try {
            Integer.parseInt(startVerse);
        }
        catch (NumberFormatException e){
            System.out.println(e);
            return null;
        }

        try {
            Integer.parseInt(endVerse);
        }
        catch (NumberFormatException e){
            System.out.println(e);
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
            
            verses.add(verse);
        }

        Verses rangeOfVerses = new Verses();
        rangeOfVerses.setVerses(verses);

        return rangeOfVerses;
    }


}
