package com.buzzfeed.project.domain;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

public class categ {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    public Map<String, Double> getCategoryweightMap() {
        return categoryweightMap;
    }

    public void setCategoryweightMap(Map<String, Double> categoryweightMap) {
        this.categoryweightMap = categoryweightMap;
    }

    @ElementCollection
    @Column
    Map<String, Double> categoryweightMap = new HashMap<String, Double>() {{
        put("a", 0.0);
        put("c", 0.0);
    }};

    public categ() {
    }


//    void setCategoryweightMap(Map<String, Integer> categoryweightMap){
//        this.categoryweightMap=categoryweightMap;
//    };

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getId() {
        return id;
    }
}
