package com.bibleit.bibleitmono.payment;

import com.stripe.model.PaymentIntent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class StripePaymentServiceTest {

    @Test
    public void getStripePaymentObject(){
        PaymentService stripePaymentService = new StripePaymentService();

        String currency = "usd";
        long amount = 1099L;

        PaymentIntent paymentIntent = stripePaymentService.getPaymentInformation(currency, amount);

        assertNotNull(paymentIntent);
    }

}