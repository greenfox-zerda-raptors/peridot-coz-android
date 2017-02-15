package com.greenfox.peridot.peridot_coz_android.backgroundSync;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;
import com.greenfox.peridot.peridot_coz_android.CozApp;
import com.greenfox.peridot.peridot_coz_android.R;
import com.greenfox.peridot.peridot_coz_android.activity.MainActivity;
import com.greenfox.peridot.peridot_coz_android.api.ApiService;
import com.greenfox.peridot.peridot_coz_android.model.pojo.Kingdom;
import com.greenfox.peridot.peridot_coz_android.model.pojo.Troop;
import com.greenfox.peridot.peridot_coz_android.model.response.KingdomResponse;
import com.greenfox.peridot.peridot_coz_android.provider.DaggerApiComponent;
import java.util.ArrayList;
import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SyncReceiver extends BroadcastReceiver {

    public static final String NEW_BUILDINGS = "New Buildings Available";
    public static final String KILL_BUILDINGS = "Buildings Destroyed In Battle";
    public static final String NEW_TROOPS = "New Troops Available";
    public static final String KILL_TROOPS = "Troops Killed In Battle";

    @Inject
    ApiService apiService;
    Kingdom syncedKingdom;
    SharedPreferences preferences;
    NotificationCompat.Builder notificationBuilder;
    Intent notificationIntent;

    @Override
    public void onReceive(Context context, Intent intent) {
        DaggerApiComponent.builder().build().inject(this);
        preferences = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        syncKingdom();
        if (!CozApp.isApplicationVisible()) {
            Log.e("syncreceiver", "i am doing background stuff");
            checkForChangesAndNotify(context);
        } else {
            Log.e("syncreceiver", "i am doing stuff");
            // TODO update current fragment (bus)
            // TODO update navbar with numbers (bus)
        }
    }

    private void checkForChangesAndNotify(Context context) {
        if (syncedKingdom.getBuildings().size() != preferences.getInt("buildings",0)) {
            prepareNotification(context);
            notificationIntent.putExtra("fragment", "buildings");
            if (syncedKingdom.getBuildings().size() > preferences.getInt("buildings",0)) {
                notificationBuilder.setContentTitle(NEW_BUILDINGS)
                        .setContentText(NEW_BUILDINGS);
            } else if (syncedKingdom.getBuildings().size() < preferences.getInt("buildings",0)) {
                notificationBuilder.setContentTitle(KILL_BUILDINGS)
                        .setContentText(KILL_BUILDINGS);
            }
        }
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(123, notificationBuilder.build());

        if (syncedKingdom.getTroops().size() != preferences.getInt("troops",0)) {
            prepareNotification(context);
            notificationIntent.putExtra("fragment", "troops");
            if (syncedKingdom.getTroops().size() > preferences.getInt("troops",0)) {
                notificationBuilder.setContentTitle(NEW_TROOPS)
                        .setContentText(NEW_TROOPS);
            } else if (syncedKingdom.getTroops().size() < preferences.getInt("troops",0)) {
                notificationBuilder.setContentTitle(KILL_TROOPS)
                        .setContentText(KILL_TROOPS);
            }
        }
        notificationManager.notify(123, notificationBuilder.build());
    }

    private void prepareNotification (Context context){
        notificationBuilder = new
                NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_castle_black);
        Intent notificationIntent = new Intent(context, MainActivity.class);
        notificationIntent.putExtra("notification", "true");
        notificationIntent.putExtra("notificationID", "123");
        TaskStackBuilder tStackBuilder = TaskStackBuilder.create(context);
        tStackBuilder.addParentStack(MainActivity.class);
        tStackBuilder.addNextIntent(notificationIntent);
        PendingIntent pendingIntent = tStackBuilder.getPendingIntent(0,
                PendingIntent.FLAG_UPDATE_CURRENT);
        notificationBuilder.setContentIntent(pendingIntent);
    }

    public void syncKingdom() {
        apiService.getKingdom().enqueue(new Callback<KingdomResponse>() {
            @Override
            public void onResponse(Call<KingdomResponse> call, Response<KingdomResponse> response) {
                syncedKingdom = response.body().getKingdom();
            }
            @Override
            public void onFailure(Call<KingdomResponse> call, Throwable t) {}
        });
    }
}
