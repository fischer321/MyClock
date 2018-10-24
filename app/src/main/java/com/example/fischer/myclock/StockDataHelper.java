package com.example.fischer.myclock;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fischer on 2017/8/26.
 */

public class StockDataHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME="/sdcard/Android/data/stock_record.db";//数据库名称
    private static final int SCHEMA_VERSION=1;//版本号,则是升级之后的,升级方法请看onUpgrade方法里面的判断
    private Context mContext;
    private ArrayList<String> mStockNameList;
    private ArrayList<String> mStockCodeList;

    public StockDataHelper(Context context) {//构造函数,接收上下文作为参数,直接调用的父类的构造函数
        super(context, DATABASE_NAME, null, SCHEMA_VERSION);
        mContext = context;

        mStockNameList = null;
        mStockCodeList = null;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE STOCK_LIST (_id INTEGER PRIMARY KEY AUTOINCREMENT, _code TEXT, _name TEXT, _valid BOOLEAN);");

        db.execSQL("CREATE TABLE REC_LIST (_id INTEGER PRIMARY KEY AUTOINCREMENT, _code TEXT, open_price TEXT, close_price TEXT, current_price TEXT," +
                                           "high_price TEXT, low_price TEXT,   deal_num TEXT," +
                                           "deal_amount TEXT,buy_num1 TEXT,    buy_price1 TEXT," +
                                           "buy_num2 TEXT,   buy_price2 TEXT,  buy_num3 TEXT," +
                                           "buy_price3 TEXT, buy_num4 TEXT,    buy_price4 TEXT," +
                                           "buy_num5 TEXT,   buy_price5 TEXT,  sell_num1 TEXT," +
                                           "sell_price1 TEXT,sell_num2 TEXT,   sell_price2 TEXT," +
                                           "sell_num3 TEXT,  sell_price3 TEXT, sell_num4 TEXT," +
                                           "sell_price4 TEXT,sell_num5 TEXT,   sell_price5 TEXT," +
                                           "update_date TEXT,update_time TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion==1 && newVersion==2) {//
            db.execSQL("ALTER TABLE REC_LIST ADD phone TEXT;");
        }
    }

    public void insertRecordData(StockRecData record) {

        if (!isStockRecordExisted(record.code, record.update_date)) {

            ContentValues cv=new ContentValues();

            cv.put("_code", record.code);
            cv.put("open_price", record.open_price);
            cv.put("close_price", record.close_price);
            cv.put("current_price", record.current_price);
            cv.put("high_price", record.high_price);
            cv.put("low_price", record.low_price);
            cv.put("deal_num", record.deal_num);
            cv.put("deal_amount", record.deal_amount);
            cv.put("buy_num1", record.buy_num1);
            cv.put("buy_price1", record.buy_price1);
            cv.put("buy_num2", record.buy_num2);
            cv.put("buy_price2", record.buy_price2);
            cv.put("buy_num3", record.buy_num3);
            cv.put("buy_price3", record.buy_price3);
            cv.put("buy_num4", record.buy_num4);
            cv.put("buy_price4", record.buy_price4);
            cv.put("buy_num5", record.buy_num5);
            cv.put("buy_price5", record.buy_price5);
            cv.put("sell_num1", record.sell_num1);
            cv.put("sell_price1", record.sell_price1);
            cv.put("sell_num2", record.sell_num2);
            cv.put("sell_price2", record.sell_price2);
            cv.put("sell_num3", record.sell_num3);
            cv.put("sell_price3", record.sell_price3);
            cv.put("sell_num4", record.sell_num4);
            cv.put("sell_price4", record.sell_price4);
            cv.put("sell_num5", record.sell_num5);
            cv.put("sell_price5", record.sell_price5);
            cv.put("update_date", record.update_date);
            cv.put("update_time", record.update_time);

            getWritableDatabase().insert("REC_LIST", "_code", cv);
        } else {
            updateRecordData(record);
        }

    }

    private void updateRecordData(StockRecData record) {

        ContentValues cv=new ContentValues();

        cv.put("open_price", record.open_price);
        cv.put("close_price", record.close_price);
        cv.put("current_price", record.current_price);
        cv.put("high_price", record.high_price);
        cv.put("low_price", record.low_price);
        cv.put("deal_num", record.deal_num);
        cv.put("deal_amount", record.deal_amount);
        cv.put("buy_num1", record.buy_num1);
        cv.put("buy_price1", record.buy_price1);
        cv.put("buy_num2", record.buy_num2);
        cv.put("buy_price2", record.buy_price2);
        cv.put("buy_num3", record.buy_num3);
        cv.put("buy_price3", record.buy_price3);
        cv.put("buy_num4", record.buy_num4);
        cv.put("buy_price4", record.buy_price4);
        cv.put("buy_num5", record.buy_num5);
        cv.put("buy_price5", record.buy_price5);
        cv.put("sell_num1", record.sell_num1);
        cv.put("sell_price1", record.sell_price1);
        cv.put("sell_num2", record.sell_num2);
        cv.put("sell_price2", record.sell_price2);
        cv.put("sell_num3", record.sell_num3);
        cv.put("sell_price3", record.sell_price3);
        cv.put("sell_num4", record.sell_num4);
        cv.put("sell_price4", record.sell_price4);
        cv.put("sell_num5", record.sell_num5);
        cv.put("sell_price5", record.sell_price5);
        cv.put("update_time", record.update_time);

        int retCount = getWritableDatabase().update("REC_LIST",
                                          cv,
                               "_code=? and update_date=?",
                new String[]{ record.code, record.update_date });

        Log.i("updateRecordData","ret count :" + retCount);
    }

    public ArrayList<String> queryStockName()
    {
         String strName;
        String codeName;
        ArrayList<String> retList;
         if (mStockNameList == null) {
             retList = new ArrayList<String>();
             ArrayList<String> codeList = new ArrayList<String>();

             Cursor cursor = getReadableDatabase().query("STOCK_LIST",
                     new String[]{ "_code", "_name", "_valid" },
                     "",
                     new String[]{ },
                     null, null, null);
             while(cursor.moveToNext()){
                 strName = cursor.getString(cursor.getColumnIndex("_name"));
                 codeName = cursor.getString(cursor.getColumnIndex("_code"));
                 retList.add(strName);
                 codeList.add(codeName);
             }

             cursor.close();

             mStockNameList = retList;
             mStockCodeList = codeList;

         } else {
             retList = mStockNameList;
         }


        return retList;
    }

    public String getCodeByIndex(int index) {
        return mStockCodeList.get(index);
    }

    public ArrayList<StockRecData> queryStockData(String code)
    {
        ArrayList<StockRecData> retList = new ArrayList<StockRecData>();
        String price;
        String date;
        String dealNum;
        String time;
        StockRecData recData;

        Cursor cursor = getReadableDatabase().query("REC_LIST",
                new String[]{ "current_price", "update_date", "deal_num", "update_time" },
                "_code=?",
                new String[]{ ""+code},
                null, null, null);
        while(cursor.moveToNext()){
            price = cursor.getString(cursor.getColumnIndex("current_price"));
            date = cursor.getString(cursor.getColumnIndex("update_date"));
            dealNum = cursor.getString(cursor.getColumnIndex("deal_num"));
            time = cursor.getString(cursor.getColumnIndex("update_time"));

            recData = new StockRecData(price, date, dealNum, time);
            retList.add(recData);
        }

        cursor.close();

        return retList;
    }

    public void insertStockList() {
        StockRawList rawList = new StockRawList();
        List<NameRecData> items = rawList.GetStockList();
        for(NameRecData rec : items) {
            insertStockName(rec);
        }
    }

    public void appendStockList(NameRecData rec) {
        insertStockName(rec);
    }

    public void fetchStockData() {
        List<String> codeList = getStockListToFetchData();
        HttpHelper httpHelper = new HttpHelper();
        httpHelper.sendAndRequestResponse(mContext, this, codeList);
//        for (String stockCode : codeList) {
//            String code = stockCode;
//        }
    }

    private void insertStockName(NameRecData stockNameRecord)
    {
         if (!isStockNameExisted(stockNameRecord.code)) {
             ContentValues cv=new ContentValues();

             cv.put("_code", stockNameRecord.code);
             cv.put("_name", stockNameRecord.name);
             cv.put("_valid", stockNameRecord.valid);

             getWritableDatabase().insert("STOCK_LIST", "_code", cv);
         }
    }

    private boolean isStockNameExisted(String code)
    {
         boolean ret = false;

        //参数1：表名
        //参数2：要想显示的列
       //参数3：where子句
       //参数4：where子句对应的条件值
       // 参数5：分组方式
       //参数6：having条件
      //参数7：排序方式
        Cursor cursor = getReadableDatabase().query("STOCK_LIST",
                                                  new String[]{ "_code", "_name", "_valid" },
                                                  "_code=?",
                                                  new String[]{ ""+code },
                                                 null, null, null);
        ret =  cursor.getCount() > 0;

        cursor.close();

        return ret;
    }

    private boolean isStockRecordExisted(String code, String date)
    {
        boolean ret = false;

        //参数1：表名
        //参数2：要想显示的列
        //参数3：where子句
        //参数4：where子句对应的条件值
        // 参数5：分组方式
        //参数6：having条件
        //参数7：排序方式
        Cursor cursor = getReadableDatabase().query("REC_LIST",
                new String[]{ "_code", "update_date" },
                "_code=? and update_date=?",
                new String[]{ ""+code,  ""+date},
                null, null, null);
        ret =  cursor.getCount() > 0;

        cursor.close();

        return ret;
    }

    private List<String> getStockListToFetchData() {
        List<String> retList = new ArrayList<>();
        Cursor cursor = getReadableDatabase().query("STOCK_LIST",
                new String[]{ "_code" },
                "_valid=?",
                new String[]{"1"},  //T-SQL use 1 represent true
                null, null, null);

        while(cursor.moveToNext()){
            String stockCode = cursor.getString(cursor.getColumnIndex("_code"));
            retList.add(stockCode);
        }

        return retList;
    }



    private  void delete(String rec_date) {

//        String whereClauses = "rec_date=?";
//        String [] whereArgs = {rec_date};
//        getReadableDatabase().delete("WAMALLFDRATE", whereClauses, whereArgs);
    }


}
