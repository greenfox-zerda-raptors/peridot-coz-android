package com.greenfox.peridot.peridot_coz_android.provider;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationProvider {


    Application mApplication;

    public ApplicationProvider(Application application) {
        this.mApplication = application;
    }

    @Provides
    @Singleton
    public Application getApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    public SharedPreferences provideSharedPref() {
        return mApplication.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
    }
}