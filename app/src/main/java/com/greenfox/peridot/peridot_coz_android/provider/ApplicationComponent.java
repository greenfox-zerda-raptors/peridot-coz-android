package com.greenfox.peridot.peridot_coz_android.provider;

import android.content.SharedPreferences;

import com.greenfox.peridot.peridot_coz_android.CozApp;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationProvider.class)
public interface ApplicationComponent {
    SharedPreferences getSharedprefernces();
    void inject(ApiProvider apiProvider);

}
