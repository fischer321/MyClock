package com.example.fischer.myclock;

import android.app.Activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends Activity {

    private TextView clock_text;
    private CountDownTimer newtimer;
    private Button mInsertBtn, mFetchDataBtn;
    private StockDataHelper mStockDataHelper;
    private EditText mCodeEdit, mNameEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clock_text = (TextView)findViewById(R.id.textView);
        newtimer = new CountDownTimer(1000000000, 1000) {

            public void onTick(long millisUntilFinished) {
                Calendar c = Calendar.getInstance();
                //clock_text.setText(c.get(Calendar.HOUR)+":"+c.get(Calendar.MINUTE)+":"+c.get(Calendar.SECOND));
                //clock_text.setText(String.format("%1$tH:%1$tM:%1$tS", c));
                clock_text.setText(String.format("%02d:%02d:%02d", c.get(Calendar.HOUR)+8, c.get(Calendar.MINUTE), c.get(Calendar.SECOND)));
            }
            public void onFinish() {

            }
        };
        newtimer.start();

        initUI();
        initData();
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        newtimer.cancel();
    }

    private void initUI() {
        mInsertBtn = (Button) findViewById(R.id.btn_init_stock_list);
        mFetchDataBtn = (Button) findViewById(R.id.btn_fetch_stock_data);

        mCodeEdit = (EditText) findViewById(R.id.editCode);
        mNameEdit = (EditText) findViewById(R.id.editName);

        mInsertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = mCodeEdit.getText().toString();
                String name = mNameEdit.getText().toString();
                if (code.length()>0 && name.length()>0) {
                    mStockDataHelper.appendStockList(new NameRecData(code, name, true));
                } else {
                    mStockDataHelper.insertStockList();
                }

            }
        });



        mFetchDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStockDataHelper.fetchStockData();
            }
        });
    }

    private void initData() {
        mStockDataHelper = new StockDataHelper(this.getApplicationContext());
    }


}
