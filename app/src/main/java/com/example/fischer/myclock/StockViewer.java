package com.example.fischer.myclock;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class StockViewer extends Activity {

    private Spinner mySpinner;
    private ArrayAdapter<String> mySpinnerAdapter;
    private StockDataHelper mStockDataHelper;
    ListView listview_record;
    private ArrayList<HashMap<String,String>> hashmapList;

    listviewAdapter listview_adapter;

    private final String FIRST_COLUMN = "1";
    private final String SECOND_COLUMN = "2";
    private final String THIRD_COLUMN = "3";
    private final String FOURTH_COLUMN = "4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_viewer);

        initUI();
        initData();
    }

    private void initUI() {

        mySpinner = (Spinner)findViewById(R.id.spinnerMonth);
        listview_record = (ListView) findViewById(R.id.listview);

    }

    private void fillSpinnerStockList()
    {
        ArrayList<String> stockList = mStockDataHelper.queryStockName();
        mySpinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, stockList);
        mySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(mySpinnerAdapter);
        //mySpinner.setSelection(mySpinnerAdapter.getPosition(todayYearMonth));

        mySpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

                String stockName = mySpinnerAdapter.getItem(arg2);
                arg0.setVisibility(View.VISIBLE);
                int index = arg2;
                String code = mStockDataHelper.getCodeByIndex(index);

                ArrayList<StockRecData> recList = mStockDataHelper.queryStockData(code);
                fillListView(recList);

                //fillListView();

            }
            public void onNothingSelected(AdapterView<?> arg0) {
                arg0.setVisibility(View.VISIBLE);
            }
        });
        /*下拉菜单弹出的内容选项触屏事件处理*/
        mySpinner.setOnTouchListener(new Spinner.OnTouchListener(){
            public boolean onTouch(View v, MotionEvent event) {

                return false;
            }
        });
        /*下拉菜单弹出的内容选项焦点改变事件处理*/
        mySpinner.setOnFocusChangeListener(new Spinner.OnFocusChangeListener(){
            public void onFocusChange(View v, boolean hasFocus) {


            }
        });
    }

    private void initData() {
        mStockDataHelper = new StockDataHelper(this.getApplicationContext());
        fillSpinnerStockList();
    }

    private void fillListView(ArrayList<StockRecData> recList)
    {
        populateListView(recList);
        listview_adapter = new listviewAdapter(this, hashmapList);
        listview_record.setAdapter(listview_adapter);
    }

    private void populateListView(ArrayList<StockRecData> recList)
    {
        hashmapList = new ArrayList<HashMap<String,String>>();



        for (StockRecData rec : recList) {
            HashMap<String,String> temp = new HashMap<String,String>();
            temp.put(FIRST_COLUMN, rec.update_date);
            temp.put(SECOND_COLUMN, rec.current_price);
            temp.put(THIRD_COLUMN, rec.deal_num);
            temp.put(FOURTH_COLUMN, rec.update_time);
            hashmapList.add(temp);
        }
    }
}
