package com.greenfox.peridot.peridot_coz_android.provider;

import dagger.Module;
import dagger.Provides;

/**
 * Created by bedij on 2017. 02. 14..
 */
@Module
public class ServiceProvider {

    @Provides
    public Services services(){
        return new Services();
    }

}
