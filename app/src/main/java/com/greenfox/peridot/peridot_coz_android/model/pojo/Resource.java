package com.greenfox.peridot.peridot_coz_android.model.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by bedij on 2017. 01. 30..
 */

public class Resource {

    @SerializedName("type")
    private String type;
    @SerializedName("amount")
    private int amount;
    @SerializedName("buildings")
    private ArrayList<Building> buildings;

    public Resource() {}

    public Resource(String type, ArrayList<Building> buildings) {
        this.type = type;
        this.buildings = buildings;
    }

    public String getType() {return type;}

    public void setType(String type) {this.type = type;}

    public int getAmount() {return amount;}

    public void setAmount(int amount) {this.amount = amount;}

    public ArrayList<Building> getBuildings() {return buildings;}

    public void setBuildings(ArrayList<Building> buildings) {this.buildings = buildings;}
}
