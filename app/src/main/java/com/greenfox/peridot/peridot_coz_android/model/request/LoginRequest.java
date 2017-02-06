package com.greenfox.peridot.peridot_coz_android.model.request;

/**
 * Created by BB on 2017-02-02.
 */

public class LoginRequest {
    String username;
    String password;

    public LoginRequest() {}

    public LoginRequest(String password) {
        this.password = password;
    }

    public String getUsername() {return username;}

    public String getPassword() {return password;}

    public void setUsername(String username) {this.username = username;}

    public void setPassword(String password) {this.password = password;}
}
