package com.buzzfeed.project.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//News source model. It has id and name.
@Entity
public class Source {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long identity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String id;
    private String name;


}
