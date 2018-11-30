package com.global.ustewardUtil;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.text.format.Time;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Okamiy on 2017/4/25.
 * Email: 542839122@qq.com
 */

public class DateUtils {
    public final static String timeFormat = "yyyy/MM/dd HH:mm";// 时间格式

    /**
     * 与当前时间比较早晚
     *
     * @param time 需要比较的时间
     * @return 输入的时间比现在时间晚则返回true
     */
    public static boolean compareNowTime(String time) {
        boolean isDayu = false;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date parse = dateFormat.parse(time);
            Date parse1 = dateFormat.parse(getNowTime());
            long diff = parse1.getTime() - parse.getTime();
            if (diff <= 0) {
                isDayu = true;
            } else {
                isDayu = false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return isDayu;
    }

    private SimpleDateFormat sf = null;

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getStatetime() {
        SimpleDateFormat sdf = new SimpleDateFormat(timeFormat);
        Calendar c = Calendar.getInstance();
        Date monday = c.getTime();
        String preMonday = sdf.format(monday);
        return preMonday;
    }

    public static String getLaterTime(int laterYear) {
        SimpleDateFormat sdf = new SimpleDateFormat(timeFormat);
        Calendar calendar = Calendar.getInstance();
        Date date = new Date(System.currentTimeMillis());
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, laterYear);
        return sdf.format(calendar.getTime());
    }

    //获取当前时间
    public static String getStatetime(int i) {
        SimpleDateFormat sdf = new SimpleDateFormat(timeFormat);
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, i);
        Date monday = c.getTime();
        String preMonday = sdf.format(monday);
        return preMonday;
    }

    public static String getNowTime() {
        String timeString = null;
        Time time = new Time();
        time.setToNow();
        String year = thanTen(time.year);
        String month = thanTen(time.month + 1);
        String monthDay = thanTen(time.monthDay);
        String hour = thanTen(time.hour);
        String minute = thanTen(time.minute);

        timeString = year + "-" + month + "-" + monthDay + " " + hour + ":"
                + minute;
        // System.out.println("-------timeString----------" + timeString);
        return timeString;
    }

    /**
     * 十一下加零
     *
     * @param str
     * @return
     */
    public static String thanTen(int str) {

        String string = null;

        if (str < 10) {
            string = "0" + str;
        } else {

            string = "" + str;

        }
        return string;
    }


    /**
     * 计算时间差
     *
     * @param starTime 开始时间
     * @param endTime  结束时间
     * @return 返回时间差
     */
    public static String getTimeDifference(String starTime, String endTime) {
        String timeString = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        try {
            Date parse = dateFormat.parse(starTime);
            Date parse1 = dateFormat.parse(endTime);

            long diff = parse1.getTime() - parse.getTime();

            long day = diff / (24 * 60 * 60 * 1000);
            long hour = (diff / (60 * 60 * 1000) - day * 24);
            long min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            long s = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
            long ms = (diff - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000
                    - min * 60 * 1000 - s * 1000);
            long hour1 = diff / (60 * 60 * 1000);
            String hourString = hour1 + "";
            long min1 = ((diff / (60 * 1000)) - hour1 * 60);
            timeString = hour1 + "小时" + min1 + "分";
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timeString;
    }

    /**
     * 比较两个时间
     *
     * @param starTime  开始时间
     * @param endString 结束时间
     * @return 结束时间大于开始时间返回true，否则反之֮
     */
    public static boolean compareTwoTime(String starTime, String endString) {
        boolean isDayu = false;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date parse = dateFormat.parse(starTime);
            Date parse1 = dateFormat.parse(endString);

            long diff = parse1.getTime() - parse.getTime();
            if (diff > 0) {
                isDayu = true;
            } else {
                isDayu = false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return isDayu;

    }

    /**
     * long型时间转换为String
     *
     * @param currentTime
     * @return
     * @throws ParseException
     */
    public static String longToString(@NonNull long currentTime) {
        return longToString(currentTime, timeFormat);
    }

    /**
     * long型时间转换为String
     *
     * @param currentTime 要转换的long类型的时间
     * @param formatType  要转换的string类型的时间格式 yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
     * @return
     * @throws ParseException
     */
    public static String longToString(long currentTime, String formatType) {
        String strTime = "";
        if (currentTime != 0) {
            try {
                Date date = longToDate(currentTime, formatType);// long类型转成Date类型
                strTime = dateToString(date, formatType); // date类型转成String
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return strTime;
    }


    /**
     * @param currentTime 要转换的long类型的时间
     * @param formatType  要转换的时间格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
     * @return
     * @throws ParseException
     */
    public static Date longToDate(@NonNull long currentTime, String formatType)
            throws ParseException {
        Date dateOld = new Date(currentTime); // 根据long类型的毫秒数生命一个date类型的时间
        String sDateTime = dateToString(dateOld, formatType); // 把date类型的时间转换为string
        Date date = stringToDate(sDateTime, formatType); // 把String类型转换为Date类型
        return date;
    }

    /**
     * @param data       Date类型的时间
     * @param formatType 格式为yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
     * @return
     */
    public static String dateToString(@NonNull Date data, String formatType) {
        return new SimpleDateFormat(formatType).format(data);
    }

    /**
     * @param strTime    要转换的string类型的时间
     * @param formatType 要转换的string类型的时间
     * @return
     * @throws ParseException
     */
    public static Date stringToDate(String strTime, String formatType)
            throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        Date date = formatter.parse(strTime);
        return date;
    }

    // strTime要转换的String类型的时间
    // formatType时间格式
    // strTime的时间格式和formatType的时间格式必须相同
    public static long stringToLong(String strTime, String formatType) {
        Date date = null; // String类型转成date类型
        try {
            date = stringToDate(strTime, formatType);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date == null) {
            return 0;
        } else {
            return dateToLong(date);
        }
    }

    // date要转换的date类型的时间
    public static long dateToLong(Date date) {
        return date.getTime();
    }

    /**
     * 计算停车时长
     *
     * @param intoTime
     * @param outTime
     * @return 停车时长 X.xx（单位小时）
     */
    public static String calculateDuration(final String intoTime, final String outTime) {
        DecimalFormat myformat = new DecimalFormat("0.00");
        long time = stringToLong(outTime, "yyyy-MM-dd HH:mm:ss.SSS") - stringToLong(intoTime, "yyyy-MM-dd HH:mm:ss.SSS");
        double c = time / 3600000.00;
        return myformat.format(c);
    }

    /**
     * 获取yyyy-MM-dd HH:mm格式的时间字符串
     *
     * @param payTime
     * @return
     */
    public static String stringToString(String payTime) {
        if (TextUtils.isEmpty(payTime))
            return "";
        String[] split = payTime.split(":");
        if (split.length > 2) {
            payTime = split[0] + ":" + split[1];
        }
        return payTime;
    }
}
