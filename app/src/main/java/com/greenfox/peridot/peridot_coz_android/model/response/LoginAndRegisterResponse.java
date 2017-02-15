package com.greenfox.peridot.peridot_coz_android.model.response;

import com.greenfox.peridot.peridot_coz_android.model.pojo.User;

public class LoginAndRegisterResponse extends Response {

/*    User user;*/
    String token;

    public LoginAndRegisterResponse() {}

    public LoginAndRegisterResponse(String token) {this.token = token;}

    public String getToken() {return token;}

    public void setToken(String token) {this.token = token;}

    /*    public User getUser() {return user;}

    public void setUser(User user) {this.user = user;}*/
}
