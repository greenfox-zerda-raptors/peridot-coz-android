package com.greenfox.peridot.peridot_coz_android.api;

import com.greenfox.peridot.peridot_coz_android.model.request.LoginRequest;
import com.greenfox.peridot.peridot_coz_android.model.request.RegisterRequest;
import com.greenfox.peridot.peridot_coz_android.model.response.LoginAndRegisterResponse;

import dagger.Module;
import retrofit2.Call;
import retrofit2.http.Body;

@Module
public class MockLoginService implements ApiLoginService {
    @Override
    public Call<LoginAndRegisterResponse> login(@Body LoginRequest loginRequest) {
        return null;
    }

    @Override
    public Call<LoginAndRegisterResponse> register(@Body RegisterRequest registerRequest) {return null;}
}
