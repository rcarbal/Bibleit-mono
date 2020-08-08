package com.bibleit.bibleitmono.dao.mysql.example;

import com.bibleit.bibleitmono.dao.mysql.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DonationRepository extends JpaRepository<Donation, Integer> {

    @Query(value="SELECT * FROM bibleitdatabase.donation WHERE payment_id = :id", nativeQuery = true)
    Donation findDonationByPaymentIntent(@Param("id") String id);

    @Query(value="SELECT * FROM bibleitdatabase.donation WHERE session_id = :id", nativeQuery = true)
    Donation findDonationBySessionId(@Param("id") String id);

}
