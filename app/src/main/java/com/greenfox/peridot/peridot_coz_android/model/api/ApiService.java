package com.greenfox.peridot.peridot_coz_android.model.api;

import com.greenfox.peridot.peridot_coz_android.model.pojo.User;
import com.greenfox.peridot.peridot_coz_android.model.request.LoginRequest;
import com.greenfox.peridot.peridot_coz_android.model.request.RegisterRequest;
import com.greenfox.peridot.peridot_coz_android.model.response.LoginAndRegisterResponse;

import retrofit2.Call;
import retrofit2.http.*;

public interface ApiService {

    String ENDPOINT = "http://clash-of-zerda.com";

    @POST("/login")
    Call<LoginAndRegisterResponse> login(LoginRequest loginRequest);

    @POST("/register")
    Call<LoginAndRegisterResponse> register(RegisterRequest registerRequest);
}
