package com.buzzfeed.project.domain;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//User Domain
@Entity
@Table(name = "USER")
public class User {
    // private static final long serialVersionUID = 1L;
//   public User() {}
//


    public User() {
        //Create categorymap for each user while creation.
        HashMap<String, Double> categorymap = new HashMap<String, Double>();
        categorymap.put("business", 0.0);
        categorymap.put("science", 0.0);
        categorymap.put("health", 0.0);
        categorymap.put("technology", 0.0);
        categorymap.put("entertainment", 0.0);
        categorymap.put("sports", 0.0);
        this.setMap(categorymap);
        // System.out.println("cate map"+categorymap + this.getMap());

    }


    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<String> getPreferredCategory() {
        return preferredCategory;
    }

    public void setPreferredCategory(List<String> preferredCategory) {
        this.preferredCategory = preferredCategory;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uid;

    @NotEmpty(message = "Mandatory field : Username")
    private String userName;

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


    private String lastname;
    @NotEmpty(message = "Mandatory field : Email")
    @Email(message = "email should be a valid email")
    private String emailId;

    @NotEmpty(message = "Mandatory field : Phone Number")
    private String phoneNumber;


    private String password;

    private String location;

    @ElementCollection
    private List<String> preferredCategory;

    private String gender;


    public Map<String, Double> getMap() {
        return map;
    }

    public void setMap(Map<String, Double> map) {
        this.map = map;
    }

    @ElementCollection
    @MapKeyColumn(name = "category_name")
    private Map<String, Double> map = new HashMap<String, Double>();


    //Users has list of liked news articles which is in relationship many - many as they can like many news
    @ManyToMany
    private List<DNews> likedList;

    public List<DNews> getLikedList() {
        return likedList;
    }

    public void setLikedList(List<DNews> likedList) {
        this.likedList = likedList;
    }

    public List<DNews> getFavoriteList() {
        return favoriteList;
    }

    public void setFavoriteList(List<DNews> favoriteList) {
        this.favoriteList = favoriteList;
    }

    //Users has list of favorite news articles which is in relationship
    // many - many as they can add many news to favorites.
    @ManyToMany
    private List<DNews> favoriteList;

    public List<DNews> getSavedList() {
        return savedList;
    }

    public void setSavedList(List<DNews> savedList) {
        this.savedList = savedList;
    }

    //Similarly many-many relationship with saved news articles.
    @ManyToMany
    private List<DNews> savedList;

}
