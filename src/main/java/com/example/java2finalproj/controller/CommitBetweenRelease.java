package com.example.java2finalproj.controller;

public class CommitBetweenRelease {
    String name1;
    String name2;
    int num;

    public CommitBetweenRelease(String name1, String name2, int num) {
        this.name1 = name1;
        this.name2 = name2;
        this.num = num;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName1() {
        return name1;
    }

    public String getName2() {
        return name2;
    }

    public int getNum() {
        return num;
    }
}
