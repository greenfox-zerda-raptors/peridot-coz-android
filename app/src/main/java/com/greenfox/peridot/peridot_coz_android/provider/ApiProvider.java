package com.greenfox.peridot.peridot_coz_android.provider;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.greenfox.peridot.peridot_coz_android.CozApp;
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
    Application mApplication;

    public ApiService provideMockService(){return new MockService();}

    public ApiLoginService provideMockLoginService(){
        return new MockLoginService();
    }

    @Provides
    public ApiLoginService provideLoginApiManager() {return RestApiManager.getLoginApi();}

    @Provides
    public ApiService provideRestApiManager() {
        DaggerApplicationComponent.builder().build().inject(this);
        SharedPreferences sharedPref = CozApp.getAppContext().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String authToken = sharedPref.getString("token", "");
        return RestApiManager.getUserApi(authToken);}

}
