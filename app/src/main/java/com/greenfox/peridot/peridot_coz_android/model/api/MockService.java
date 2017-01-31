package com.greenfox.peridot.peridot_coz_android.model.api;

import com.greenfox.peridot.peridot_coz_android.model.pojo.Building;
import com.greenfox.peridot.peridot_coz_android.model.pojo.Kingdom;
import com.greenfox.peridot.peridot_coz_android.model.pojo.Resource;
import com.greenfox.peridot.peridot_coz_android.model.pojo.Troop;
import com.greenfox.peridot.peridot_coz_android.model.pojo.User;

import java.util.ArrayList;
import java.util.Arrays;

import dagger.Module;
import dagger.Provides;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Module
public class MockService implements ApiService {

    private static final String TAG = "MockService";

    private User user = new User("aaa","aaa");

    private ArrayList<Building> buildings = new ArrayList<>(Arrays.asList(new Building("townhall")));

    private ArrayList<Resource> resources = new ArrayList<>(Arrays.asList(new Resource("food", 10, buildings)));

    private ArrayList<Troop> troops = new ArrayList<>(Arrays.asList(new Troop(5, 5, 5)));

    public MockService() {}

    @Override
    public Call<User> getUser() {
        return new MockCall<User>() {
            @Override
            public void enqueue(Callback<User> callback) {
                Response<User> r = Response.success(user);
                callback.onResponse(this, r);
            }
        };
    }
    @Override
    public Call<Kingdom> getKingdom() {
        return new MockCall<Kingdom>() {
            @Override
            public void enqueue(Callback<Kingdom> callback) {
                Response<Kingdom> r = Response.success(new Kingdom(user, buildings, resources, troops));
                callback.onResponse(this, r);
            }
        };
    }
}
