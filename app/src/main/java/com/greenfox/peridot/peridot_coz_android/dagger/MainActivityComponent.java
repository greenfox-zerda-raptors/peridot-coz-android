package com.greenfox.peridot.peridot_coz_android.dagger;

import com.greenfox.peridot.peridot_coz_android.activity.LoginActivity;
import com.greenfox.peridot.peridot_coz_android.activity.MainActivity;
import com.greenfox.peridot.peridot_coz_android.fragment.BuildingsOverviewFragment;
import com.greenfox.peridot.peridot_coz_android.api.ApiProvider;
import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = {ApiProvider.class})
public interface MainActivityComponent {
    void inject(MainActivity mainActivity);
    void inject(LoginActivity loginActivity);
    void inject(BuildingsOverviewFragment buildingsOverviewFragment);
}
