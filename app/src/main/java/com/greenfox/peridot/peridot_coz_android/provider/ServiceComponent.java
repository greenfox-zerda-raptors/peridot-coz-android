package com.greenfox.peridot.peridot_coz_android.provider;

import com.greenfox.peridot.peridot_coz_android.activity.LoginActivity;
import com.greenfox.peridot.peridot_coz_android.activity.MainActivity;
import com.greenfox.peridot.peridot_coz_android.activity.RegisterActivity;
import com.greenfox.peridot.peridot_coz_android.adapter.BuildingAdapter;
import com.greenfox.peridot.peridot_coz_android.backgroundSync.SyncReceiver;
import com.greenfox.peridot.peridot_coz_android.fragment.BuildingDetailFragment;
import com.greenfox.peridot.peridot_coz_android.fragment.BuildingsOverviewFragment;
import com.greenfox.peridot.peridot_coz_android.fragment.KingdomOverviewFragment;
import com.greenfox.peridot.peridot_coz_android.fragment.ResourcesOverviewFragment;
import com.greenfox.peridot.peridot_coz_android.fragment.TroopDetailFragment;
import com.greenfox.peridot.peridot_coz_android.fragment.TroopsOverviewFragment;
import com.greenfox.peridot.peridot_coz_android.fragment.UserOverviewFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ServiceProvider.class)
public interface ServiceComponent {
    void inject(MainActivity mainActivity);
    void inject(LoginActivity loginActivity);
    void inject(RegisterActivity registerActivity);
    void inject(KingdomOverviewFragment kingdomOverviewFragment);
    void inject(TroopsOverviewFragment troopsOverviewFragment);
    void inject(BuildingsOverviewFragment buildingsOverviewFragment);
    void inject(SyncReceiver syncReceiver);
    void inject(BuildingDetailFragment buildingDetailFragment);
    void inject(BuildingAdapter buildingAdapter);
    void inject(TroopDetailFragment buildingDetailFragment);
    void inject(ResourcesOverviewFragment resourcesOverviewFragment);
    void inject(UserOverviewFragment userOverviewFragment);
}
