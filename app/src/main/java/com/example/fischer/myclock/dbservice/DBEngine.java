package com.example.fischer.myclock.dbservice;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.example.fischer.myclock.NameRecData;
import com.example.fischer.myclock.StockDataHelper;

public class DBEngine extends Service {
    private StockDataHelper mStockDataHelper;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("DBEngine", "on start command xx");
        String action;
        Bundle extras = intent.getExtras();
        if (extras != null) {
            action = extras.getString("cmd");
            switch (action) {
                case "FetchData":
                    mStockDataHelper.fetchStockData();
                    break;
                case "InsertStockList":
                    mStockDataHelper.insertStockList();
                    break;
                case "AppendStockList":
                    String code = extras.getString("code");
                    String name = extras.getString("name");
                    mStockDataHelper.appendStockList(new NameRecData(code, name, true));
                    break;
                default:
                    break;
            }
        }
        return Service.START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        mStockDataHelper = new StockDataHelper(this.getApplicationContext());
    }
}
