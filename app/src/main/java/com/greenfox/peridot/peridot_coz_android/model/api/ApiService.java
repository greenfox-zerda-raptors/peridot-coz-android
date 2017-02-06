package com.greenfox.peridot.peridot_coz_android.model.api;

import com.greenfox.peridot.peridot_coz_android.model.pojo.Troop;
import com.greenfox.peridot.peridot_coz_android.model.request.LoginRequest;
import com.greenfox.peridot.peridot_coz_android.model.request.RegisterRequest;
import com.greenfox.peridot.peridot_coz_android.model.response.KingdomResponse;
import com.greenfox.peridot.peridot_coz_android.model.response.LoginAndRegisterResponse;
import com.greenfox.peridot.peridot_coz_android.model.response.TroopsResponse;

import java.util.ArrayList;

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
    Call<KingdomResponse> getKingdom(@Path("userId") int userId);

    @GET ("/kingdom/{userId}/troops/")
    Call<TroopsResponse> getTroops(@Path("userId") int userId);

    @GET ("/kingdom/{userId}/troops/{troopId}/")
    Call<Troop> getTroopDetail(@Path("userId") int userId, @Path("troopId") int troopId);

}
