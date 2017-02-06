package com.greenfox.peridot.peridot_coz_android.model.response;

import com.greenfox.peridot.peridot_coz_android.model.pojo.Kingdom;

/**
 * Created by mozgaanna on 31/01/17.
 */

public class KingdomResponse extends Response{
    Kingdom kingdom;

    public Kingdom getKingdom() {
        return kingdom;
    }

    public void setKingdom(Kingdom kingdom) {
        this.kingdom = kingdom;
    }

    public KingdomResponse(Kingdom kingdom) {
        this.kingdom = kingdom;
    }

    public KingdomResponse() {
    }
}
