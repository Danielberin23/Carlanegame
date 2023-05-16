package com.example.firstapplication;
import java.util.ArrayList;

public class myDB {

    private ArrayList<Score> scores;

    public myDB() { }

    public ArrayList<Score> getRecords() {
        if(scores == null){
            scores = new ArrayList<>();
        }
        return scores;
    }

}