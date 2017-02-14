package com.greenfox.peridot.peridot_coz_android.api;

import com.greenfox.peridot.peridot_coz_android.model.request.LoginRequest;
import com.greenfox.peridot.peridot_coz_android.model.request.RegisterRequest;
import com.greenfox.peridot.peridot_coz_android.model.response.LoginAndRegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;

/**
 * Created by bedij on 2017. 02. 14..
 */
public class MockLoginService implements ApiLoginService {
    @Override
    public Call<LoginAndRegisterResponse> login(@Body LoginRequest loginRequest) {
        return null;
    }

    @Override
    public Call<LoginAndRegisterResponse> register(RegisterRequest registerRequest) {
        return null;
    }
}
