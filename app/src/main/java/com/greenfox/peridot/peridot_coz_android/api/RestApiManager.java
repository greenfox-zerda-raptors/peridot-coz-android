package com.greenfox.peridot.peridot_coz_android.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApiManager {

    private static OkHttpClient client;
    private static HttpLoggingInterceptor loggerInterceptor = new HttpLoggingInterceptor();

    public static OkHttpClient.Builder setHttpClient(){
        DefaultInterceptor defaultInterceptor = new DefaultInterceptor();
        return new OkHttpClient.Builder()
                .addInterceptor(defaultInterceptor);
    }

    public static Retrofit setRetrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(ApiLoginService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    public static ApiLoginService getLoginApi() {
        loggerInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client = setHttpClient()
                .addInterceptor(loggerInterceptor)
                .build();
        return setRetrofit(client).create(ApiLoginService.class);
    }

    public static ApiService getUserApi(final String authToken) {
        AuthorizationInterceptor authInterceptor = new AuthorizationInterceptor(authToken);
        client = setHttpClient().addInterceptor(authInterceptor).build();
        return setRetrofit(client).create(ApiService.class);
    }
}
