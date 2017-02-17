package com.greenfox.peridot.peridot_coz_android.model.response;

import com.greenfox.peridot.peridot_coz_android.model.pojo.Building;

import java.io.Serializable;
import java.util.ArrayList;

public class BuildingsResponse extends Response implements Serializable{

    ArrayList<Building> buildings;

    public BuildingsResponse() {}

    public BuildingsResponse(ArrayList<Building> buildings) {this.buildings = buildings;}

    public ArrayList<Building> getBuildings() {return buildings;}

    public void setBuildings(ArrayList<Building> buildings) {this.buildings = buildings;}
}
