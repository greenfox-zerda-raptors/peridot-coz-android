package com.greenfox.peridot.peridot_coz_android.dagger;

import com.greenfox.peridot.peridot_coz_android.activity.LoginActivity;
import com.greenfox.peridot.peridot_coz_android.activity.MainActivity;
import com.greenfox.peridot.peridot_coz_android.activity.RegisterActivity;
import com.greenfox.peridot.peridot_coz_android.fragment.TroopsOverviewFragment;
import com.greenfox.peridot.peridot_coz_android.fragment.BuildingsOverviewFragment;

import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = {ApiProvider.class, ApplicationProvider.class})
public interface ApiComponent {
    void inject(MainActivity mainActivity);
    void inject(LoginActivity loginActivity);
    void inject(RegisterActivity registerActivity);
    void inject(TroopsOverviewFragment troopsOverviewFragment);
    void inject(BuildingsOverviewFragment buildingsOverviewFragment);
}
