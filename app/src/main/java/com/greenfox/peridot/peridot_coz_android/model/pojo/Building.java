package com.greenfox.peridot.peridot_coz_android.model.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by bedij on 2017. 01. 30..
 */

public class Building {

    @SerializedName("id")
    private int id;
    @SerializedName("type")
    private String type;
    @SerializedName("level")
    private int level;
    @SerializedName("hp")
    private int hp;

    public Building(String type) {
        this.type = type;
        this.level = 0;
        this.hp = 0;
    }

    public Building() {
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getType() {return type;}

    public void setType(String type) {this.type = type;}

    public int getLevel() {return level;}

    public void setLevel(int level) {this.level = level;}

    public int getHp() {return hp;}

    public void setHp(int hp) {this.hp = hp;}
}
