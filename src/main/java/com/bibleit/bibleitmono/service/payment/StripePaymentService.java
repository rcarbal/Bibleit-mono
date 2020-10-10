package com.bibleit.bibleitmono.service.payment;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class StripePaymentService implements PaymentService{

    private Map<String, String> env = System.getenv();

    @Override
    public Session getPaymentInformation(String currency, String donationName, Long amount, String email) throws StripeException {

        Stripe.apiKey = env.get("STRIPE_SECRET_KEY");

        SessionCreateParams params =
                SessionCreateParams.builder()
                        .setCustomerEmail(email)
                        .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                        .setMode(SessionCreateParams.Mode.PAYMENT)
                        .setSuccessUrl(env.get("STRIPE_PAYMENT_SUCCESS"))
                        .setCancelUrl(env.get("STRIPE_PAYMENT_FAILURE"))
                        .addLineItem(
                                SessionCreateParams.LineItem.builder()
                                        .setQuantity(1L)
                                        .setPriceData(
                                                SessionCreateParams.LineItem.PriceData.builder()
                                                        .setCurrency(currency)
                                                        .setUnitAmount(amount)
                                                        .setProductData(
                                                                SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                        .setName(donationName)
                                                                        .build())
                                                        .build())
                                        .build())
                        .build();
        Session session = Session.create(params);
        return session;
    }
}
