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

    //Taken code from https://pengw00.github.io/2019/05/31/stepsToBuildSpringbootRESTAPI/
//    @Column(nullable = false, updatable = false)
//    @Temporal(TemporalType.TIMESTAMP)
//    @CreatedDate
//    private LocalDateTime createdAt;
//
//    @Column(nullable = false)
//    @Temporal(TemporalType.TIMESTAMP)
//    @LastModifiedDate
//    private LocalDateTime updatedAt;

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

//    public Date getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(Date createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public Date getUpdatedAt() {
//        return updatedAt;
//    }
//
//    public void setUpdatedAt(Date updatedAt) {
//        this.updatedAt = updatedAt;
//    }
}
