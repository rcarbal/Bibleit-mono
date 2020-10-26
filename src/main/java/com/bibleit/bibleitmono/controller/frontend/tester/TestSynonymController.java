package com.bibleit.bibleitmono.controller.frontend.tester;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestSynonymController {
    @GetMapping("/testSyns")
    public String testSynonyms(){
        return "synonymSearch";
    }
}
