package com.buzzfeed.project.domain;

public class userfile {
    public int getUSER_UID() {
        return USER_UID;
    }

    public void setUSER_UID(int USER_UID) {
        this.USER_UID = USER_UID;
    }

    public Double getMAP() {
        return MAP;
    }

    public void setMAP(Double MAP) {
        this.MAP = MAP;
    }

    public String getCATEGORY_NAME() {
        return CATEGORY_NAME;
    }

    public void setCATEGORY_NAME(String CATEGORY_NAME) {
        this.CATEGORY_NAME = CATEGORY_NAME;
    }

    private int USER_UID;
    private Double MAP;
    private String CATEGORY_NAME;
}
