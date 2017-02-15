package com.greenfox.peridot.peridot_coz_android.api;

import android.media.session.MediaSession;

import com.greenfox.peridot.peridot_coz_android.model.pojo.User;
import com.greenfox.peridot.peridot_coz_android.model.request.LoginRequest;
import com.greenfox.peridot.peridot_coz_android.model.request.RegisterRequest;
import com.greenfox.peridot.peridot_coz_android.model.response.Error;
import com.greenfox.peridot.peridot_coz_android.model.response.LoginAndRegisterResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;

/**
 * Created by bedij on 2017. 02. 14..
 */
public class MockLoginService implements ApiLoginService {
    @Override
    public Call<LoginAndRegisterResponse> login(final LoginRequest loginRequest) {
        return new MockCall<LoginAndRegisterResponse>() {
            @Override
            public void enqueue(Callback<LoginAndRegisterResponse> callback) {
                if (loginRequest.getUsername().equals("aaa")
                        && loginRequest.getPassword().equals("aaa")) {
                    Response<LoginAndRegisterResponse> r = Response.success(new LoginAndRegisterResponse());
                    r.body().setToken("vfdbfdbsbsgbgbfbabaffbsbsfd");
                    callback.onResponse(this, r);
                } else if (!loginRequest.getUsername().equals("aaa")){
                    Response<LoginAndRegisterResponse> r = Response.success(new LoginAndRegisterResponse());
                    Error error = new Error();
                    error.setUsername("Wrong username");
                    r.body().setErrors(error);
                    callback.onResponse(this, r);
                } else if (!loginRequest.getPassword().equals("aaa")) {
                    Response<LoginAndRegisterResponse> r = Response.success(new LoginAndRegisterResponse());
                    Error error = new Error();
                    error.setPassword("Wrong password");
                    r.body().setErrors(error);
                    callback.onResponse(this, r);
                }
            }
        };
    }

    @Override
    public Call<LoginAndRegisterResponse> register(final @Body RegisterRequest registerRequest) {
        return new MockCall<LoginAndRegisterResponse>() {
            @Override
            public void enqueue(Callback<LoginAndRegisterResponse> callback) {
                Response<LoginAndRegisterResponse> r = Response.success(new LoginAndRegisterResponse());
                r.body().setToken("gkuahfoirkbggfjfkgkufg7558586");
                callback.onResponse(this, r);
            }
        };
    }
}
