package com.greenfox.peridot.peridot_coz_android.provider;

import android.content.SharedPreferences;
import android.util.Log;

import com.greenfox.peridot.peridot_coz_android.api.ApiLoginService;
import com.greenfox.peridot.peridot_coz_android.api.ApiService;
import com.greenfox.peridot.peridot_coz_android.api.MockLoginService;
import com.greenfox.peridot.peridot_coz_android.api.MockService;
import com.greenfox.peridot.peridot_coz_android.api.RestApiManager;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

@Module
public class ApiProvider {

    public static final boolean MOCK = true;
    @Inject
    SharedPreferences sharedPreferences;

    @Provides
    public ApiLoginService provideLoginService() {
        if (MOCK) {
            return new MockLoginService();
        } else {
            return RestApiManager.getLoginApi();
        }
    }

    @Provides
    public ApiService provideApiService() {
        if (MOCK) {
            return new MockService();
        } else {
            DaggerApplicationComponent.builder().build().inject(this);
            String authToken = sharedPreferences.getString("token", "");
            return RestApiManager.getUserApi(authToken);
        }
    }
}