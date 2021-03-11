package com.rson.rsoncoffeeshop;

import com.google.gson.annotations.SerializedName;

public class User {
    private Long id;
    @SerializedName("username")
    private String userName;
    private String password;

    public User() {}

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public User(String userName, Long id) {
        this.userName = userName;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
