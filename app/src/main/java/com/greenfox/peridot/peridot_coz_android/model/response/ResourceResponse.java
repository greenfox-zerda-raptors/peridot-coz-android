package com.greenfox.peridot.peridot_coz_android.model.response;

import com.greenfox.peridot.peridot_coz_android.model.pojo.Resource;

public class ResourceResponse extends Response {
    Resource resource;

    public ResourceResponse(Resource resource) {
        this.resource = resource;
    }
}
