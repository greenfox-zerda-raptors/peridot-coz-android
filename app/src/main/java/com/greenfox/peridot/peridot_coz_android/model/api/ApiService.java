package com.greenfox.peridot.peridot_coz_android.model.api;

import com.greenfox.peridot.peridot_coz_android.model.pojo.Building;
import com.greenfox.peridot.peridot_coz_android.model.pojo.Kingdom;
import com.greenfox.peridot.peridot_coz_android.model.pojo.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.*;

public interface ApiService {

    String ENDPOINT = "http://clash-of-zerda.com";

    @GET("/")
    Call<User> getUser();

    @GET("/kingdom/{userId}/")
    Call<Kingdom> getKingdom();

    @GET("/kingdom/{userId}/buildings/")
    Call<ArrayList<Building>> getBuildings(@Path("userId") int userId);

    @GET("/kingdom/{userId}/buildings/[buildingId]")
    Call<Building> getDetailsOfBuilding();

    @POST("/kingdom/{userId}/buildings/")
    Call<Building> createBuilding();

    @PUT("/kingdom/{userId}/buildings/")
    Call<Building> upgradeBuilding();

}
