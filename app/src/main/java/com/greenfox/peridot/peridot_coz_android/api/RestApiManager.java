package com.greenfox.peridot.peridot_coz_android.api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApiManager {

    private static ApiService mApiService;

    public static ApiService getUserApi() {
        if(mApiService == null) {
            final OkHttpClient client = new OkHttpClient();
            final Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(ApiService.ENDPOINT)
                    .client(client)
                    .build();
            mApiService = retrofit.create(ApiService.class);
        }
        return mApiService;
    }
}
