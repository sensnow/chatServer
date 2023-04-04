package com.chatAssistant.domain;

public class User {
    private Long isAvailable;
    private String password;
    private long uid;
    private String userName;

    public User() {
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public Long getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Long isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
