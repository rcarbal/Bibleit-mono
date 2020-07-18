package com.bibleit.bibleitmono.controller;

import com.bibleit.bibleitmono.payment.PaymentService;
import com.bibleit.bibleitmono.pojo.PaymentResponse;
import com.bibleit.bibleitmono.pojo.PaymentResponseImpl;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import org.springframework.beans.factory.annotation.Autowired;
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

        Session session = paymentService.getPaymentInformation(currency, productName, amount);
        PaymentResponse paymentResponse = new PaymentResponseImpl();
        paymentResponse.addClientId(session.getId());
        return paymentResponse;
    }
}
