package com.bibleit.bibleitmono.service.payment;

import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;


class StripePaymentServiceTest {

    @Test
    public void getStripePaymentObject() throws StripeException {
        PaymentService stripePaymentService = new StripePaymentService();

        String currency = "usd";
        String productName = "Bible-it Donation";
        String email = "rcarbaleq2@gmail.com";
        long donationAmount = 1099L;

        Session paymentIntent = stripePaymentService.getPaymentInformation(currency, productName, donationAmount, email);

        assertNotNull(paymentIntent);
    }

}