package com.greenfox.peridot.peridot_coz_android.model.api;

import com.greenfox.peridot.peridot_coz_android.model.pojo.User;

import retrofit2.Call;
import retrofit2.http.*;

public interface UserInterface {

    String ENDPOINT = "http://clash-of-zerda.com";

    @GET("/")
    Call<User> getUser();
}
