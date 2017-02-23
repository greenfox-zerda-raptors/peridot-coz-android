package com.greenfox.peridot.peridot_coz_android.backgroundSync;

import com.greenfox.peridot.peridot_coz_android.model.pojo.Building;

import java.util.ArrayList;

public class BuildingsEvent {

    ArrayList<Building> buildings;

    public BuildingsEvent(ArrayList<Building> buildings) {
        this.buildings = buildings;
    }
}
