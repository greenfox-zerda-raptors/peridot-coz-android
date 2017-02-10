package com.greenfox.peridot.peridot_coz_android.model.response;

import com.greenfox.peridot.peridot_coz_android.model.pojo.Resource;

import java.util.ArrayList;

public class ResourceResponse extends Response {
    ArrayList<Resource> resources;

    public ResourceResponse(ArrayList<Resource> resources) {
        this.resources = resources;
    }

    public ArrayList<Resource> getResources() {
        return resources;
    }

    public void setResources(ArrayList<Resource> resources) {
        this.resources = resources;
    }
}
