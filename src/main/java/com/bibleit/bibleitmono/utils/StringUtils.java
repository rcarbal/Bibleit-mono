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

    public static JsonArray removeSynsMultipleWords(JsonArray extractedSynonyms) {

        JsonArray array = new JsonArray();

        for (JsonElement element : extractedSynonyms){
            String synonymString = element.getAsString();
            String[] synonymArray = synonymString.split("\\W+");

            //remove multi-word synonyms
            if (synonymArray.length > 1){
                continue;
            }
            array.add(synonymString);
        }

        return array;
    }
}
