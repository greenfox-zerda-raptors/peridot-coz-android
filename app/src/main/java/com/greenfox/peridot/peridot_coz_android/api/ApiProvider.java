package com.greenfox.peridot.peridot_coz_android.api;

import dagger.Module;
import dagger.Provides;

@Module
public class ApiProvider {

    /*public ApiService provideMockService(){return new MockService();}*/
    @Provides
    public ApiService provideRestApiManager() {return RestApiManager.getUserApi();}
}
