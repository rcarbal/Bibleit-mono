package com.bibleit.bibleitmono.dao.mysql.example;

import com.bibleit.bibleitmono.dao.mysql.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DonationRepository extends JpaRepository<Donation, Integer> {

//    @Query(value="SELECT c FROM donation WHERE session_id = :id", nativeQuery = true)
    @Query(value="SELECT * FROM bibleitdatabase.donation WHERE session_id = :id", nativeQuery = true)
    Donation findDonationByIntent(@Param("id") String id);

}
