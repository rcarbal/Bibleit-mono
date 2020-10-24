package com.bibleit.bibleitmono.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.Arrays;
import java.util.LinkedHashSet;

public class StringUtils {

    public static String[] removeDuplicates(String[] arr){
        //convert String array to LinkedHashSet to remove duplicates
        String str = new LinkedHashSet<String>( Arrays.asList(arr) ).toString().replaceAll("[\\[\\],]", "");
        String[] returnedArr = str.split("\\s+");

        return returnedArr;
        
    }

    public static void removeSynsMultipleWords(JsonArray extractedSynonyms) {
        for (JsonElement element : extractedSynonyms){
            String a = element.getAsString();
            System.out.println();
        }
    }
}
