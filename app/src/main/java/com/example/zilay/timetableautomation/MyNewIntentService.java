package com.example.zilay.timetableautomation;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

/**
 * Created by zilay on 12/7/17.
 */

public class MyNewIntentService extends IntentService {


    public MyNewIntentService() {
        super("MyNewIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        Intent myintent = new Intent();
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,myintent,0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("Next Class Notification");
        builder.setContentText("Your next class is about to start");
        builder.setDefaults(Notification.DEFAULT_SOUND);
        builder.setSmallIcon(R.drawable.calendar);
        builder.setContentIntent(pendingIntent).getNotification();
        Notification notificationCompat = builder.build();
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0 /* ID of notification */,
                builder.build());

    }
}
