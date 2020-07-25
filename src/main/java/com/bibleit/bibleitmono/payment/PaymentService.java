package com.bibleit.bibleitmono.payment;

import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;

public interface PaymentService {
    Session getPaymentInformation(String currency, String donationName, long amount, String email) throws StripeException;
}
