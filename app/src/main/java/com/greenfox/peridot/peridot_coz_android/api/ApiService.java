package com.greenfox.peridot.peridot_coz_android.api;

import com.greenfox.peridot.peridot_coz_android.model.pojo.Building;
import com.greenfox.peridot.peridot_coz_android.model.pojo.Troop;
import com.greenfox.peridot.peridot_coz_android.model.request.LoginRequest;
import com.greenfox.peridot.peridot_coz_android.model.request.RegisterRequest;
import com.greenfox.peridot.peridot_coz_android.model.response.BuildingsResponse;
import com.greenfox.peridot.peridot_coz_android.model.response.KingdomResponse;
import com.greenfox.peridot.peridot_coz_android.model.response.LoginAndRegisterResponse;
import com.greenfox.peridot.peridot_coz_android.model.response.ResourceResponse;
import com.greenfox.peridot.peridot_coz_android.model.response.TroopsResponse;
import retrofit2.Call;
import retrofit2.http.*;

public interface ApiService {

    String ENDPOINT = "http://clash-of-zerda.com";

    @POST("/login")
    Call<LoginAndRegisterResponse> login(LoginRequest loginRequest);

    @POST("/register")
    Call<LoginAndRegisterResponse> register(RegisterRequest registerRequest);

    @GET("/kingdom/{userId}/")
    Call<KingdomResponse> getKingdom(@Path("userId") int userId);

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
  
    @GET("/kingdom/{userId}/resources/")
    Call<ResourceResponse> getResource();

    @GET("/kingdom/{userId}/resources/{type}")
    Call<ResourceResponse> getType();

}
