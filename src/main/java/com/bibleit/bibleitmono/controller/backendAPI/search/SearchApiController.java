package com.bibleit.bibleitmono.controller.backendAPI.search;

import com.bibleit.bibleitmono.enums.QuestionType;
import com.bibleit.bibleitmono.pojo.QuestionAnswer;
import com.bibleit.bibleitmono.service.search.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SearchApiController {

    @Autowired
    private SearchService search;

    @GetMapping("search/test")
    public String test(){
        return "API SEARCH RESPONSE";
    }

    @GetMapping("/search")
    public List<QuestionAnswer> getSearch(@RequestParam String userInput){
        List<QuestionAnswer> result = search.getBestMatched(userInput, QuestionType.QUESTION);
        return result;
    }
}
