package com.greenfox.peridot.peridot_coz_android.model.pojo;

/**
 * Created by bedij on 2017. 01. 30..
 */

public class User {

    int id;
    String username;
    String kingdom;
    int points;

    public User() {}



    public User(int id, String username, String kingdom, int points) {
        this.id = id;
        this.username = username;
        this.kingdom = kingdom;
        this.points = points;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getUsername() {return username;}

    public void setUsername(String username) {this.username = username;}

    public String getKingdom() {return kingdom;}

    public void setKingdom(String kingdom) {this.kingdom = kingdom;}

    public int getPoints() {return points;}

    public void setPoints(int points) {this.points = points;}

}
