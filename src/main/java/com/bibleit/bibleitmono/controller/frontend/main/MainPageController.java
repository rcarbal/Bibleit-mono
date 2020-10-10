package com.bibleit.bibleitmono.controller.frontend.main;

import com.bibleit.bibleitmono.pojo.QuestionAnswer;
import com.bibleit.bibleitmono.service.question.QuestionRetrievalService;
import com.bibleit.bibleitmono.utils.VerseExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class MainPageController {
    @Autowired
    private QuestionRetrievalService questions;
    @Autowired
    private VerseExtractor verseExtractor;

    @GetMapping("/")
    public String root(@CookieValue(value = "voice", defaultValue = "default")String myVoice,
                       Model model,
                       HttpServletResponse response){

        Cookie cookie = null;
        if (myVoice.equals("default")){
            cookie = new Cookie("voice", "off");
        } else {
            cookie = new Cookie("voice", myVoice);
        }
        //add cookie to response
        response.addCookie(cookie);
        model.addAttribute("voice", myVoice);
        return "search";
    }

    @GetMapping("question/{id}")
    public ModelAndView getQuestion(@PathVariable(value = "id")String id, Model model){

        QuestionAnswer questionById = questions.getQuestionById(id);
        QuestionAnswer questionWithVerses = verseExtractor.getVerseFromQuestion(questionById);
        model.addAttribute("question", questionWithVerses);
        return new ModelAndView("question");
    }
}
