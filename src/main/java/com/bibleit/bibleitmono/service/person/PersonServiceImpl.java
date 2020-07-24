package com.bibleit.bibleitmono.service.person;

import com.bibleit.bibleitmono.dao.mysql.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class PersonServiceImpl implements PersonService{

    @Autowired
    private PersonRepository personDAO;

}
