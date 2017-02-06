package com.greenfox.peridot.peridot_coz_android.model.api;

import com.greenfox.peridot.peridot_coz_android.model.pojo.Building;
import com.greenfox.peridot.peridot_coz_android.model.pojo.Kingdom;
import com.greenfox.peridot.peridot_coz_android.model.pojo.User;
import com.greenfox.peridot.peridot_coz_android.model.request.LoginRequest;
import com.greenfox.peridot.peridot_coz_android.model.request.RegisterRequest;
import com.greenfox.peridot.peridot_coz_android.model.response.BuildingsResponse;
import com.greenfox.peridot.peridot_coz_android.model.response.LoginAndRegisterResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.*;

import static android.R.attr.id;

public interface ApiService {

    String ENDPOINT = "http://clash-of-zerda.com";

    @POST("/login")
    Call<LoginAndRegisterResponse> login(LoginRequest loginRequest);

    @POST("/register")
    Call<LoginAndRegisterResponse> register(RegisterRequest registerRequest);

    @GET("/kingdom/{userId}/")
    Call<Kingdom> getKingdom(@Path("userId") int userId);

    @GET("/kingdom/{userId}/buildings/")
    Call<BuildingsResponse> getBuildings(@Path("userId") int userId);

    @GET("/kingdom/{userId}/buildings/{buildingId}/")
    Call<Building> getDetailsOfBuilding(@Path("userId")int userId, @Path("buildingId")int buildingId);

    @POST("/kingdom/{userId}/buildings/")
    Call<Building> createBuilding(@Path("userId") int userId,@Body Building building);

    @PUT("/kingdom/{userId}/buildings/{buildingId}/")
    Call<Building> upgradeBuilding(@Path("userId") int userId, @Path("buildingId")int buildingId, @Body Building building);

    @GET ("/kingdom/{userId}/troops/")
    Call<TroopsResponse> getTroops(@Path("userId") int userId);

    @GET ("/kingdom/{userId}/troops/{troopId}/")
    Call<Troop> getTroopDetail(@Path("userId") int userId, @Path("troopId") int troopId);

}
