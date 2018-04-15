package com.example.fischer.myclock;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.List;

public class HttpHelper {

    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    //url http://hq.sinajs.cn/list=sh601006,sh000001
    private String url = "http://hq.sinajs.cn/list=";
    private static final String TAG = HttpHelper.class.getName();
    public void sendAndRequestResponse(Context context, final StockDataHelper dataHelper, List<String> codeList) {

        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(context);
        String getURL = url;
        for (String code : codeList) {
            getURL += code;
            getURL += ",";
        }

        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, getURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    //String data = new String(response.getBytes("ISO-8859-1"),"GBK");
                    String data = response.toString();
                    procesHtmlContent(data, dataHelper);
                } catch (Exception e) {
                    Log.i(TAG,"Error :" + e.toString());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG,"Error :" + error.toString());
            }
        });

        mStringRequest.setRetryPolicy(new DefaultRetryPolicy(
                20000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        mRequestQueue.add(mStringRequest);
    }

    private void procesHtmlContent(String htmlTxt, final StockDataHelper dataHelper) {

        //Log.i(TAG,"CONTENT :" + htmlTxt);
        String StockContent[] = htmlTxt.split(";");
        String code;
//        String open_price;
//        String close_price;
//        String current_price;
//        String high_price;
//        String low_price;
//        String deal_num;
//        String deal_amount;
//        String buy_num1;
//        String buy_price1;
//        String buy_num2;
//        String buy_price2;
//        String buy_num3;
//        String buy_price3;
//        String buy_num4;
//        String buy_price4;
//        String buy_num5;
//        String buy_price5;
//        String sell_num1;
//        String sell_price1;
//        String sell_num2;
//        String sell_price2;
//        String sell_num3;
//        String sell_price3;
//        String sell_num4;
//        String sell_price4;
//        String sell_num5;
//        String sell_price5;
//        String update_date;
//        String update_time;

        for (int i=0; i<StockContent.length; i++) {
            String content = StockContent[i];
            if (content.length() > 10) {
                String NamePair[] = content.split("=");
                code = NamePair[0].substring(NamePair[0].indexOf("hq_str_")+7);
                NamePair[1] = NamePair[1].replace("\"", "");
                String recData[] = NamePair[1].split(",");

                StockRecData stockData = new StockRecData(code, recData[1], recData[2], recData[3], recData[4],
                        recData[5], recData[8], recData[9], recData[10], recData[11],
                        recData[12], recData[13], recData[14], recData[15], recData[16],
                        recData[17], recData[18], recData[19], recData[20], recData[21],
                        recData[22], recData[23], recData[24], recData[25], recData[26],
                        recData[27], recData[28], recData[29], recData[30], recData[31]);

                dataHelper.insertRecordData(stockData);
            }

        }

    }

}
