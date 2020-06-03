package com.bibleit.bibleitmono.remover;

import com.bibleit.bibleitmono.pojo.QuestionAnswer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ArrayListElementRemoverImpl implements ElementRemover{

    @Override
    public List<QuestionAnswer> removeFromList(List originalSortedList, int amountToRemove) {
        List<QuestionAnswer> lastNQuestions = new ArrayList<>();


        int length = originalSortedList.size();

        if (length < amountToRemove){
            amountToRemove = length;
        }

        if (length > 0){
            for (int i = length; i > length - amountToRemove; i--){
                QuestionAnswer removedElement = (QuestionAnswer) originalSortedList.get(i-1);
                lastNQuestions.add(removedElement);
            }
        }
        return lastNQuestions;
    }
}
