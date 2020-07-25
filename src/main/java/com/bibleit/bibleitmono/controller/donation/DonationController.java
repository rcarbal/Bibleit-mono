package com.bibleit.bibleitmono.controller.donation;

import com.bibleit.bibleitmono.payment.PaymentService;
import com.bibleit.bibleitmono.service.person.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DonationController {

    @Autowired
    private PersonService personService;

    @Autowired
    private PaymentService paymentService;

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
                                 @RequestParam long amount,
                                 @RequestParam String comment,
                                 Model theModel){

        theModel.addAttribute("fName", fName);
        theModel.addAttribute("lName", lName);
        theModel.addAttribute("email", email);
        theModel.addAttribute("phoneNumber", phoneN);
        theModel.addAttribute("amount", amount);
        theModel.addAttribute("comment", comment);

        return "donationReview";
    }

    @GetMapping("/donationSuccess")
    public String donationSuccess(){
        return "success";
    }

}
