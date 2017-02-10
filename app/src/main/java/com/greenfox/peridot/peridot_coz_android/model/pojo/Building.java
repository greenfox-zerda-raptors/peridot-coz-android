package com.greenfox.peridot.peridot_coz_android.model.pojo;

import java.io.Serializable;

public class Building implements Serializable {

    private int id;
    private String type;
    private int level;
    private int hp;

    public Building(int id, String type) {
        this.type = type;
        this.level = 1;
        this.hp = 100;
        this.id = id;
    }

    public Building(String type, int level) {
        this.type = type;
        this.level = level;
        this.hp = 100;
        this.id ++;
    }

    public int increaseLevelOfBuilding(){
        return level++;
    }

    public Building() {}

    public int getId() {return id;}

    public void setId(int id) {this.id++;}

    public String getType() {return type;}

    public void setType(String type) {this.type = type;}

    public int getLevel() {return level;}

    public void setLevel(int level) {this.level = level;}

    public int getHp() {return hp;}

    public void setHp(int hp) {this.hp = hp;}

}
