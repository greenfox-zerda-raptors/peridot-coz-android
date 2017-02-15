package com.greenfox.peridot.peridot_coz_android.provider;

import android.app.Application;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationProvider {
/*    Application mApplication;

    public ApplicationProvider(Application mApplication) {
        this.mApplication = mApplication;
    }*/

    @Provides
    public Application getApplication() {return getApplication();}

/*    @Provides
    public SharedPreferences provideSharedPref() {
        return mApplication.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
    }*/
}