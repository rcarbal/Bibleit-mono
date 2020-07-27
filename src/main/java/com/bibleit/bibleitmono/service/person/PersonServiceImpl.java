package com.bibleit.bibleitmono.service.person;

import com.bibleit.bibleitmono.dao.mysql.Person;
import com.bibleit.bibleitmono.dao.mysql.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
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
    public Person save(Person person) {
        return personRepository.save(person);
    }

    @Override
    public void deleteById(int id) {
        personRepository.deleteById(id);
    }

    @Override
    public int findPerson(String fName, String lName, String email, String phoneN) {
        return 0;
    }
}
