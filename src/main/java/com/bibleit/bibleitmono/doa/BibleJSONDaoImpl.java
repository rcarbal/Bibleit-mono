package com.bibleit.bibleitmono.doa;

import com.bibleit.bibleitmono.utils.JSONObjectParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Component
public class BibleJSONDaoImpl implements BibleJSONDao {
    
    private JSONObject parsedBibleJSON;
    @Autowired
    private JSONObjectParser parser;


    public BibleJSONDaoImpl() {
        setupScriptureCollection();
    }

    @Override
    public JSONObject getBible() {
        return parsedBibleJSON;
    }

    @Override
    public String[] getBooks() {
        return parser.getBooks();
    }

    @Override
    public JSONObject getBook(String bookName) {
        return parser.getBook(bookName, parsedBibleJSON);
    }

    @Override
    public String getVerse(String book, String chapter, String verse) {
        return parser.getVerse(book, chapter, verse, parsedBibleJSON);
    }


    public void setupScriptureCollection() {

        Resource resource = new ClassPathResource("json/bible-niv.json");
        JSONParser jsonParser = new JSONParser();

        try {
            InputStream input = resource.getInputStream();
            // get json data
            parsedBibleJSON = (JSONObject) jsonParser.parse(new InputStreamReader(input, "UTF-8"));

            input.close();

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
