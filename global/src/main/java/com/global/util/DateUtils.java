package com.global.util;


import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by bszspro on 2016/12/26 0026.本工具类的作用是把时间格式化
 */

public class DateUtils {
    /**
     * 返回String类型的时间格式：yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String transformDate2Str(Date date) {
        SimpleDateFormat myFmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return myFmt.format(date);
    }

    /**
     * 获取时间格式 yyyy-MM-dd HH:mm:ss (24小时制)
     *
     * @return
     */
    public static String getCurrentFormatString() {
        Date dt = new Date();
        SimpleDateFormat myFmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return myFmt.format(dt);
    }

    /**
     * 获取时间格式 月.日 时:分
     *
     * @return
     */

    public static String getCurrentDate() {
        Date dt = new Date();
        SimpleDateFormat myFmt = new SimpleDateFormat("MM.dd HH:mm");
        return myFmt.format(dt);
    }

    /**
     * 获取时间
     *
     * @return
     */
    public static String getCurrentFormatWithMill() {
        Date dt = new Date();
        SimpleDateFormat myFmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        return myFmt.format(dt);
    }

    public static void getFormatTimeValueOfhhmm(String time) {
        String currentFormatString = getCurrentFormatString();
        int[] t = new int[]{};
    }

    public static String getStringDateShort() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 获取时间 小时:分;秒 HH:mm:ss
     *
     * @return
     */
    public static String getTimeShort() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date currentTime = new Date();
        String dateString = formatter.format(currentTime);
        return dateString;
    }


    public static long getLongTimeFormat(String strTime) {
        long time = -1;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            time = simpleDateFormat.parse(strTime).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }

    public static Date strToDateLong(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }


    public static String getWeek(String sdate) {
        // 再转换为时间
        Date date = strToDateLong(sdate);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        // int hour=c.get(Calendar.DAY_OF_WEEK);
        // hour中存的就是星期几了，其范围 1~7
        // 1=星期日 7=星期六，其他类推
        return new SimpleDateFormat("EEEE").format(c.getTime());
    }

    /**
     * 根据一个日期，返回是星期几的字符串
     *
     * @param sdate
     * @return
     */
    public static String getWeekStr(String sdate) {
        String str = "";
        str = getWeek(sdate);
        if ("1".equals(str)) {
            str = "星期日";
        } else if ("2".equals(str)) {
            str = "星期一";
        } else if ("3".equals(str)) {
            str = "星期二";
        } else if ("4".equals(str)) {
            str = "星期三";
        } else if ("5".equals(str)) {
            str = "星期四";
        } else if ("6".equals(str)) {
            str = "星期五";
        } else if ("7".equals(str)) {
            str = "星期六";
        }
        return str;
    }

    /**
     * 根据一个日期，返回是xx月xx日的字符串
     *
     * @param formatTime
     * @return
     */
    public static String getStringDateMonth(String formatTime) {
        String sreturn = "";
        if (formatTime != null) {
            String s_nd = formatTime.substring(0, 4); // 年份
            String s_yf = formatTime.substring(5, 7); // 月份
            String s_rq = formatTime.substring(8, 10); // 日期
            sreturn = s_yf + "月" + s_rq + "日";

        }
        return sreturn;
    }

    /**
     * 控件展示日期的方法（最大：今天，最小：今天-10年）
     *
     * @param context 上下文
     * @param date    标题（截止日期）
     * @param view    控件
     */
    public static void showDateDialogUntilToday(Context context, String date, TextView view) {
        PickerViewUtil.showSelectDatePickerViewBefore(context, date, dateStr -> view.setText(dateStr));
    }

    /**
     * 控件展示日期的方法（最大：今天+10年，最小：今天）
     * 开始时间是当前时间，结束时间10年,默认选中当前时间
     *
     * @param context 上下文
     * @param date    标题（截止日期）
     * @param view    控件
     */
    public static void showDateDialogFromToday(Context context, String date, TextView view) {
        PickerViewUtil.showSelectDatePickerView(context, date, dateStr -> view.setText(dateStr));
    }

    /**
     * 控件展示日期的方法（最大：今天+10年，最小：今天-10年）
     * 开始时间是当前时间减去10年，结束时间当前时间+10年,默认选中当前时间
     *
     * @param context 上下文
     * @param date    标题（截止日期）
     * @param view    控件
     */
    public static void showDateDialogFromBefore2After(Context context, String date, TextView view) {
        PickerViewUtil.showSelectDatePickerViewBefore(context, date, dateStr -> view.setText(dateStr));
    }

    /**
     * 设置底部弹出的 dialog 样式等
     *
     * @param dialog        Dialog 对象
     * @param localView     Dialog布局对应的View
     * @param windowManager windowManager对象
     */
    public static void initDialog(Dialog dialog, View localView, WindowManager windowManager) {
        dialog.setContentView(localView);
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        // 设置全屏
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.width = display.getWidth(); // 设置宽度
        dialog.getWindow().setAttributes(lp);
    }
}
