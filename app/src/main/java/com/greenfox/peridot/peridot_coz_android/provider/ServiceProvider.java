package com.greenfox.peridot.peridot_coz_android.provider;

import dagger.Module;
import dagger.Provides;

@Module
public class ServiceProvider {

    @Provides
    public Services services(){
        return new Services();
    }
}
