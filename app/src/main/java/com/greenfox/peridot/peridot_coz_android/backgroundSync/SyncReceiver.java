package com.greenfox.peridot.peridot_coz_android.backgroundSync;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import com.greenfox.peridot.peridot_coz_android.R;
import com.greenfox.peridot.peridot_coz_android.activity.MainActivity;

public class SyncReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
//        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
//        vibrator.vibrate(1000);
        showNotification(context);

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
}
