package com.buzzfeed.project.domain;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
public class User {
    private static final long serialVersionUID = 1L;

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
    @NotEmpty(message = "Mandatory field : Email")
    @Email(message = "email should be a valid email")
    private String emailId;

    @NotEmpty(message = "Mandatory field : Phone Number")
    private String phoneNumber;

//    public User(Integer uid, @NotEmpty(message = "Mandatory field : Username") String userName, @NotEmpty(message = "Mandatory field : Email") @Email(message = "email should be a valid email") String emailId, @NotEmpty(message = "Mandatory field : Phone Number") String phoneNumber, String password, String location, List<String> preferredCategory, String gender) {
//        this.uid = uid;
//        this.userName = userName;
//        this.emailId = emailId;
//        this.phoneNumber = phoneNumber;
//        this.password = password;
//        this.location = location;
//        this.preferredCategory = preferredCategory;
//        this.gender = gender;
//    }

    private String password;

    private String location;

    @ElementCollection
    private List<String> preferredCategory;

    private String gender;





}
