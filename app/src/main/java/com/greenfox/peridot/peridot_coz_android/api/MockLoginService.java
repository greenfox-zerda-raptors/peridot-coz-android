package com.greenfox.peridot.peridot_coz_android.api;

import com.greenfox.peridot.peridot_coz_android.model.request.LoginRequest;
import com.greenfox.peridot.peridot_coz_android.model.request.RegisterRequest;
import com.greenfox.peridot.peridot_coz_android.model.response.Error;
import com.greenfox.peridot.peridot_coz_android.model.response.LoginResponse;
import dagger.Module;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;

@Module
public class MockLoginService implements ApiLoginService {
    @Override
    public Call<LoginResponse> login(final LoginRequest loginRequest) {
        return new MockCall<LoginResponse>() {
            @Override
            public void enqueue(Callback<LoginResponse> callback) {
                if (loginRequest.getUsername().equals("aaa")
                        && loginRequest.getPassword().equals("aaa")) {
                    Response<LoginResponse> r = Response.success(new LoginResponse());
                    r.body().setToken("vfdbfdbsbsgbgbfbabaffbsbsfd");
                    callback.onResponse(this, r);
                } else if (!loginRequest.getUsername().equals("aaa")){
                    Response<LoginResponse> r = Response.success(new LoginResponse());
                    Error error = new Error();
                    error.setUsername("Wrong username");
                    r.body().setErrors(error);
                    callback.onResponse(this, r);
                } else if (!loginRequest.getPassword().equals("aaa")) {
                    Response<LoginResponse> r = Response.success(new LoginResponse());
                    Error error = new Error();
                    error.setPassword("Wrong password");
                    r.body().setErrors(error);
                    callback.onResponse(this, r);
                }
            }
        };
    }

    @Override

    public Call<com.greenfox.peridot.peridot_coz_android.model.response.Response> register(final @Body RegisterRequest registerRequest) {
        return new MockCall<com.greenfox.peridot.peridot_coz_android.model.response.Response>() {
            @Override
            public void enqueue(Callback<com.greenfox.peridot.peridot_coz_android.model.response.Response> callback) {
                Response<com.greenfox.peridot.peridot_coz_android.model.response.Response> r = Response.success(new com.greenfox.peridot.peridot_coz_android.model.response.Response());
                callback.onResponse(this, r);
            }
        };
    }
}
