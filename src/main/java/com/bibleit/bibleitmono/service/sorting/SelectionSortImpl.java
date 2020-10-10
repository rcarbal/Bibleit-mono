package com.bibleit.bibleitmono.service.sorting;

import com.bibleit.bibleitmono.pojo.QuestionAnswer;
import org.springframework.stereotype.Component;

@Component
public class SelectionSortImpl implements AlgorithmService{
    


    @Override
    public QuestionAnswer[] sortUsingInput(QuestionAnswer[] arrData) {

        QuestionAnswer[] arr = arrData;

        for (int lastUnsortedIndex = arr.length -1; lastUnsortedIndex >0; lastUnsortedIndex--){
            int largest = 0;

            for (int i = 1; i <= lastUnsortedIndex; i++){
                if (arr[i].getScore() > arr[largest].getScore()){
                    largest = i;
                }
            }
            swap(arr, largest, lastUnsortedIndex);
        }

        for (int i= 0; i < arr.length; i++){
        }
        
        return arr;
    }

    private static void swap(QuestionAnswer[] array, int i, int j){
        if (i == j){
            return;
        }
        QuestionAnswer temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

//    private QuestionAnswerImpl[] convertToImpl(QuestionAnswer[] arrData){
//
//    }
}
