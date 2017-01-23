package com.greenfox.peridot.peridot_coz_android.model.api;

import android.util.Log;

import com.greenfox.peridot.peridot_coz_android.model.pojo.User;

import retrofit2.Call;
import retrofit2.Callback;

public class MockService implements UserInterface {

    private static final String TAG = "MockService";

    public MockService() {
    }

    @Override
    public Call<User> getUser() {
        return new MockCall<User>() {
            @Override
            public void enqueue(Callback<User> callback) {
                Log.d(TAG, "Enqueue method called!");
            }
        };
    }
}