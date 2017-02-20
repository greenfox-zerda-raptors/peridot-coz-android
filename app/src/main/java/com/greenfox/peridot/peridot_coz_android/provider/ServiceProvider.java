package com.greenfox.peridot.peridot_coz_android.provider;

import com.greenfox.peridot.peridot_coz_android.api.ApiLoginService;
import com.greenfox.peridot.peridot_coz_android.api.ApiService;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

@Module
public class ServiceProvider {

    @Provides
    public Services services(){
        return new Services();
    }
}
