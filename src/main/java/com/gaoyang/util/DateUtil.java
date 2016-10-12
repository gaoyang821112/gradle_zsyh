package com.gaoyang.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Ray on 2016/4/17.
 */
public class DateUtil {

    public static long getDiffMin (String endDate) throws Exception {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = sd.parse(endDate).getTime() - sd.parse(sd.format(new Date())).getTime();
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        return min;
    }

    public static long getDiffHour (String endDate) throws Exception {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = sd.parse(endDate).getTime() - sd.parse(sd.format(new Date())).getTime();
        // 计算差多少分钟
        long hour = diff % nd / nh;
        return hour;
    }

    public static long getDiffDay (String endDate) throws Exception {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = sd.parse(endDate).getTime() - sd.parse(sd.format(new Date())).getTime();
        // 计算差多少分钟
        long day = diff / nd;
        return day;
    }

//    public static void main(String[] args) throws Exception{
//        String endDate = "2016-04-17 11:57:47";
//        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        long nd = 1000 * 24 * 60 * 60;
//        long nh = 1000 * 60 * 60;
//        long nm = 1000 * 60;
//        // long ns = 1000;
//        // 获得两个时间的毫秒时间差异
//        long diff = sd.parse(endDate).getTime() - sd.parse(sd.format(new Date())).getTime();
//        // 计算差多少分钟
//        long hour = diff % nd / nh;
//        System.out.println(hour);
//    }

}
