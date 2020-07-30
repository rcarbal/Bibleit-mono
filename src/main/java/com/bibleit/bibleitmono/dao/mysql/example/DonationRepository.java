package com.bibleit.bibleitmono.dao.mysql.example;

import com.bibleit.bibleitmono.dao.mysql.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationRepository extends JpaRepository<Donation, Integer> {

}
