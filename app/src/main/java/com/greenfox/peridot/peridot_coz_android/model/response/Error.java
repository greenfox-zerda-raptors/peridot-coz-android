package com.greenfox.peridot.peridot_coz_android.model.response;

public class Error {
    String username;
    String password;
    String id;
    String upgrade;
    String attack;

    public Error() {}

    public String getUsername() {return username;}

    public void setUsername(String username) {this.username = username;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    public String getId() {return id;}

    public void setId(String id) {this.id = id;}

    public String getUpgrade() {return upgrade;}

    public void setUpgrade(String upgrade) {this.upgrade = upgrade;}

    public String getAttack() {return attack;}

    public void setAttack(String attack) {this.attack = attack;}
}
