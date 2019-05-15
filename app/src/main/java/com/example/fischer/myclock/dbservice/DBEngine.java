package com.example.fischer.myclock.dbservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class DBEngine extends Service {
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("DBEngine", "on start command xx");
        return Service.START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
