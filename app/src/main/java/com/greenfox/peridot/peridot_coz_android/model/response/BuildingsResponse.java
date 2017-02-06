package com.greenfox.peridot.peridot_coz_android.model.response;

import com.greenfox.peridot.peridot_coz_android.model.pojo.Building;

import java.util.ArrayList;

/**
 * Created by BB on 2017-02-03.
 */

public class BuildingsResponse extends Response {

    ArrayList<Building> buildings;

    public BuildingsResponse() {}

    public BuildingsResponse(ArrayList<Building> buildings) {this.buildings = buildings;}

    public ArrayList<Building> getBuildings() {return buildings;}

    public void setBuildings(ArrayList<Building> buildings) {this.buildings = buildings;}
}
