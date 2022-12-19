package com.example.java2finalproj.controller;


public class Developers {
    String name;
    int commitnumber;
    Developers(String name, int commitnumber){
        this.name = name;
        this.commitnumber = commitnumber;
    }

    public String getName() {
        return name;
    }

    public int getCommitnumber() {
        return commitnumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCommitnumber(int commitnumber) {
        this.commitnumber = commitnumber;
    }
}
