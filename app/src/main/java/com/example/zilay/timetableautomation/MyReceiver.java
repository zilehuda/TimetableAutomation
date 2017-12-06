package com.example.zilay.timetableautomation;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by zilay on 12/7/17.
 */

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intent1 = new Intent(context, MyNewIntentService.class);
        context.startService(intent1);
    }
}
