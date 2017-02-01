package com.greenfox.peridot.peridot_coz_android.model.pojo;

/**
 * Created by bedij on 2017. 01. 30..
 */

public class Building {

    private int id;
    private String type;
    private int level;
    private int hp;

    public Building(String type) {
        this.type = type;
        this.level = 0;
        this.hp = 0;
    }

    public Building() {}

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getType() {return type;}

    public void setType(String type) {this.type = type;}

    public int getLevel() {return level;}

    public void setLevel(int level) {this.level = level;}

    public int getHp() {return hp;}

    public void setHp(int hp) {this.hp = hp;}

}
