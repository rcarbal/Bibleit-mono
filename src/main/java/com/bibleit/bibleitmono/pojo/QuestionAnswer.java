package com.bibleit.bibleitmono.pojo;

import java.util.List;

public interface QuestionAnswer {
    double getScore();
    void setVersesList(List<Verses> verses);

    String[] getVerses();
}
