package com.greenfox.peridot.peridot_coz_android.dagger;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Papp Beata Andrea on 13.02.2017.
 */
@Singleton
@Component(modules = {ApplicationProvider.class})
public interface ApplicationComponent {
    void inject(ApiProvider apiProvider);
}
