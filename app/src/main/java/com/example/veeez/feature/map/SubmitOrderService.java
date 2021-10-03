package com.example.veeez.feature.map;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.veeez.R;
import com.example.veeez.services.http.map.MapApiInterface;
import com.mapbox.mapboxsdk.geometry.LatLng;

import java.util.Timer;

import static com.example.veeez.common.Base.EXTRA_KEY_DESTINATION_LAT;
import static com.example.veeez.common.Base.EXTRA_KEY_DESTINATION_LON;
import static com.example.veeez.common.Base.EXTRA_KEY_ORIGIN_LAT;
import static com.example.veeez.common.Base.EXTRA_KEY_ORIGIN_LON;

public class SubmitOrderService extends Service {

    private Timer timer;
    private LatLng origin;
    private LatLng destination;
    int a = 0;

    private MapApiInterface mapApiInterface;


    private final int NOTIFICATION_ID = 100;
    private final String NOTIFICATION_CHANNEL_ID = "STATUS_CHANNEL";
    private NotificationManager notificationManager;

    @Override
    public void onCreate() {
        super.onCreate();

        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent.getExtras() != null) {
            Bundle bundle = intent.getExtras();

            if (bundle != null) {
                origin = new LatLng(Double.parseDouble(bundle.getString(EXTRA_KEY_ORIGIN_LAT)), Double.parseDouble(bundle.getString(EXTRA_KEY_ORIGIN_LON)));
                destination = new LatLng(Double.parseDouble(bundle.getString(EXTRA_KEY_DESTINATION_LAT)), Double.parseDouble(bundle.getString(EXTRA_KEY_DESTINATION_LON)));
            }
        }

        if (origin != null && destination != null) {
            new Thread(new Runnable() {
                @Override
                public void run() {

                    startForeground(NOTIFICATION_ID,getNotification(true));
                    // TODO: 9/26/2021 send request for order status

                    notificationManager.notify(NOTIFICATION_ID,getNotification(false));

                    stopForeground(false);
                    stopSelf();


                }
            });


        }

        return START_STICKY;
    }

    public Notification getNotification(boolean waiting){
       return new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
                .setContentTitle("content title")
                .setContentText("waiting")
                .setSmallIcon(R.drawable.ic_back)
                .build();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
