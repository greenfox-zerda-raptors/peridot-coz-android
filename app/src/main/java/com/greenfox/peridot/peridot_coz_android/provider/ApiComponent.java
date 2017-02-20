package com.greenfox.peridot.peridot_coz_android.provider;


import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApiProvider.class)
public interface ApiComponent {
    void inject(Services services);

}
