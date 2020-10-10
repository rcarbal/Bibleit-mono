package com.bibleit.bibleitmono.service.payment;

import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;

public interface PaymentService {
    Session getPaymentInformation(String currency, String donationName, Long amount, String email) throws StripeException;
}
