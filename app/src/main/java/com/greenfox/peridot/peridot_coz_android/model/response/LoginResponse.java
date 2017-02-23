package com.greenfox.peridot.peridot_coz_android.model.response;

public class LoginResponse extends Response {

    String token;

    public LoginResponse() {}

    public LoginResponse(String token) {this.token = token;}

    public String getToken() {return token;}

    public void setToken(String token) {this.token = token;}

}
