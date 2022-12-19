package com.example.java2finalproj.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Timestamp;


@Entity
@Table
public class Openissues {
    @Id
    private int id;
    private String title;
    private Timestamp createtime;
    public Openissues(){

    }

    public Openissues(int id, String title, Timestamp createtime){
        this.id = id;
        this.title = title;
        this.createtime = createtime;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }


    private void setId(int id) {
        this.id = id;
    }


    public int getId() {
        return id;
    }


}
