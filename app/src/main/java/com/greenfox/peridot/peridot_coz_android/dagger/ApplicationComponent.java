package com.greenfox.peridot.peridot_coz_android.dagger;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationProvider.class})
public interface ApplicationComponent {
    void inject(ApiProvider apiProvider);
}
