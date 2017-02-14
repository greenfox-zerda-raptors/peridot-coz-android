package com.greenfox.peridot.peridot_coz_android.backgroundSync;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import com.greenfox.peridot.peridot_coz_android.R;
import com.greenfox.peridot.peridot_coz_android.activity.MainActivity;
import com.greenfox.peridot.peridot_coz_android.api.ApiService;
import com.greenfox.peridot.peridot_coz_android.dagger.DaggerApiComponent;
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
    NotificationManager notificationManager;

    public static final String SYNC_DONE = "com.greenfox.peridot.peridot_coz_android.backgroundSync.SyncService.SYNC_DONE";

    public SyncService(){
        super(SyncService.class.getName());
    }

    public SyncService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        DaggerApiComponent.builder().build().inject(this);
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
                SharedPreferences preferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                if (response.body().getBuildings().size() > preferences.getInt("buildings",0)) {
                    showNotification();
                }
                buildings = response.body();
            }
            @Override
            public void onFailure(Call<BuildingsResponse> call, Throwable t) {
            }
        });
    }

    private void showNotification() {
        NotificationCompat.Builder notificationBuilder = new
                NotificationCompat.Builder(this)
                .setContentTitle("New buildings")
                .setContentText("New Buildings available")
                .setSmallIcon(R.drawable.ic_castle_black);

        Intent notificationIntent = new Intent(this,  MainActivity.class);
        notificationIntent.putExtra("fragment", "buildings");
        notificationIntent.putExtra("notification", "true");
        notificationIntent.putExtra("notificationID", "123");
        TaskStackBuilder tStackBuilder = TaskStackBuilder.create(this);
        tStackBuilder.addParentStack(MainActivity.class);
        tStackBuilder.addNextIntent(notificationIntent);
        PendingIntent pendingIntent = tStackBuilder.getPendingIntent(0,
                PendingIntent.FLAG_UPDATE_CURRENT);
        notificationBuilder.setContentIntent(pendingIntent);
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(123, notificationBuilder.build());

    }
}
