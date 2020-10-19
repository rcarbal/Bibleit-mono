package com.bibleit.bibleitmono.controller.backendAPI.repository;

import com.bibleit.bibleitmono.service.question.QuestionRetrievalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/questions")
public class QuestionsRepoController {

    @Autowired
    private QuestionRetrievalService questions;

    @GetMapping("/refresh")
    public String refreshQuestions(){
        boolean refreshed = questions.refreshQuestions();

        if (refreshed){
            return "refreshed successful";
        }
        else {
            return "refreshed failed";
        }
    }
}
