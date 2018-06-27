package com.yuong.csv.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by yuandong on 2018/6/27.
 */

public class DateUtil {

    public static final String DATE_YMD = "yyyy-MM-dd";
    /**
     * 获取当前系统时间
     */
    public static String getNowTime(String aMask) {
        SimpleDateFormat df = new SimpleDateFormat(aMask, Locale.getDefault());
        return df.format(new Date());
    }
}
