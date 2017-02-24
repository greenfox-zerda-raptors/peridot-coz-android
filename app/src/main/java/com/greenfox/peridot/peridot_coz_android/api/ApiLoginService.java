package com.greenfox.peridot.peridot_coz_android.api;

import com.greenfox.peridot.peridot_coz_android.model.request.LoginRequest;
import com.greenfox.peridot.peridot_coz_android.model.request.RegisterRequest;
import com.greenfox.peridot.peridot_coz_android.model.response.LoginResponse;
import com.greenfox.peridot.peridot_coz_android.model.response.Response;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiLoginService {

    String ENDPOINT = "https://pacific-bastion-75389.herokuapp.com";

    @POST("/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @POST("/register")
    Call<Response> register(@Body RegisterRequest registerRequest);
}
