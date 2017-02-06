package com.greenfox.peridot.peridot_coz_android.model.response;

import com.greenfox.peridot.peridot_coz_android.model.pojo.User;

public class LoginAndRegisterResponse extends Response {
    User user;

    public LoginAndRegisterResponse() {}

    public LoginAndRegisterResponse(User user) {this.user = user;}

    public User getUser() {return user;}

    public void setUser(User user) {this.user = user;}
}
