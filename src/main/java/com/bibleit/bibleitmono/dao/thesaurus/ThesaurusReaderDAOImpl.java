package com.bibleit.bibleitmono.dao.thesaurus;

import com.bibleit.bibleitmono.enums.WordPos;
import com.bibleit.bibleitmono.utils.StringUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ThesaurusReaderDAOImpl implements ThesaurusReaderDAO {

    private static Map<String, JsonObject> thesaurusData = new HashMap<>();

    static {
        Scanner scanner = null;
        InputStream input = null;

        try {
            Resource resource = new ClassPathResource("json/en_thesaurus.jsonl");

            if (resource != null) {
                input = resource.getInputStream();
                scanner = new Scanner(input);

                int counter = 0;

                while (scanner.hasNextLine()){
                    String params = scanner.nextLine();
//                    JsonObject jsonObject = JsonParser.parseString(params).getAsJsonObject();
                    JsonObject jsonObject = new Gson().fromJson(params, JsonObject.class);

                    if (jsonObject.get("key") != null){
                        thesaurusData.put(jsonObject.get("key").getAsString(), jsonObject);

                        counter++;
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (scanner != null){
                scanner.close();
            }
        }
    }


    @Override
    public Map<String, JsonObject> getAll() {
        return thesaurusData;
    }

    @Override
    public void getWordInfo() {

    }

    @Override
    public JsonArray getAllSynonymsOfWord(String word) {
        JsonArray synonyms = new JsonArray();

        // find all occurrences of a word
        int index = 1;

        while (thesaurusData.containsKey(word + "_" + index)){

            String key = word + "_" + index;
            JsonObject allWordInfo = thesaurusData.get(key);
            JsonArray extractedSynonyms = (JsonArray) allWordInfo.get(WordPos.SYNONYMS.getPosValue());

            synonyms.addAll(extractedSynonyms);

            index++;
        }

        return synonyms;
    }

    @Override
    public JsonArray getAllSynonymsOfWord(String word, String synonymFormat) {
        JsonArray synonyms = new JsonArray();

        // find all occurrences of a word
        int index = 1;

        while (thesaurusData.containsKey(word + "_" + index)){

            String key = word + "_" + index;
            JsonObject allWordInfo = thesaurusData.get(key);
            JsonArray extractedSynonyms = (JsonArray) allWordInfo.get(WordPos.SYNONYMS.getPosValue());

            // check if synonyms have multiple words
            if (synonymFormat.equals(WordPos.SINGLE.getPosValue())){
                JsonArray array = StringUtils.removeSynsMultipleWords(extractedSynonyms);
                synonyms.addAll(array);
            }
            index++;
        }

        return synonyms;
    }
}
