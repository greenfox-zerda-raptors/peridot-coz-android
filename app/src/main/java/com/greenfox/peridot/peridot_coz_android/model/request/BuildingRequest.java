package com.greenfox.peridot.peridot_coz_android.model.request;

public class BuildingRequest {

    String type;

    public BuildingRequest() {}

    public BuildingRequest(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
