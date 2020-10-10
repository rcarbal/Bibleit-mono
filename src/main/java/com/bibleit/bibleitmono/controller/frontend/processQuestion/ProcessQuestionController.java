package com.bibleit.bibleitmono.controller.frontend.processQuestion;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProcessQuestionController {

    @GetMapping("/processQuestion")
    public String processQuestionLayout(){

        return "processQuestion";
    }
}
