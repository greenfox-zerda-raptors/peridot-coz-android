package com.greenfox.peridot.peridot_coz_android.model.response;

import com.greenfox.peridot.peridot_coz_android.model.pojo.Building;

/**
 * Created by bedij on 2017. 02. 10..
 */
public class BuildingNewResponse {

    Building building;

    public BuildingNewResponse() {
    }

    public BuildingNewResponse(Building building) {
        this.building = building;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }
}
