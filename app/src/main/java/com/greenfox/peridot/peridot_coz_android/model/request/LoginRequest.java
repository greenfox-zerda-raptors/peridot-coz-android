package com.greenfox.peridot.peridot_coz_android.model.request;

public class LoginRequest {

    String password;
    String username;

    public LoginRequest() {}

    public LoginRequest(String password, String username) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {return username;}

    public String getPassword() {return password;}

    public void setUsername(String username) {this.username = username;}

    public void setPassword(String password) {this.password = password;}
}
