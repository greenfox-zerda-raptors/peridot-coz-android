package com.greenfox.peridot.peridot_coz_android.model.request;

public class RegisterRequest {

    String username;
    String password;
    String kingdomName;

    public RegisterRequest(String username, String password, String kingdomName) {
        this.username = username;
        this.password = password;
        this.kingdomName = kingdomName;
    }

    public RegisterRequest() {}

    public String getUsername() {return username;}

    public void setUsername(String username) {this.username = username;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    public String getKingdomName() {return kingdomName;}

    public void setKingdomName(String kingdomName) {this.kingdomName = kingdomName;}
}
