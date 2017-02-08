package com.greenfox.peridot.peridot_coz_android.model.response;

import com.greenfox.peridot.peridot_coz_android.model.pojo.Troop;
import java.util.ArrayList;

public class TroopsResponse extends Response {

    ArrayList<Troop> troops;

    public TroopsResponse() {}

    public TroopsResponse(ArrayList<Troop> troops) {
        this.troops = troops;
    }

    public ArrayList<Troop> getTroops() {return troops;}

    public void setTroops(ArrayList<Troop> troops) {this.troops = troops;}
}
