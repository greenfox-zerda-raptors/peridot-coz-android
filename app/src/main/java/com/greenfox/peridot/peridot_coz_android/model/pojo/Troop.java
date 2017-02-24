package com.greenfox.peridot.peridot_coz_android.model.pojo;

import java.io.Serializable;

public class Troop implements Serializable {

    public Integer id;
    public Integer hp;
    public Integer attackPower;
    public Integer defensePower;

    public Troop(int hp, int attackPower, int defensePower) {
        this.hp = hp;
        this.attackPower = attackPower;
        this.defensePower = defensePower;
    }

    public Troop(int id, int hp, int attackPower, int defensePower) {
        this.id = id;
        this.hp = hp;
        this.attackPower = attackPower;
        this.defensePower = defensePower;
    }

    public Troop() {}

    public Troop upgradeTroop(Troop troop){
        troop.defensePower++;
        troop.attackPower++;
        return troop;
    }

    public int getHp() {return hp;}

    public void setHp(int hp) {this.hp = hp;}

    public int getAttackPower() {return attackPower;}

    public void setAttackPower(int attackPower) {this.attackPower = attackPower;}

    public int getDefensePower() {return defensePower;}

    public void setDefensePower(int defensePower) {this.defensePower = defensePower;}

    public Integer getId() {return id;}

    public void setId(Integer id) {this.id = id;}
}
