package com.greenfox.peridot.peridot_coz_android.model.pojo;

import com.google.gson.annotations.SerializedName;


/// TODO upadate this class' name after merge
public class UserToBeRenamed {

    String username;
    String password;
    String kingdomName;

    public UserToBeRenamed(String username, String password, String kingdomName) {
        this.username = username;
        this.password = password;
        this.kingdomName = kingdomName;
    }

    public UserToBeRenamed() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getKingdomName() {
        return kingdomName;
    }

    public void setKingdomName(String kingdomName) {
        this.kingdomName = kingdomName;
    }
}
