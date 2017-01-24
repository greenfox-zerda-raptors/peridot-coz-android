package com.greenfox.peridot.peridot_coz_android.model.api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RestApiManager {

    private static UserInterface mUserInterface;

    public static UserInterface getUserApi() {
        if(mUserInterface == null) {
            final OkHttpClient client = new OkHttpClient();
            final Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(UserInterface.ENDPOINT)
                    .client(client)
                    .build();
            mUserInterface = retrofit.create(UserInterface.class);
        }
        return mUserInterface;
    }
}
