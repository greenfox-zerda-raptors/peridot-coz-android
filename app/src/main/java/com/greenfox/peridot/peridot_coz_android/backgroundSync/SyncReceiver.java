package com.greenfox.peridot.peridot_coz_android.backgroundSync;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;
import com.greenfox.peridot.peridot_coz_android.CozApp;
import com.greenfox.peridot.peridot_coz_android.R;
import com.greenfox.peridot.peridot_coz_android.activity.MainActivity;
import com.greenfox.peridot.peridot_coz_android.api.ApiService;
import com.greenfox.peridot.peridot_coz_android.model.pojo.Kingdom;
import com.greenfox.peridot.peridot_coz_android.model.response.KingdomResponse;
import com.greenfox.peridot.peridot_coz_android.provider.DaggerApiComponent;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SyncReceiver extends BroadcastReceiver {

    @Inject
    ApiService apiService;
    Kingdom syncedKingdom;

    @Override
    public void onReceive(Context context, Intent intent) {
        DaggerApiComponent.builder().build().inject(this);
        syncKingdom();
        if (CozApp.isApplicationVisible()) {
            Log.e("syncreceiver", "i am doing background stuff");
            /// TODO throw notification if app is closed (not bus)
            showNotification(context);
        } else {
            Log.e("syncreceiver", "i am doing stuff");
        }
        /// TODO update current fragment (bus)
        /// TODO update navbar with numbers (bus)

    }

    private void showNotification(Context context) {
        NotificationCompat.Builder notificationBuilder = new
                NotificationCompat.Builder(context)
                .setContentTitle("New buildings")
                .setContentText("New Buildings available")
                .setSmallIcon(R.drawable.ic_castle_black);
        Intent notificationIntent = new Intent(context, MainActivity.class);
        notificationIntent.putExtra("fragment", "buildings");
        notificationIntent.putExtra("notification", "true");
        notificationIntent.putExtra("notificationID", "123");
        TaskStackBuilder tStackBuilder = TaskStackBuilder.create(context);
        tStackBuilder.addParentStack(MainActivity.class);
        tStackBuilder.addNextIntent(notificationIntent);
        PendingIntent pendingIntent = tStackBuilder.getPendingIntent(0,
                PendingIntent.FLAG_UPDATE_CURRENT);
        notificationBuilder.setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(123, notificationBuilder.build());
    }

    public void syncKingdom() {
        apiService.getKingdom().enqueue(new Callback<KingdomResponse>() {
            @Override
            public void onResponse(Call<KingdomResponse> call, Response<KingdomResponse> response) {
                syncedKingdom = response.body().getKingdom();
            }
            @Override
            public void onFailure(Call<KingdomResponse> call, Throwable t) {
            }
        });
    }
}
