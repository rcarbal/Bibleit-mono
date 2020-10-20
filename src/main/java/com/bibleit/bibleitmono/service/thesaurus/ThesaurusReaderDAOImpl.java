package com.bibleit.bibleitmono.service.thesaurus;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
                    JsonObject jsonObject = JsonParser.parseString(params).getAsJsonObject();

                    if (jsonObject.get("key") != null){
                        thesaurusData.put(jsonObject.get("key").getAsString(), jsonObject);

                        counter++;
                        System.out.print(counter + " " + "extracted");
                    }
                }

                System.out.println("Thesaurus Extraction complese");
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
    public void getWordSynonyms() {

    }
}
