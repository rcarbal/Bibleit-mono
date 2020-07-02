package com.bibleit.bibleitmono.utils.dataChecker;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class VerseCheckerImplTest {

    @Autowired
    private VerseChecker checker;

    @Test
    public void checkAllVersesForErrors(){
        List<Integer> checkedList = checker.checkAllVerses();

        assertTrue(checkedList.size() == 0);


    }
}