package com.greenfox.peridot.peridot_coz_android;

import android.app.Application;
import android.content.Context;
import android.util.Log;

public class CozApp extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
/*
        DaggerMainActivityComponent.builder().appModule(new ApplicationProvider(this)).build();
*/
    }

    public static Context getAppContext() {
        return context;
    }

}