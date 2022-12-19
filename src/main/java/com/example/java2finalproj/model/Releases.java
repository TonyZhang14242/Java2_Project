package com.example.java2finalproj.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Timestamp;

@Entity
@Table
public class Releases {
    @Id
    private int id;
    private String name;
    private Timestamp time;

    public Releases() {
    }

    public Releases(int id, String name, Timestamp time) {
        this.id = id;
        this.name = name;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
