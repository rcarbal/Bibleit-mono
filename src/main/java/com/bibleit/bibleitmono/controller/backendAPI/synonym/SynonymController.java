package com.bibleit.bibleitmono.controller.backendAPI.synonym;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SynonymController {

    @GetMapping("/synonyms/test")
    public String getSynonyms(){
        return "Back-end:  response";
    }

}
