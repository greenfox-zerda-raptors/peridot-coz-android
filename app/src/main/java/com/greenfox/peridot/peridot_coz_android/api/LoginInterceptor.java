package com.greenfox.peridot.peridot_coz_android.api;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

@Singleton
public class LoginInterceptor implements Interceptor {

    private String authToken;

    @Inject
    public LoginInterceptor(String token) {
        this.authToken = token;
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request original = chain.request();

        Request.Builder builder = original.newBuilder()
        .header("Content-Type", "application/json");

        Request authorisedRequest = builder.build();
        return chain.proceed(authorisedRequest);
    }

    public String getAuthToken() {return authToken;}

    public void setAuthToken(String authToken) {this.authToken = authToken;}
}