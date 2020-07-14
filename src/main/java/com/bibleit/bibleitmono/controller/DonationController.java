package com.bibleit.bibleitmono.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DonationController {

    @GetMapping("/donate")
    public String donationInfo(){
        return "donationInfo";
    }

    @GetMapping("/donationForm")
    public String donationForm(){
        return "donationForm";
    }

    @GetMapping("/donationSuccess")
    public String donationSuccess(){
        return "success";
    }


}
