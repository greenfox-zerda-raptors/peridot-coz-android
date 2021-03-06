package com.greenfox.peridot.peridot_coz_android.api;

import com.greenfox.peridot.peridot_coz_android.model.pojo.Building;
import com.greenfox.peridot.peridot_coz_android.model.pojo.Kingdom;
import com.greenfox.peridot.peridot_coz_android.model.pojo.Resource;
import com.greenfox.peridot.peridot_coz_android.model.pojo.Troop;
import com.greenfox.peridot.peridot_coz_android.model.pojo.User;
import com.greenfox.peridot.peridot_coz_android.model.response.BuildingsResponse;
import com.greenfox.peridot.peridot_coz_android.model.response.KingdomResponse;
import com.greenfox.peridot.peridot_coz_android.model.response.ResourceResponse;
import com.greenfox.peridot.peridot_coz_android.model.response.TroopsResponse;
import com.greenfox.peridot.peridot_coz_android.model.response.UserResponse;
import com.greenfox.peridot.peridot_coz_android.model.response.UsersResponse;
import java.util.ArrayList;
import java.util.Arrays;
import dagger.Module;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Path;

@Module
public class MockService implements ApiService {

    private User user = new User (1, "aaa", "aaa's Kingdom", 10);
    private static final String TAG = "MockService";
    private Building building = new Building("Townhall");
    private ArrayList<Building> buildings = new ArrayList<>(Arrays.asList(new Building(1,"Townhall"), new Building(2,"Farm"), new Building(3,"Farm"),new Building(4,"Mine"),new Building(5,"Mine"),new Building(6,"Barrack"),new Building(7,"Barrack"),new Building(8,"Farm"),new Building(9,"Mine"), new Building(10,"Townhall") ));
    private ArrayList<User> users = new ArrayList<>(Arrays.asList(new User("Anna"), new User("Balint"), new User("Bea"),new User("Szilvi")));
    private ArrayList<Resource> resources = new ArrayList<>(Arrays.asList(new Resource("food", 10, buildings),new Resource("gold", 20, buildings)));
    private Troop troop1 = new Troop(1, 5, 5, 5);
    private Troop troop2 = new Troop(2, 10, 8, 2);
    private Troop troop3 = new Troop(3, 20, 3, 7);
    private ArrayList<Troop> troops = new ArrayList<>(Arrays.asList(troop1, troop2, troop3));

    public MockService() {}

    @Override
    public Call<UserResponse> searchUser() {
        return null;
    }

    @Override
    public Call<UsersResponse> getUsers() {
        return new MockCall<UsersResponse>() {
            @Override
            public void enqueue(Callback<UsersResponse> callback) {
                Response<UsersResponse> r = Response.success(new UsersResponse(users));
                callback.onResponse(this, r);
            }
        };
    }

    @Override
    public Call<KingdomResponse> getKingdom() {
        return new MockCall<KingdomResponse>() {
            @Override
            public void enqueue(Callback<KingdomResponse> callback) {
                Response<KingdomResponse> r = Response.success(new KingdomResponse(new Kingdom(user, buildings, resources, troops)));
                callback.onResponse(this, r);
            }
        };
    }

    @Override
    public Call<TroopsResponse> getTroops() {
        return new MockCall<TroopsResponse>() {
            @Override
            public void enqueue(Callback<TroopsResponse> callback) {
                Response<TroopsResponse> r = Response.success(new TroopsResponse(troops));
                callback.onResponse(this, r);
            }
        };
    }

    @Override
    public Call<Troop> getTroopDetail(final int troopId) {
        return new MockCall<Troop>() {
            @Override
            public void enqueue(Callback<Troop> callback) {
                Response<Troop> r = null;
                if (troopId == 1) {
                    r = Response.success(troop1);
                } else if (troopId == 2) {
                    r = Response.success(troop2);
                } else if (troopId == 3) {
                    r = Response.success(troop3);
                }
                callback.onResponse(this, r);
            }
        };
    }

    @Override
    public Call<Troop> trainTroop() {
        return new MockCall<Troop>() {
            @Override
            public void enqueue(Callback<Troop> callback) {
                Response<Troop> r = Response.success(new Troop());
                callback.onResponse(this, r);
            }
        };
    }

    @Override
    public Call<Troop> upgradeTroop(@Body final Troop troop) {
        return new MockCall<Troop>() {
            @Override
            public void enqueue(Callback<Troop> callback) {
                Response<Troop> r = Response.success(troop.upgradeTroop(troop));
                callback.onResponse(this, r);
            }
        };
    }

    @Override
    public Call<BuildingsResponse> getBuildings() {
        return new MockCall<BuildingsResponse>() {
            @Override
            public void enqueue(Callback<BuildingsResponse> callback) {
                Response<BuildingsResponse> r = Response.success(new BuildingsResponse(buildings));
                callback.onResponse(this, r);
            }
        };
    }

    @Override
    public Call<Building> getDetailsOfBuilding(final int buildingId) {
        return new MockCall<Building>() {
            @Override
            public void enqueue(Callback<Building> callback) {
                Response<Building> r = Response.success(buildings.get(buildingId-1));
                callback.onResponse(this, r);
            }
        };
    }

    @Override
    public Call<Building> createBuilding(@Body final Building building) {
        return new MockCall<Building>() {
            @Override
            public void enqueue(Callback<Building> callback) {
                Response<Building> r = Response.success(building);
                callback.onResponse(this, r);
            }
        };
    }

    @Override
    public Call<Building> upgradeBuilding(@Body final Building building) {
        return new MockCall<Building>() {
            @Override
            public void enqueue(Callback<Building> callback) {
                Response<Building> r = Response.success(building.upgradeBuilding(building));
                callback.onResponse(this,r);
            }
        };
    }

    @Override
    public Call<ResourceResponse> getResource() {
        return new MockCall<ResourceResponse>() {
            @Override
            public void enqueue(Callback<ResourceResponse> callback) {
                Response<ResourceResponse> r = Response.success(new ResourceResponse(resources));
                callback.onResponse(this, r);
            }
        };
    }

    @Override
    public Call<ResourceResponse> getResourceType(@Path("type") String type) {
        return new MockCall<ResourceResponse>() {
            @Override
            public void enqueue(Callback<ResourceResponse> callback) {
                Response<ResourceResponse> r = Response.success(new ResourceResponse(resources));
                callback.onResponse(this, r);
            }
        };
    }

    @Override
    public Call<BuildingsResponse> syncBuildings() {
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
                buildings.add(new Building("Farm"));
                buildings.add(new Building("Mine"));
                buildings.add(new Building("Mine"));
                buildings.add(new Building("Barrack"));
                buildings.add(new Building("Barrack"));
                buildings.add(new Building("Farm"));
                buildings.add(new Building("Mine"));
                buildings.add(new Building("Mine"));
                buildings.add(new Building("Barrack"));
                buildings.add(new Building("Barrack"));
                Response<BuildingsResponse> r = Response.success(new BuildingsResponse(buildings));
                callback.onResponse(this, r);
            }
        };
    }

//    @Override
//    public Call<Building> upgradeBuilding(final BuildingRequest buildingRequest) {
//        return new MockCall<Building>() {
//            @Override
//            public void enqueue(Callback<Building> callback) {
//                Bundle bundle = getArguments();
//                Building buildingFromPrevFrag = (Building) bundle.getSerializable("building");
//                Response<Building> r = Response.success(buildingFromPrevFrag.increaseLevelOfBuilding());
//                callback.onResponse(this, r);
//            }
//        };
//    }
}

