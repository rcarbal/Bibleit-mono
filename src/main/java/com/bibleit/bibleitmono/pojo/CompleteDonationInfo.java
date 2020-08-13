package com.bibleit.bibleitmono.pojo;

import com.bibleit.bibleitmono.dao.mysql.Donation;
import com.bibleit.bibleitmono.dao.mysql.Person;

public interface CompleteDonationInfo {
    void setPerson(Person person);

    void setDonation(Donation donation);
}
