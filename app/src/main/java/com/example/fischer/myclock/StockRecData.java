package com.example.fischer.myclock;

/**
 * Created by fischer on 2017/8/26.
 */

public class StockRecData {
    String code;
    String open_price;
    String close_price;
    String current_price;
    String high_price;
    String low_price;
    String deal_num;
    String deal_amount;
    String buy_num1;
    String buy_price1;
    String buy_num2;
    String buy_price2;
    String buy_num3;
    String buy_price3;
    String buy_num4;
    String buy_price4;
    String buy_num5;
    String buy_price5;
    String sell_num1;
    String sell_price1;
    String sell_num2;
    String sell_price2;
    String sell_num3;
    String sell_price3;
    String sell_num4;
    String sell_price4;
    String sell_num5;
    String sell_price5;
    String update_date;
    String update_time;

    public StockRecData(String i1, String i2, String i3, String i4, String i5,
                        String i6, String i7, String i8, String i9, String i10,
                        String i11, String i12, String i13, String i14, String i15,
                        String i16, String i17, String i18, String i19, String i20,
                        String i21, String i22, String i23, String i24, String i25,
                        String i26, String i27, String i28, String i29, String i30)
    {
        code = i1;
        open_price = i2;
        close_price = i3;
        current_price = i4;
        high_price = i5;
        low_price = i6;
        deal_num = i7;
        deal_amount = i8;
        buy_num1 = i9;
        buy_price1 = i10;
        buy_num2 = i11;
        buy_price2 = i12;
        buy_num3 = i13;
        buy_price3 = i14;
        buy_num4 = i15;
        buy_price4 = i16;
        buy_num5 = i17;
        buy_price5 = i18;
        sell_num1 = i19;
        sell_price1 = i20;
        sell_num2 = i21;
        sell_price2 = i22;
        sell_num3 = i23;
        sell_price3 = i24;
        sell_num4 = i25;
        sell_price4 = i26;
        sell_num5 = i27;
        sell_price5 = i28;
        update_date = i29;
        update_time = i30;
    }

    public StockRecData(String cur_price, String date, String dealNum, String time) {
        current_price = cur_price;
        update_date = date;
        deal_num = dealNum;
        update_time = time;
    }
}
