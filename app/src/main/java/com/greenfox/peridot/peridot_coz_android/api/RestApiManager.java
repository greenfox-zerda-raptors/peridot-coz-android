package com.greenfox.peridot.peridot_coz_android.api;

import android.text.TextUtils;

import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApiManager {

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    private static OkHttpClient client;
    private static ApiService mApiService;
    private static Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl(ApiService.ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create());
    private static Retrofit retrofit;

    public static ApiService getUserApi() {
        client = httpClient.build();
        retrofit = builder
                .client(client)
                .build();
        if (mApiService == null) {
            mApiService = retrofit.create(ApiService.class);
        }
        return mApiService;
    }
/*
    public ApiService getUserApi(ApiService apiService, String username, String password) {
        if (!TextUtils.isEmpty(username)
                && !TextUtils.isEmpty(password)) {
            String authToken = Credentials.basic(username, password);
            return getUserApi(apiService, authToken);
        }
        return getUserApi(apiService, null, null);
    }
*/
    public  ApiService getUserApi(final String authToken) {
        if (!TextUtils.isEmpty(authToken)) {
            AuthenticationInterceptor authInterceptor = new AuthenticationInterceptor(authToken);
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
