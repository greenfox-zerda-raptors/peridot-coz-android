package com.greenfox.peridot.peridot_coz_android;

import android.app.Application;
import android.content.Context;

public class CozApp extends Application {
    private static boolean applicationVisible = true;
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
/*
        DaggerMainActivityComponent.builder().appModule(new ApplicationProvider(this)).build();
*/
    }

    public static void setApplicationVisible(boolean applicationVisible) {CozApp.applicationVisible = applicationVisible;}
    public static boolean isApplicationVisible() {return applicationVisible;}
    public static Context getContext() {return context;}
}
