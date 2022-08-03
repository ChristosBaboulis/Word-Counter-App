package com.example.objects;

public class WordFreq {
    private final String word;
    private int frequency;

    public WordFreq(String word) {
        this.word = word;
    }

    public String key(){
        return word;
    }

    public int getFreq() {
        return frequency;
    }

    public void incFreq(){
        frequency ++;
    }

    public String toString() {
        return "Word= " + word +
                ", frequency= " + frequency;
    }
}
