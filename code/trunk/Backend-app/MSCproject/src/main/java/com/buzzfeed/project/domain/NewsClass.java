package com.buzzfeed.project.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class NewsClass {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
}
