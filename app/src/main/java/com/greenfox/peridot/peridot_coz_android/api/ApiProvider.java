package com.greenfox.peridot.peridot_coz_android.api;

import dagger.Module;
import dagger.Provides;

@Module
public class ApiProvider {

    @Provides
    public ApiService provideMockService(){return new MockService();}

    public ApiService provideRestApiManager() {return RestApiManager.getUserApi();}
}
