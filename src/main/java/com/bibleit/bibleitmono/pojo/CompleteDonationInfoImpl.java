package com.bibleit.bibleitmono.pojo;

import com.bibleit.bibleitmono.dao.mysql.Donation;
import com.bibleit.bibleitmono.dao.mysql.Person;

public class CompleteDonationInfoImpl implements CompleteDonationInfo {

    private Donation donation;
    private Person person;

    public Donation getDonation() {
        return donation;
    }

    public void setDonation(Donation donation) {
        this.donation = donation;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
