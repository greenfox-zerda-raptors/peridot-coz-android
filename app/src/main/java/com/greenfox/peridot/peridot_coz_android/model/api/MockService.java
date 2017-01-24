package com.greenfox.peridot.peridot_coz_android.model.api;

import com.greenfox.peridot.peridot_coz_android.model.pojo.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MockService implements UserInterface{

    private static final String TAG = "MockService";

    public MockService() {}

    @Override
    public Call<User> getUser() {
        return new MockCall<User>() {
            @Override
            public void enqueue(Callback<User> callback) {
                Response<User> r = Response.success(new User("aaa","aaa"));
                callback.onResponse(this, r);
            }
        };
    }
}