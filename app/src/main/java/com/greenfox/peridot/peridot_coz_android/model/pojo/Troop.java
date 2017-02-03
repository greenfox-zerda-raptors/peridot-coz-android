package com.greenfox.peridot.peridot_coz_android.model.pojo;

/**
 * Created by bedij on 2017. 01. 30..
 */

public class Troop {

    public Integer hp;
    public Integer attackPower;
    public Integer defensePower;

    public Troop(int hp, int attackPower, int defensePower) {
        this.hp = hp;
        this.attackPower = attackPower;
        this.defensePower = defensePower;
    }

    public Troop() {}

    public int getHp() {return hp;}

    public void setHp(int hp) {this.hp = hp;}

    public int getAttackPower() {return attackPower;}

    public void setAttackPower(int attackPower) {this.attackPower = attackPower;}

    public int getDefensePower() {return defensePower;}

    public void setDefensePower(int defensePower) {this.defensePower = defensePower;}
}
