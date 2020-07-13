package com.bibleit.bibleitmono.payment;

import com.stripe.model.PaymentIntent;

public interface PaymentService {
    PaymentIntent getPaymentInformation(String currency, long amount);
}
