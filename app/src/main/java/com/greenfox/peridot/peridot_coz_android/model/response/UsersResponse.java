package com.greenfox.peridot.peridot_coz_android.model.response;

import com.greenfox.peridot.peridot_coz_android.model.pojo.User;

import java.util.ArrayList;

/**
 * Created by mozgaanna on 10/02/17.
 */

public class UsersResponse extends Response {

    ArrayList<User> users;

    public UsersResponse(){

    }

    public UsersResponse(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }
}
