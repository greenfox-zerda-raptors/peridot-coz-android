package com.greenfox.peridot.peridot_coz_android.dagger;

import com.greenfox.peridot.peridot_coz_android.activity.MainActivity;
import com.greenfox.peridot.peridot_coz_android.model.api.MockService;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by BB on 2017-01-24.
 */

@Singleton
@Component(modules = {MockService.class})
public interface MainActivityComponent {
    void inject(MainActivity mainActivity);
}
