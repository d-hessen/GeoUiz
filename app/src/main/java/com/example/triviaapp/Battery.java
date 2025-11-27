package com.example.triviaapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class Battery extends BroadcastReceiver {
    NotificationHelper notification;
    @Override
    public void onReceive(Context context, Intent intent) {
        int level = intent.getIntExtra("level", 0);
        if(level<15)
        {
            notification = new NotificationHelper(context);
            notification.createNotification("Battery Low","level is below 15%",0);
        }
    }
}
