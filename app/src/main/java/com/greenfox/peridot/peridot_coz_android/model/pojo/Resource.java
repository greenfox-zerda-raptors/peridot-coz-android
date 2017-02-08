package com.greenfox.peridot.peridot_coz_android.model.pojo;

import java.util.ArrayList;

public class Resource {

    private String type;
    private int amount;
    private ArrayList<Building> buildings;

    public Resource() {}

    public Resource(String type, int amount, ArrayList<Building> buildings) {
        this.type = type;
        this.amount = amount;
        this.buildings = buildings;
    }

    public String getType() {return type;}

    public void setType(String type) {this.type = type;}

    public int getAmount() {return amount;}

    public void setAmount(int amount) {this.amount = amount;}

    public ArrayList<Building> getBuildings() {return buildings;}

    public void setBuildings(ArrayList<Building> buildings) {this.buildings = buildings;}
}
