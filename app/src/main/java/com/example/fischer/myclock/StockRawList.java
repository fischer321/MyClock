package com.example.fischer.myclock;


import java.util.ArrayList;
import java.util.List;

public class StockRawList {


    public List<NameRecData> GetStockList() {

        String [] codes = {"sh601238", "sh601360", "sz300104", "sh600908", "sh600519",
                           "sz002739", "sz300168", "sh601229", "sh600926", "sz000002",
                           "sz000901", "sz000338", "sh601318", "sz000651", "sh600009",
                           "sh600660", "sh601766", "sh601328", "sh600016", "sh601939",
                           "sh601398", "sh601857", "sh600028", "sz300431", "sz002508",
                           "sz200037", "sz002195", "sh600075", "sh000001", "sz399001"};
        String [] names = {"广汽集团", "三六零", "乐视网", "无锡银行", "贵州茅台",
                           "万达电影", "万达信息", "上海银行", "杭州银行", "万 科Ａ",
                           "航天科技", "潍柴动力", "中国平安", "格力电器", "上海机场",
                           "福耀玻璃", "中国中车", "交通银行", "民生银行", "建设银行",
                           "工商银行", "中国石油", "中国石化", "暴风集团", "老板电器",
                           "深南电B", "二三四五", "新疆天业", "上证指数", "深证成指"};

        boolean [] valids = {true, true, true, true, true,
                             true, true, true, true, true,
                             true, true, true, true, true,
                             true, true, true, true, true,
                             true, true, true, true, true,
                             true, true, true, true, true};

        List<NameRecData> retList = new ArrayList<NameRecData>();
        for (int loop=0; loop<codes.length; loop++) {
            NameRecData rcd = new NameRecData(codes[loop], names[loop], valids[loop]);
            retList.add(rcd);
        }


        return retList;

    }
}
