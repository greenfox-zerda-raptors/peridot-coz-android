package com.greenfox.peridot.peridot_coz_android.model.pojo;

import java.util.ArrayList;

/**
 * Created by bedij on 2017. 01. 30..
 */

public class Kingdom {

    private UserToBeRenamed user;
    private ArrayList<Building> buildings;
    private ArrayList<Resource> resources;
    private ArrayList<Troop> troops;

    public Kingdom(UserToBeRenamed user, ArrayList<Building> buildings, ArrayList<Resource> resources, ArrayList<Troop> troops) {
        this.user = user;
        this.buildings = buildings;
        this.resources = resources;
        this.troops = troops;
    }

    public Kingdom() {}

    public UserToBeRenamed getUser() {return user;}

    public void setUser(UserToBeRenamed user) {this.user = user;}

    public ArrayList<Building> getBuildings() {return buildings;}

    public void setBuildings(ArrayList<Building> buildings) {this.buildings = buildings;}

    public ArrayList<Resource> getResources() {return resources;}

    public void setResources(ArrayList<Resource> resources) {this.resources = resources;}

    public ArrayList<Troop> getTroops() {return troops;}

    public void setTroops(ArrayList<Troop> troops) {this.troops = troops;}
}
