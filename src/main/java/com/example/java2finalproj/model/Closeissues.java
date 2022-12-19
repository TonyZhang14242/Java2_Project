package com.example.java2finalproj.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Timestamp;

@Entity
@Table
public class Closeissues {
    @Id
    private int id;
    private String title;
    private Timestamp createtime;
    private Timestamp closeat;

    public Closeissues() {
    }

    public Closeissues(int id, String title, Timestamp createtime, Timestamp closeat) {
        this.id = id;
        this.title = title;
        this.createtime = createtime;
        this.closeat = closeat;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    public void setCloseat(Timestamp closeat) {
        this.closeat = closeat;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }

    public Timestamp getCloseat() {
        return closeat;
    }

}
