package com.bibleit.bibleitmono.pojo;

import java.util.List;

public interface QuestionAnswer {
    double getScore();
    void setVersesList(List<VersesContainer> verses);
    public int getId();

    String[] getVerses();
}
