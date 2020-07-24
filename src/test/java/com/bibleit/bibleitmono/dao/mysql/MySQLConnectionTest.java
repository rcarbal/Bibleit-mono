package com.bibleit.bibleitmono.dao.mysql;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MySQLConnectionTest {

    @Autowired
    private PersonRepository repository;

    @Test
    public void testConnection(){
        System.out.println("Just a repository test");
    }
}
