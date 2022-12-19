package com.example.java2finalproj.controller;

public class TopIssueWord {
    private String word;
    private int number;

    public TopIssueWord(String word, int number) {
        this.word = word;
        this.number = number;
    }

    public String getWord() {
        return word;
    }

    public int getNumber() {
        return number;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
