package com.bibleit.bibleitmono.payment;

import com.stripe.model.PaymentIntent;

public class StripePaymentService implements PaymentService{

    @Override
    public PaymentIntent getPaymentInformation(String currency, long amount) {
        return new PaymentIntent();
    }
}
