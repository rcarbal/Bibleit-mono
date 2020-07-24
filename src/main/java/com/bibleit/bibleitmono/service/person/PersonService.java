package com.bibleit.bibleitmono.service.person;

import com.bibleit.bibleitmono.dao.mysql.Person;

public interface PersonService {

    Person findById();
    void save(Person person);
    void deleteById(int id);

}
