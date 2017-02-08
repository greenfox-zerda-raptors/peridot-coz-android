package com.greenfox.peridot.peridot_coz_android.api;

import com.greenfox.peridot.peridot_coz_android.model.pojo.Building;
import com.greenfox.peridot.peridot_coz_android.model.pojo.Kingdom;
import com.greenfox.peridot.peridot_coz_android.model.pojo.Resource;
import com.greenfox.peridot.peridot_coz_android.model.pojo.Troop;
import com.greenfox.peridot.peridot_coz_android.model.pojo.User;
import com.greenfox.peridot.peridot_coz_android.model.request.LoginRequest;
import com.greenfox.peridot.peridot_coz_android.model.request.RegisterRequest;
import com.greenfox.peridot.peridot_coz_android.model.response.BuildingsResponse;
import com.greenfox.peridot.peridot_coz_android.model.response.Error;
import com.greenfox.peridot.peridot_coz_android.model.response.KingdomResponse;
import com.greenfox.peridot.peridot_coz_android.model.response.LoginAndRegisterResponse;
import com.greenfox.peridot.peridot_coz_android.model.response.ResourceResponse;
import com.greenfox.peridot.peridot_coz_android.model.response.TroopsResponse;
import java.util.ArrayList;
import java.util.Arrays;
import dagger.Module;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Module
public class MockService implements ApiService {

    private User user = new User (1, "aaa", "aaa's Kingdom", 10);
    private static final String TAG = "MockService";
    private Building building = new Building("townhall");
    private ArrayList<Building> buildings = new ArrayList<>(Arrays.asList(new Building("townhall")));
    private ArrayList<Resource> resources = new ArrayList<>(Arrays.asList(new Resource("food", 10, buildings)));
    private Troop troop1 = new Troop(5, 5, 5);
    private Troop troop2 = new Troop(10, 8, 2);
    private Troop troop3 = new Troop(20, 3, 7);
    private ArrayList<Troop> troops = new ArrayList<>(Arrays.asList(troop1, troop2, troop3));

    public MockService() {}

    @Override
    public Call<LoginAndRegisterResponse> login(final LoginRequest loginRequest) {
        return new MockCall<LoginAndRegisterResponse>() {
            @Override
            public void enqueue(Callback<LoginAndRegisterResponse> callback) {
                if (loginRequest.getUsername().equals("aaa")
                        && loginRequest.getPassword().equals("aaa")) {
                    Response<LoginAndRegisterResponse> r = Response.success(new LoginAndRegisterResponse());
                    r.body().setUser(new User(1, "aaa","aaa's kingdom",0));
                    callback.onResponse(this, r);
                } else if (!loginRequest.getUsername().equals("aaa")){
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
    public Call<KingdomResponse> getKingdom(int userId) {
        return new MockCall<KingdomResponse>() {
            @Override
            public void enqueue(Callback<KingdomResponse> callback) {
                Response<KingdomResponse> r = Response.success(new KingdomResponse(new Kingdom(user, buildings, resources, troops)));
                callback.onResponse(this, r);
            }
        };
    }

    @Override
    public Call<TroopsResponse> getTroops(int userId) {
        return new MockCall<TroopsResponse>() {
            @Override
            public void enqueue(Callback<TroopsResponse> callback) {
                Response<TroopsResponse> r = Response.success(new TroopsResponse(troops));
                callback.onResponse(this, r);
            }
        };
    }

    @Override
    public Call<Troop> getTroopDetail(int userId,final int troopId) {
        return new MockCall<Troop>() {
            @Override
            public void enqueue(Callback<Troop> callback) {
                Response<Troop> r = null;
                if (troopId == 1) {
                    r = Response.success(troop1);
                } else if (troopId == 2){
                    r = Response.success(troop2);
                } else if (troopId == 3){
                    r = Response.success(troop3);
                }
                callback.onResponse(this, r);
            }
        };
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
                Response<BuildingsResponse> r = Response.success(new BuildingsResponse(buildings));
                callback.onResponse(this, r);
            }
        };
    }

    @Override
    public Call<Building> getDetailsOfBuilding(int userId, int buildingId) {
        return new MockCall<Building>() {
            @Override
            public void enqueue(Callback<Building> callback) {
                Response<Building> r = Response.success(building);
                callback.onResponse(this, r);
            }
        };
    }

    @Override
    public Call<Building> createBuilding(int userId, Building building) {
        return new MockCall<Building>() {
            @Override
            public void enqueue(Callback<Building> callback) {
                Response<Building> r = Response.success(new Building("townhall"));
                callback.onResponse(this, r);
            }
        };
    }

    @Override
    public Call<Building> upgradeBuilding(int userId, int buildingId, final Building building) {
        return new MockCall<Building>() {
            @Override
            public void enqueue(Callback<Building> callback) {
                Response<Building> r = Response.success(new Building("townhall", building.increaseLevelOfBuilding()));
                callback.onResponse(this, r);
            }
        };
    }   
  
    @Override
    public Call<LoginAndRegisterResponse> register(RegisterRequest registerRequest) {
        /// TODO this.
        return null;
    }

    @Override
    public Call<ResourceResponse> getResource() {
        return new MockCall<ResourceResponse>() {
            @Override
            public void enqueue(Callback<ResourceResponse> callback) {
                Response<ResourceResponse> r = Response.success(new ResourceResponse(new Resource("food", 40, buildings)));
                callback.onResponse(this, r);
            }
        };
    }

    @Override
    public Call<ResourceResponse> getType() {
        return new MockCall<ResourceResponse>() {
            @Override
            public void enqueue(Callback<ResourceResponse> callback) {
                Response<ResourceResponse> r = Response.success(new ResourceResponse(new Resource("gold", 20, buildings)));
                callback.onResponse(this, r);
                    }
        };
    }

    @Override
    public Call<Troop> createTroop(int userId) {
        return new MockCall<Troop>() {
            @Override
            public void enqueue(Callback<Troop> callback) {
                Response<Troop> v = Response.success(new Troop());
                callback.onResponse(this, v);
            }
        };
    }

    @Override
    public Call<Troop> upgradeTroop(int userId, int troopId) {
        return new MockCall<Troop>() {
            @Override
            public void enqueue(Callback<Troop> callback) {
                Response<Troop> v = Response.success(new Troop(10, 2, 1));
                callback.onResponse(this, v);
            }
        };
    }
}
