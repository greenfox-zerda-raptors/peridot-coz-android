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
import com.greenfox.peridot.peridot_coz_android.model.response.KingdomResponse;
import com.greenfox.peridot.peridot_coz_android.provider.Services;

import org.greenrobot.eventbus.EventBus;

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
    Services services;
    Context context;
    Kingdom syncedKingdom;
    SharedPreferences preferences;
    NotificationManager notificationManager;
    int differenceBuildings;
    int differenceTroops;


    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
//        DaggerServiceComponent.builder().build().inject(this);
        preferences = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        syncKingdom();
//        EventBus.getDefault().post(new NavBarEvent(new int [] {5, 1}));
        Log.e("asdasdasda","asdasdasd");
        calculateDifferences();
        if (differenceBuildings != 0 && differenceTroops != 0) {
            if (!CozApp.isApplicationVisible()) {
                Log.e("syncreceiver", "i am doing background stuff");
                notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                checkForBuildingsAndNotify();
                checkForTroopsAndNotify();
            } else {
                Log.e("syncreceiver", "i am doing stuff");
                checkForBuildingsAndPostEvent();
                checkForTroopsAndPostEvent();
                EventBus.getDefault().post(new NavBarEvent(new int [] {differenceBuildings, differenceTroops}));
            }
        }
    }

    private void calculateDifferences() {
        int currentNumberOfBuildings = preferences.getInt("buildings", 0);
        int currentNumberOfTroops = preferences.getInt("troops", 0);
        int updatedNumberOfBuildings = syncedKingdom.getBuildings().size();
        int updatedNumberOfTroops = syncedKingdom.getTroops().size();
        differenceBuildings = currentNumberOfBuildings - updatedNumberOfBuildings;
        differenceTroops = currentNumberOfTroops - updatedNumberOfTroops;
    }

    private void checkForBuildingsAndNotify() {
        if (differenceBuildings < 0) {
            sendNotification(Math.abs(differenceBuildings), NEW_BUILDINGS, "buildings");
        }
        if (differenceBuildings > 0) {
            sendNotification(differenceBuildings, KILL_BUILDINGS, "buildings");
        }
    }

    private void checkForTroopsAndNotify() {
        if (differenceTroops < 0) {
            sendNotification(Math.abs(differenceTroops), NEW_TROOPS, "troops");
        }
        if (differenceTroops > 0) {
            sendNotification(differenceTroops, KILL_TROOPS, "troops");
        }
    }

    private void sendNotification(int numberChanged, String content, String fragment){
        NotificationCompat.Builder notificationBuilder = new
                NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_castle_black)
                .setContentTitle(numberChanged + content)
                .setContentText(numberChanged + content);
        Intent notificationIntent = new Intent(context, MainActivity.class);
        notificationIntent.putExtra("notification", "true");
        notificationIntent.putExtra("notificationID", "123");
        notificationIntent.putExtra("fragment", fragment);
        TaskStackBuilder tStackBuilder = TaskStackBuilder.create(context);
        tStackBuilder.addParentStack(MainActivity.class);
        tStackBuilder.addNextIntent(notificationIntent);
        PendingIntent pendingIntent = tStackBuilder.getPendingIntent(0,
                PendingIntent.FLAG_UPDATE_CURRENT);
        notificationBuilder.setContentIntent(pendingIntent);
        notificationManager.notify(123, notificationBuilder.build());
    }

    private void checkForBuildingsAndPostEvent() {
        if (differenceBuildings != 0) {
            EventBus.getDefault().post(new BuildingsEvent(syncedKingdom.getBuildings()));
        }
    }

    private void checkForTroopsAndPostEvent() {
        if (differenceTroops != 0) {
            EventBus.getDefault().post(new TroopsEvent(syncedKingdom.getTroops()));
        }
    }

    private void syncKingdom() {
        services.apiService.getKingdom().enqueue(new Callback<KingdomResponse>() {
            @Override
            public void onResponse(Call<KingdomResponse> call, Response<KingdomResponse> response) {
                syncedKingdom = response.body().getKingdom();
            }
            @Override
            public void onFailure(Call<KingdomResponse> call, Throwable t) {}
        });
    }
}
