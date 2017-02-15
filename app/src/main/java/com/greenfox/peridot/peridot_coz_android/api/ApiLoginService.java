package com.greenfox.peridot.peridot_coz_android.api;

import com.greenfox.peridot.peridot_coz_android.model.request.LoginRequest;
import com.greenfox.peridot.peridot_coz_android.model.request.RegisterRequest;
import com.greenfox.peridot.peridot_coz_android.model.response.LoginAndRegisterResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiLoginService {

    String ENDPOINT = "https://pacific-bastion-75389.herokuapp.com";


    @POST("/login")
    Call<LoginAndRegisterResponse> login(@Body LoginRequest loginRequest);

    @POST("/register")
    Call<LoginAndRegisterResponse> register(@Body RegisterRequest registerRequest);
}
