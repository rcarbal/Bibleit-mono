package com.bibleit.bibleitmono.controller.backendAPI.payment;

import com.bibleit.bibleitmono.dao.mysql.Donation;
import com.bibleit.bibleitmono.dao.mysql.Person;
import com.bibleit.bibleitmono.enums.CurrencyType;
import com.bibleit.bibleitmono.enums.PaymentStatus;
import com.bibleit.bibleitmono.service.payment.PaymentService;
import com.bibleit.bibleitmono.pojo.CompleteDonationInfo;
import com.bibleit.bibleitmono.pojo.CompleteDonationInfoImpl;
import com.bibleit.bibleitmono.pojo.PaymentResponse;
import com.bibleit.bibleitmono.pojo.PaymentResponseImpl;
import com.bibleit.bibleitmono.service.donation.DonationService;
import com.bibleit.bibleitmono.service.person.PersonService;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.exception.StripeException;
import com.stripe.model.*;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    @Autowired
    private PersonService personService;
    @Autowired
    private DonationService donationService;
    private Map<String, String> env = System.getenv();

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

        amount = amount.replace("$","");

        fName = fName.trim();
        lName = lName.trim();
        email = email.trim();
        phoneN = phoneN.trim();

        double amountConvt = Double. parseDouble(amount) * 100;
        Long longAmount = Double.valueOf(amountConvt).longValue();
        Session session = paymentService.getPaymentInformation(currency, productName, longAmount, email);
        PaymentResponse paymentResponse = new PaymentResponseImpl();

        // store session id to of donation
        Person person = new Person(fName, lName, email, phoneN);
        Person savedPerson = personService.save(person);

        String paymentId = session.getPaymentIntent();
        String sessionId = session.getId();
        BigDecimal donationAmount = BigDecimal.valueOf(amountConvt);
        String currencyType = CurrencyType.USD.getValue();
        int personId = person.getId();
        String initialDonationCreation = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String statusAsPending = PaymentStatus.PENDING.getStatusValue();


        paymentResponse.addClientId(paymentId);

        // TODO person object is not saved in the database
        if (savedPerson == null){
            return null;
        }
        else if (person.getId() < 0){
            return null;
        }
        Donation donation = new Donation(
                paymentId,
                sessionId,
                donationAmount,
                currencyType,
                personId,
                comment,
                initialDonationCreation,
                statusAsPending);

        Donation savedDonation = donationService.save(donation);
        if (savedDonation == null){
            System.out.println("Donation not saved to database");
        }
        else{
            System.out.println("Donation saved");
        }
        return session.getId();

    }

    @GetMapping("/donationSession")
    public CompleteDonationInfo getDonationInformationFromSession(@RequestParam String session_id){
        Donation donation = donationService.findBySessionId(session_id);
        Person person = personService.findById(donation.getPersonId());

        CompleteDonationInfo info = new CompleteDonationInfoImpl();
        info.setPerson(person);
        info.setDonation(donation);
        return info;
    }

    @PostMapping("/webhook")
    public String stripeWebhookEndpoint(@RequestBody String json, HttpServletRequest request) {
        String header = request.getHeader("Stripe-Signature");
        String endpointSecret = env.get("STRIPE_ENDPOINT_SECRET");
        Event event = null;
        try {
             event = Webhook.constructEvent(json, header, endpointSecret);
//            System.err.println(event);
        } catch (SignatureVerificationException e) {
            e.printStackTrace();
        }

        // Deserialize the nested object inside the event
        EventDataObjectDeserializer dataObjectDeserializer = event.getDataObjectDeserializer();
        StripeObject stripeObject = null;
        if (dataObjectDeserializer.getObject().isPresent()) {
            stripeObject = dataObjectDeserializer.getObject().get();
        } else {
            // Deserialization failed, probably due to an API version mismatch.
            // Refer to the Javadoc documentation on `EventDataObjectDeserializer` for
            // instructions on how to handle this case, or return an error here.
        }

        // Handle the event
        switch (event.getType()) {
            case "payment_intent.succeeded":
                PaymentIntent paymentIntent = (PaymentIntent) stripeObject;
                System.out.println("PaymentIntent was successful!");
                break;
            case "payment_method.attached":
                PaymentMethod paymentMethod = (PaymentMethod) stripeObject;
                System.out.println("PaymentMethod was attached to a Customer!");
                break;
            // ... handle other event types
            default:
                // Unexpected event type: response 400
                return "";
        }

        // Handle the checkout.session.completed event
        if ("payment_intent.succeeded".equals(event.getType())) {

            PaymentIntent paymentIntent = (PaymentIntent) stripeObject;

            String processedPaymentIntentId = paymentIntent.getId();
            String newTimeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

            Donation processedDonation = donationService.findByPaymentId(processedPaymentIntentId);
            processedDonation.setStatus(PaymentStatus.COMPLETE.getStatusValue());
            processedDonation.setTimeStamp(newTimeStamp);

            // update database
            Donation save = donationService.save(processedDonation);

            if (save.getStatus() == PaymentStatus.COMPLETE.getStatusValue()){
                System.out.println("donation updated to COMPLETE ***");
            }
        }
        return "";
    }
    @GetMapping("/test")
    public String test(){
        return "ok";
    }

}
