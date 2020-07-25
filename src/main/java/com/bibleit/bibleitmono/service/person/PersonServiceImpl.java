package com.bibleit.bibleitmono.service.person;

import com.bibleit.bibleitmono.dao.mysql.Person;
import com.bibleit.bibleitmono.dao.mysql.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class PersonServiceImpl implements PersonService{

    @Autowired
    private PersonRepository personRepository;

    @Override
    public Person findById(int id) {
        Optional<Person> result = personRepository.findById(id);

        Person thePerson = null;
        if (result.isPresent()){
            thePerson = result.get();
        }
        else {
            throw new RuntimeException("Did not find person id - " + id);
        }
        return thePerson;
    }

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public void save(Person person) {
        personRepository.save(person);
    }

    @Override
    public void deleteById(int id) {
        personRepository.deleteById(id);
    }
}
