package com.buzzfeed.project.domain;


import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String newsIdentifier;
    @Column
    @NotEmpty(message = "Mandatory field : News Title")
    private String newsTitle;
    @NotEmpty(message = "Mandatory field : News Category")
    @Column
    private String newsCategory;
    @NotEmpty(message = "Mandatory field : Location")
    @Column
    private String location;
    @NotEmpty(message = "Mandatory field : News Description")
    @Column
    private String newsDesc;



    public News(Integer id, String newsIdentifier, @NotEmpty(message = "Mandatory field : News Title") String newsTitle, @NotEmpty(message = "Mandatory field : News Category") String newsCategory, @NotEmpty(message = "Mandatory field : Location") String location, @NotEmpty(message = "Mandatory field : News Description") String newsDesc) {
        this.id = id;
        this.newsIdentifier = newsIdentifier;
        this.newsTitle = newsTitle;
        this.newsCategory = newsCategory;
        this.location = location;
        this.newsDesc = newsDesc;
//        this.createdAt = createdAt;
//        this.updatedAt = updatedAt;
    }

    public News() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNewsIdentifier() {
        return newsIdentifier;
    }

    public void setNewsIdentifier(String newsIdentifier) {
        this.newsIdentifier = newsIdentifier;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsCategory() {
        return newsCategory;
    }

    public void setNewsCategory(String newsCategory) {
        this.newsCategory = newsCategory;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNewsDesc() {
        return newsDesc;
    }

    public void setNewsDesc(String newsDesc) {
        this.newsDesc = newsDesc;
    }


}
