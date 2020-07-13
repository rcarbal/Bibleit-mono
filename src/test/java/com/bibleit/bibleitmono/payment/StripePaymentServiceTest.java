package com.bibleit.bibleitmono.payment;

import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;


class StripePaymentServiceTest {

    @Test
    public void getStripePaymentObject() throws StripeException {
        PaymentService stripePaymentService = new StripePaymentService();

        String currency = "usd";
        long amount = 1099L;

        Session paymentIntent = stripePaymentService.getPaymentInformation(currency, amount);

        assertNotNull(paymentIntent);
    }

}