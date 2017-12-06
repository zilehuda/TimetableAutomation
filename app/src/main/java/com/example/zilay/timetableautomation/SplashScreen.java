package com.example.zilay.timetableautomation;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import java.util.Calendar;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.zilay.timetableautomation.student.StudentMainActivity;
import com.example.zilay.timetableautomation.student.StudentRegisterActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        callBroadcast();
        //notification();
        SharedPreferences preferences = getSharedPreferences("login",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
        Thread thread = new Thread(){

            @Override
            public void run() {
                try {
                    sleep(3000);


                        CheckUser();


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();

        //CheckUser();

    }


    public void callBroadcast()
    {
        Intent notifyIntent = new Intent(this,MyReceiver.class);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,2);
        calendar.set(Calendar.MINUTE,54);
        calendar.set(Calendar.SECOND,00);

        PendingIntent pendingIntent = PendingIntent.getBroadcast
                (this, 100, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,  calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, pendingIntent);
    }
    public void notification()
    {
        Intent intent = new Intent();
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,intent,0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("My Title");
        builder.setContentText("This is the Body");
        builder.setSmallIcon(R.drawable.calendar);
        builder.setContentIntent(pendingIntent).getNotification();
        Notification notificationCompat = builder.build();
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0 /* ID of notification */,
                builder.build());

    }
    public void CheckUser()
    {
        if (getSharedPreferences("login",0).getBoolean("isLoginKey",false)){

            Intent intent = new Intent(SplashScreen.this,StudentMainActivity.class);
            startActivity(intent);
        }
        else
        {
            Intent intent = new Intent(SplashScreen.this,StudentRegisterActivity.class);
            startActivity(intent);
        }
    }

}
