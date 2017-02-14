package com.greenfox.peridot.peridot_coz_android.api;

import android.text.TextUtils;

import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApiManager {

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    private static OkHttpClient client;
    private static ApiLoginService lApiService;
    private static ApiService mApiService;
    private static Retrofit retrofit;

    public static ApiLoginService getLoginApi() {
        client = httpClient.build();
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(ApiLoginService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create());
        retrofit = builder
                .client(client)
                .build();
        if (lApiService == null) {
            lApiService = retrofit.create(ApiLoginService.class);
        }
        return lApiService;
    }

    public static ApiService getUserApi(final String authToken) {
        if (!TextUtils.isEmpty(authToken)) {
            AuthenticationInterceptor authInterceptor = new AuthenticationInterceptor(authToken);
            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl(ApiService.ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create());
            if (!client.interceptors().contains(authInterceptor)) {
                 httpClient.addInterceptor(authInterceptor);
                 client = httpClient.build();
                 builder.client(client);
                 retrofit = builder.build();
            }
        }
        return retrofit.create(mApiService.getClass());
    }
}
