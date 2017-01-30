package com.greenfox.peridot.peridot_coz_android.model.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bedij on 2017. 01. 30..
 */

public class User {

    @SerializedName("UserName")
    String username;
    @SerializedName("Password")
    String password;

    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
