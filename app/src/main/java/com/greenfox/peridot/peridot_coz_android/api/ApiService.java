package com.greenfox.peridot.peridot_coz_android.api;

import com.greenfox.peridot.peridot_coz_android.model.pojo.Building;
import com.greenfox.peridot.peridot_coz_android.model.pojo.Troop;
import com.greenfox.peridot.peridot_coz_android.model.response.BuildingsResponse;
import com.greenfox.peridot.peridot_coz_android.model.response.KingdomResponse;
import com.greenfox.peridot.peridot_coz_android.model.response.ResourceResponse;
import com.greenfox.peridot.peridot_coz_android.model.response.TroopsResponse;
import com.greenfox.peridot.peridot_coz_android.model.response.UserResponse;
import com.greenfox.peridot.peridot_coz_android.model.response.UsersResponse;

import retrofit2.Call;
import retrofit2.http.*;

public interface ApiService {

    String ENDPOINT = "https://pacific-bastion-75389.herokuapp.com";

    @GET("/search/{username}")
    Call<UserResponse> searchUser();

    @GET("/realm/leaderboard")
    Call<UsersResponse> getUsers();

    @GET("/kingdom/")
    Call<KingdomResponse> getKingdom();

    @GET ("/kingdom/troops/")
    Call<TroopsResponse> getTroops();

    @GET ("/kingdom/troops/{troopId}/")
    Call<Troop> getTroopDetail(@Path("troopId") int troopId);

    @POST ("/kingdom/troops/")
    Call<Troop> createTroop();

    @PUT ("/kingdom/troops/{troopId}/")
    Call<Troop> upgradeTroop(@Path("troopId") int troopId);

    @GET("/kingdom/buildings/")
    Call<BuildingsResponse> getBuildings();

    @GET("/kingdom/buildings/{buildingId}/")
    Call<Building> getDetailsOfBuilding(@Path("buildingId")int buildingId);

    @POST("/kingdom/buildings/")
    Call<Building> createBuilding(@Body Building building);

    @POST("/kingdom/buildings/{buildingId}/")
    Call<Building> upgradeBuilding(@Path("buildingId")int buildingId, @Body Building building);
  
    @GET("/kingdom/resources/")
    Call<ResourceResponse> getResource();

    @GET("/kingdom/resources/{type}")
    Call<ResourceResponse> getType(@Path("type") String type);
  
    @GET("/kingdom/buildings/")
    Call<BuildingsResponse> syncBuildings();
}
