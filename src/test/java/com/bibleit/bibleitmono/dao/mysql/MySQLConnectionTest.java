package com.bibleit.bibleitmono.dao.mysql;

import com.bibleit.bibleitmono.dao.mysql.example.Expense;
import com.bibleit.bibleitmono.dao.mysql.example.ExpenseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MySQLConnectionTest {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private ExpenseRepository repository;

    @Test
    public void testConnection(){
        System.out.println("Just a repository test");
    }

    @Test
    public void testDatabase(){
        repository.save(new Expense("breakfast", 5));
        repository.save(new Expense("coffee", 2));
        repository.save(new Expense("New SSD drive", 200));
        repository.save(new Expense("Tution for baby", 350));
        repository.save(new Expense("Some apples", 5));

        Iterable<Expense> iterator = repository.findAll();

        System.out.println("All expense items: ");
        iterator.forEach(item -> System.out.println(item));

        List<Expense> breakfast = repository.findByItem("breakfast");
        System.out.println("\nHow does my breakfast cost?: ");
        breakfast.forEach(item -> System.out.println(item));

        List<Expense> expensiveItems = repository.listItemsWithPriceOver(200);
        System.out.println("\nExpensive Items: ");
        expensiveItems.forEach(item -> System.out.println(item));
    }
}
