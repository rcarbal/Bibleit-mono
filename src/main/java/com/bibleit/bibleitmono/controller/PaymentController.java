package com.bibleit.bibleitmono.controller;

import com.bibleit.bibleitmono.payment.PaymentService;
import com.bibleit.bibleitmono.pojo.PaymentResponse;
import com.bibleit.bibleitmono.pojo.PaymentResponseImpl;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/createSession")
    public PaymentResponse createSession() throws StripeException {
        Session session = paymentService.getPaymentInformation("usd", 1099L);
        PaymentResponse paymentResponse = new PaymentResponseImpl();
        paymentResponse.addClientId(session.getId());
        return paymentResponse;
    }
}
