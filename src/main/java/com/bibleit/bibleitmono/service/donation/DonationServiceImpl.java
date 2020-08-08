package com.bibleit.bibleitmono.service.donation;

import com.bibleit.bibleitmono.dao.mysql.Donation;
import com.bibleit.bibleitmono.dao.mysql.example.DonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DonationServiceImpl implements DonationService{

    @Autowired
    private DonationRepository repository;

    @Override
    public Donation findById(int id) {
        return null;
    }

    @Override
    public Donation findByPaymentId(String id) {
        return repository.findDonationByPaymentIntent(id);
    }

    @Override
    public Donation findBySessionId(String id) {
        return repository.findDonationBySessionId(id);
    }


    @Override
    public List<Donation> findAll() {
        return null;
    }

    @Override
    public Donation save(Donation donation) {
        return repository.save(donation);
    }

    @Override
    public void deleteById(int id) {

    }
}
