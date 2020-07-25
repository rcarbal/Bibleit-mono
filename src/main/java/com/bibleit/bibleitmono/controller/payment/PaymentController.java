package com.bibleit.bibleitmono.controller.payment;

import com.bibleit.bibleitmono.payment.PaymentService;
import com.bibleit.bibleitmono.pojo.PaymentResponse;
import com.bibleit.bibleitmono.pojo.PaymentResponseImpl;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/createSession")
    public PaymentResponse createSession(@RequestParam String fName,
                                         @RequestParam String lName,
                                         @RequestParam String email,
                                         @RequestParam String phoneN,
                                         @RequestParam long amount,
                                         @RequestParam String comment) throws StripeException {

        String currency = "usd";
        String productName = "Bible-it Donation";

        Session session = paymentService.getPaymentInformation(currency, productName, amount, email);
        PaymentResponse paymentResponse = new PaymentResponseImpl();
        paymentResponse.addClientId(session.getId());
        return paymentResponse;
    }

    @PostMapping("/donationProcess")
    public String donationProcess(@RequestParam String fName,
                                  @RequestParam String lName,
                                  @RequestParam String email,
                                  @RequestParam String phoneN,
                                  @RequestParam long amount,
                                  @RequestParam String comment,
                                  Model theModel) throws StripeException {
        String currency = "usd";
        String productName = "Bible-it Donation";

        fName = fName.trim();
        lName = lName.trim();
        email = email.trim();
        phoneN = phoneN.trim();

        Session session = paymentService.getPaymentInformation(currency, productName, amount, email);
        PaymentResponse paymentResponse = new PaymentResponseImpl();
        paymentResponse.addClientId(session.getId());

        // store session id to of donation


        theModel.addAttribute("id", session.getId());
        theModel.addAttribute("fName", fName);
        theModel.addAttribute("lName", lName);
        theModel.addAttribute("email", email);
        theModel.addAttribute("phoneNumber", phoneN);
        theModel.addAttribute("amount", amount);
        theModel.addAttribute("comment", comment);

        return session.getId();

    }

    @PostMapping("/webhook")
    public JSONObject webhook(){
        JSONObject webhookResponse = new JSONObject();
        webhookResponse.put("message", "okay");

        return webhookResponse;
    }

}
