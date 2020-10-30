package com.bibleit.bibleitmono.controller.backendAPI.synonym;

import com.bibleit.bibleitmono.service.thesaurus.ThesaurusReaderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SynonymController {

    @Autowired
    private ThesaurusReaderDAO doa;

    @GetMapping("/synonyms/test")
    public String getSynonyms(){
        return "Back-end:  response";
    }
    @GetMapping("/synonym/string")
    public void getSynonymString(@RequestParam String userInput){

    }

}
