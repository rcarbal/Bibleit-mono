package com.bibleit.bibleitmono.file_reader;

import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class FileReaderImplTest {

    @Test
    public void readFile(){
        Resource resource = new ClassPathResource("json/en_thesaurus.jsonl");
        JSONParser jsonParser = new JSONParser();

        try {
            InputStream input = resource.getInputStream();
            // get json data
//            parsedBibleJSON = (JSONObject) jsonParser.parse(new InputStreamReader(input, "UTF-8"));

            assertNotNull(resource);
            input.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}