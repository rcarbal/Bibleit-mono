package com.bibleit.bibleitmono.service.person;

import com.bibleit.bibleitmono.dao.mysql.Person;

import java.util.List;

public interface PersonService {

    Person findById(int id);
    List<Person> findAll();
    Person save(Person person);
    void deleteById(int id);

    int findPerson(String fName, String lName, String email, String phoneN);
}
