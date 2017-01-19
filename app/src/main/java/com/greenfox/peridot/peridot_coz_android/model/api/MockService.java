package com.greenfox.peridot.peridot_coz_android.model.api;

import com.greenfox.peridot.peridot_coz_android.model.pojo.User;

import retrofit2.Call;

public class MockService implements UserInterface {

    public MockService() {
    }

    @Override
    public Call<User> getUser() {
        return null;
    }
}
