package com.bibleit.bibleitmono.controller.payment;

import com.bibleit.bibleitmono.dao.mysql.Donation;
import com.bibleit.bibleitmono.dao.mysql.Person;
import com.bibleit.bibleitmono.enums.PaymentStatus;
import com.bibleit.bibleitmono.payment.PaymentService;
import com.bibleit.bibleitmono.pojo.PaymentResponse;
import com.bibleit.bibleitmono.pojo.PaymentResponseImpl;
import com.bibleit.bibleitmono.service.donation.DonationService;
import com.bibleit.bibleitmono.service.person.PersonService;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.exception.StripeException;
import com.stripe.model.Event;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    @Autowired
    private PersonService personService;
    @Autowired
    private DonationService donationService;

    @PostMapping("/createPaymentSession")
    public PaymentResponse createSession(@RequestParam String fName,
                                         @RequestParam String lName,
                                         @RequestParam String email,
                                         @RequestParam String phoneN,
                                         @RequestParam long amount,
                                         @RequestParam String comment) throws StripeException {

        String currency = "usd";
        String productName = "Bible-it Donation";

        Session session = paymentService.getPaymentInformation(currency, productName, null, email);
        PaymentResponse paymentResponse = new PaymentResponseImpl();
        paymentResponse.addClientId(session.getId());
        return paymentResponse;
    }

    @PostMapping("/processPaymentSession")
    public String donationProcess(@RequestParam String fName,
                                  @RequestParam String lName,
                                  @RequestParam String email,
                                  @RequestParam String phoneN,
                                  @RequestParam String amount,
                                  @RequestParam String comment,
                                  Model theModel) throws StripeException {
        String currency = "usd";
        String productName = "Bible-it Donation";

        fName = fName.trim();
        lName = lName.trim();
        email = email.trim();
        phoneN = phoneN.trim();

        Double amountConvt = Double.parseDouble(amount) * 100;
        Long longAmount = Double.valueOf(amountConvt).longValue();
        Session session = paymentService.getPaymentInformation(currency, productName, longAmount, email);
        PaymentResponse paymentResponse = new PaymentResponseImpl();
        paymentResponse.addClientId(session.getId());

        // store session id to of donation
        Person person = new Person(fName, lName, email, phoneN);
        Person savedPerson = personService.save(person);

        // TODO person object is not saved in the database
        if (savedPerson == null){
            return null;
        }
        else if (person.getId() < 0){
            return null;
        }
        Donation donation = new Donation(session.getId(),
                BigDecimal.valueOf(amountConvt),
                "usd",
                person.getId(),
                comment,
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),
                PaymentStatus.PENDING.getStatusValue());

        donationService.save(donation);

        return session.getId();

    }

    @PostMapping("/webhook")
    public String stripeWebhookEndpoint(@RequestBody String json, HttpServletRequest request) {
        String header = request.getHeader("Stripe-Signature");
        String endpointSecret = "your stripe webhook secret";
        try {
            Event event = Webhook.constructEvent(json, header, endpointSecret);
            System.err.println(event);
        } catch (SignatureVerificationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //
        return "";

    }

}
