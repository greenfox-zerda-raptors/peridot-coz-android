package com.greenfox.peridot.peridot_coz_android.backgroundSync;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.greenfox.peridot.peridot_coz_android.api.ApiService;
import com.greenfox.peridot.peridot_coz_android.dagger.DaggerMainActivityComponent;
import com.greenfox.peridot.peridot_coz_android.fragment.BuildingsOverviewFragment;
import com.greenfox.peridot.peridot_coz_android.model.response.BuildingsResponse;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SyncService extends IntentService{

    @Inject
    ApiService apiService;
    BuildingsResponse buildings;

    public static final String SYNC_DONE = "com.greenfox.peridot.peridot_coz_android.backgroundSync.SyncService.SYNC_DONE";

    public SyncService(){
        super(SyncService.class.getName());
    }

    public SyncService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        DaggerMainActivityComponent.builder().build().inject(this);
        Log.e("SyncService", "Sync start");
        Intent syncIntent = new Intent();
        Bundle bundle = new Bundle();
        syncIntent.setAction(SYNC_DONE);
        backgroundSync();
        bundle.putSerializable("buildings", buildings);
        Log.e("SyncService", buildings.getBuildings().get(1).getType());
        syncIntent.putExtra("bundle", bundle.getSerializable("buildings"));
        syncIntent.setClass(this, BuildingsOverviewFragment.class);
        Log.e("SyncService", "Sync stop");
        LocalBroadcastManager.getInstance(this).sendBroadcast(syncIntent);
        Log.e("SyncService", "Broadcast sent");
    }

    private void backgroundSync() {
        apiService.syncBuildings(1).enqueue(new Callback<BuildingsResponse>() {
            @Override
            public void onResponse(Call<BuildingsResponse> call, Response<BuildingsResponse> response) {
                buildings = response.body();
            }
            @Override
            public void onFailure(Call<BuildingsResponse> call, Throwable t) {
            }
        });
    }
}
