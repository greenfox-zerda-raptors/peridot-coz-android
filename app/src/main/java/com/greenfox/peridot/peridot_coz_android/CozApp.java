package com.greenfox.peridot.peridot_coz_android;

import android.app.Application;

import com.greenfox.peridot.peridot_coz_android.provider.ApplicationProvider;
import com.greenfox.peridot.peridot_coz_android.provider.DaggerApplicationComponent;

public class CozApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerApplicationComponent.builder().applicationProvider(new ApplicationProvider(this)).build();
    }
}
