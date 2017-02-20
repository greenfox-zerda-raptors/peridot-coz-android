package com.greenfox.peridot.peridot_coz_android;

import android.app.Application;

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
