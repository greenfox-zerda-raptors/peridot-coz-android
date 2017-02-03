package com.greenfox.peridot.peridot_coz_android.model.api;

import com.greenfox.peridot.peridot_coz_android.model.pojo.Building;
import com.greenfox.peridot.peridot_coz_android.model.pojo.Kingdom;
import com.greenfox.peridot.peridot_coz_android.model.pojo.Resource;
import com.greenfox.peridot.peridot_coz_android.model.pojo.Troop;
import com.greenfox.peridot.peridot_coz_android.model.pojo.User;
import com.greenfox.peridot.peridot_coz_android.model.request.LoginRequest;
import com.greenfox.peridot.peridot_coz_android.model.request.RegisterRequest;
import com.greenfox.peridot.peridot_coz_android.model.response.BuildingsResponse;
import com.greenfox.peridot.peridot_coz_android.model.response.Error;
import com.greenfox.peridot.peridot_coz_android.model.response.LoginAndRegisterResponse;

import java.util.ArrayList;
import java.util.Arrays;

import dagger.Module;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Module
public class MockService implements ApiService {

    private static final String TAG = "MockService";

    private Building building = new Building("townhall");
    private ArrayList<Building> buildings = new ArrayList<>();
    private ArrayList<Resource> resources = new ArrayList<>(Arrays.asList(new Resource("food", 10, buildings)));
    private ArrayList<Troop> troops = new ArrayList<>(Arrays.asList(new Troop(5, 5, 5)));

    public MockService() {
    }

    @Override
    public Call<LoginAndRegisterResponse> login(final LoginRequest loginRequest) {
        return new MockCall<LoginAndRegisterResponse>() {
            @Override
            public void enqueue(Callback<LoginAndRegisterResponse> callback) {
                if (loginRequest.getUsername().equals("aaa")
                        && loginRequest.getPassword().equals("aaa")) {
                    Response<LoginAndRegisterResponse> r = Response.success(new LoginAndRegisterResponse());
                    r.body().setUser(new User(1, "aaa", "aaa's kingdom", 0));
                    callback.onResponse(this, r);
                } else if (!loginRequest.getUsername().equals("aaa")) {
                    Response<LoginAndRegisterResponse> r = Response.success(new LoginAndRegisterResponse());
                    Error error = new Error();
                    error.setUsername("No such user exists");
                    r.body().setErrors(error);
                    callback.onResponse(this, r);
                } else if (!loginRequest.getPassword().equals("aaa")) {
                    Response<LoginAndRegisterResponse> r = Response.success(new LoginAndRegisterResponse());
                    Error error = new Error();
                    error.setPassword("Wrong password");
                    r.body().setErrors(error);
                    callback.onResponse(this, r);
                }
            }
        };
    }

    @Override
    public Call<Kingdom> getKingdom(int userId) {
        ///TODO this.
        return null;
//        return new MockCall<Kingdom>() {
//            @Override
//            public void enqueue(Callback<Kingdom> callback) {
//                Response<Kingdom> r = Response.success(new Kingdom(user, buildings, resources, troops));
//                callback.onResponse(this, r);
//            }
//        };
    }

    @Override
    public Call<LoginAndRegisterResponse> register(RegisterRequest registerRequest) {
        /// TODO this.
        return null;
    }

    @Override
    public Call<BuildingsResponse> getBuildings(int userId) {
        return new MockCall<BuildingsResponse>() {
            @Override
            public void enqueue(Callback<BuildingsResponse> callback) {
                buildings.add(new Building("Farm"));
                buildings.add(new Building("Farm"));
                buildings.add(new Building("Mine"));
                buildings.add(new Building("Mine"));
                buildings.add(new Building("Barrack"));
                buildings.add(new Building("Barrack"));
                buildings.add(new Building("Farm"));
                buildings.add(new Building("Mine"));
                buildings.add(new Building("Townhall"));
                Response<BuildingsResponse> v = Response.success(new BuildingsResponse(buildings));
                callback.onResponse(this, v);
            }
        };
    }

    @Override
    public Call<Building> getDetailsOfBuilding(int userId, int buildingId) {
        return new MockCall<Building>() {
            @Override
            public void enqueue(Callback<Building> callback) {
                Response<Building> v = Response.success(building);
                callback.onResponse(this, v);
            }
        };
    }

    @Override
    public Call<Building> createBuilding(int userId, Building building) {
        return new MockCall<Building>() {
            @Override
            public void enqueue(Callback<Building> callback) {
                Response<Building> v = Response.success(new Building("townhall"));
                callback.onResponse(this, v);
            }
        };
    }

    @Override
    public Call<Building> upgradeBuilding(int userId, int buildingId, final Building building) {
        return new MockCall<Building>() {
            @Override
            public void enqueue(Callback<Building> callback) {
                Response<Building> v = Response.success(new Building("townhall", building.increaseLevelOfBuilding()));
                callback.onResponse(this, v);
            }
        };
    }
}
