package com.greenfox.peridot.peridot_coz_android.model.api;

import com.greenfox.peridot.peridot_coz_android.model.pojo.Kingdom;
import com.greenfox.peridot.peridot_coz_android.model.pojo.Resource;
import com.greenfox.peridot.peridot_coz_android.model.pojo.User;
import com.greenfox.peridot.peridot_coz_android.model.request.LoginRequest;
import com.greenfox.peridot.peridot_coz_android.model.request.RegisterRequest;
import com.greenfox.peridot.peridot_coz_android.model.response.LoginAndRegisterResponse;
import com.greenfox.peridot.peridot_coz_android.model.response.ResourceResponse;

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

    @GET("/kingdom/{userId}/resources/")
    Call<ResourceResponse> getResource();

    @GET("/kingdom/{userId}/resources/{type}")
    Call<ResourceResponse> getType();
}
