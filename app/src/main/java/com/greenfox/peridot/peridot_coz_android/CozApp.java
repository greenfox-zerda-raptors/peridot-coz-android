package com.greenfox.peridot.peridot_coz_android;

import android.app.Application;

import com.greenfox.peridot.peridot_coz_android.provider.ApplicationProvider;
import com.greenfox.peridot.peridot_coz_android.provider.DaggerApplicationComponent;

public class CozApp extends Application {

    private static Application mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
    }

    public static Application getApplication() {
        return mApplication;
    }
}
