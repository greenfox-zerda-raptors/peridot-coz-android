package com.greenfox.peridot.peridot_coz_android.model.api;

import dagger.Module;
import dagger.Provides;

/**
 * Created by BB on 2017-01-30.
 */

@Module
public class ApiProvider {

    @Provides
    public ApiService provideMockService(){return new MockService();}

    public ApiService provideRestApiManager() {return RestApiManager.getUserApi();}
}
