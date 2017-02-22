package com.greenfox.peridot.peridot_coz_android.provider;

import android.content.Context;
import com.greenfox.peridot.peridot_coz_android.CozApp;
import com.greenfox.peridot.peridot_coz_android.api.ApiLoginService;
import com.greenfox.peridot.peridot_coz_android.api.ApiService;
import com.greenfox.peridot.peridot_coz_android.api.MockLoginService;
import com.greenfox.peridot.peridot_coz_android.api.MockService;
import com.greenfox.peridot.peridot_coz_android.api.RestApiManager;
import dagger.Module;
import dagger.Provides;

@Module
public class ApiProvider {

    public static final boolean MOCK = true;

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
            return RestApiManager.getUserApi(CozApp.getApplication().getSharedPreferences("userInfo", Context.MODE_PRIVATE).getString("token", ""));
        }
    }
}
