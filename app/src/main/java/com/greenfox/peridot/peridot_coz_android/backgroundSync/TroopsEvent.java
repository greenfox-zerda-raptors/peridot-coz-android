package com.greenfox.peridot.peridot_coz_android.backgroundSync;

import com.greenfox.peridot.peridot_coz_android.model.pojo.Troop;

import java.util.ArrayList;

public class TroopsEvent {

    ArrayList<Troop> troops;

    public TroopsEvent(ArrayList<Troop> troops) {
        this.troops = troops;
    }
}
