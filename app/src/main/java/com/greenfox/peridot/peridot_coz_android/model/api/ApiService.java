package com.greenfox.peridot.peridot_coz_android.model.api;

import com.greenfox.peridot.peridot_coz_android.model.pojo.Kingdom;
import com.greenfox.peridot.peridot_coz_android.model.pojo.User;

import retrofit2.Call;
import retrofit2.http.*;

import static android.R.attr.id;

public interface ApiService {

    String ENDPOINT = "http://clash-of-zerda.com";

    @GET("/")
    Call<User> getUser();

    @GET("/kingdom/{userId}/")
    Call<Kingdom> getKingdom(@Path("userId") int userId);
}
