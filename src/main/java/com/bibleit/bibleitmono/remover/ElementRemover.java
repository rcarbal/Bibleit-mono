package com.bibleit.bibleitmono.remover;


import com.bibleit.bibleitmono.pojo.QuestionAnswer;

import java.util.List;

public interface ElementRemover<T> {
    List<QuestionAnswer> removeFromList(List<T> array, int amountToRemove);
}
