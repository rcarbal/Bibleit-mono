package com.bibleit.bibleitmono.controller.frontend.donation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DecimalFormat;

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

    @GetMapping("/donationReview")
    public String donationReview(@RequestParam String fName,
                                 @RequestParam String lName,
                                 @RequestParam String email,
                                 @RequestParam String phoneN,
                                 @RequestParam String amount,
                                 @RequestParam String comment,
                                 Model theModel){

        theModel.addAttribute("fName", fName);
        theModel.addAttribute("lName", lName);
        theModel.addAttribute("email", email);
        theModel.addAttribute("phoneNumber", phoneN);

        // check if value is a decimal
        String add$ToAmount = "$";
        boolean s = amount.contains(".");

        if (!s){
            DecimalFormat df = new DecimalFormat("0.00");
            String format = df.format(Long.parseLong(amount));
            add$ToAmount = add$ToAmount + format;

        }
        else {
            add$ToAmount = add$ToAmount + amount;
        }
        theModel.addAttribute("amount", add$ToAmount);
        theModel.addAttribute("comment", comment);

        return "donationReview";
    }

    @GetMapping("/donationSuccess")
    public String donationSuccess(){
        return "success";
    }

}
