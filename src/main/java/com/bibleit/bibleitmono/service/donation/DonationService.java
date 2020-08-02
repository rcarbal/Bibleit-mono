package com.bibleit.bibleitmono.service.donation;

import com.bibleit.bibleitmono.dao.mysql.Donation;

import java.util.List;

public interface DonationService {

    Donation findById(int id);
    Donation findByIntentId(String id);
    List<Donation> findAll();
    Donation save(Donation donation);
    void deleteById(int id);
}
