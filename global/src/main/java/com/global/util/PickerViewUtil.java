package com.global.util;

import android.content.Context;
import android.graphics.Color;

import com.global.R;
import com.waterbase.utile.DateUtil;
import com.waterbase.utile.LogUtil;
import com.waterbase.utile.StrUtil;
import com.waterbase.widget.pickerview.OptionsPickerView;
import com.waterbase.widget.pickerview.TimePickerView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Water on 2018/4/13.
 */

public class PickerViewUtil {
    /**
     *
     * @param context
     * @param titleStr
     * @param selectListener
     * @param stringList
     */
    public static void showOptionsPickerView(Context context, String titleStr, SelectResultListener selectListener
            , List stringList) {
        OptionsPickerView pvOptions = new OptionsPickerView.Builder(context, (options1, options2, options3, v) -> {
            //返回的分别是三个级别的选中位置
            selectListener.result(stringList.get(options1));
        })
                .setTitleText(titleStr)
                .setTitleColor(context.getResources().getColor(R.color.pickerview_timebtn_nor))
                .setTextColorCenter(context.getResources().getColor(R.color.pickerview_timebtn_nor))
                .setDividerColor(context.getResources().getColor(R.color.pickerview_timebtn_nor))
                .setContentTextSize(16)
                .setOutSideCancelable(false)
                .isDialog(true)
                .build();
        pvOptions.setPicker(stringList);//一级选择器
        pvOptions.show();
    }


    public static void showSelectDatePickerView(Context context, String titleStr, DateSelectListener selectListener) {
        Calendar calendar = Calendar.getInstance();
        // 开始时间是当前时间，结束时间10年,默认选中当前时间
        showSelectDatePickerView(context, selectListener, titleStr
                , calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE)
                , calendar.get(Calendar.YEAR) + 10, calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE)
                , calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
    }


    public static void showSelectDate(Context context, String titleStr
            , DateSelectListener selectListener) {
        Calendar calendar = Calendar.getInstance();
        // 开始时间是当前时间减去10年，结束时间当前时间+10年,默认选中当前时间
        showSelectDatePickerView(context, selectListener, titleStr
                , calendar.get(Calendar.YEAR) - 10, calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE)
                , calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE)
                , calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
    }

    /**
     * 开始时间10年前 ，结束时间当天时间 默认选中当前时间
     *
     * @param context
     * @param titleStr
     * @param selectListener
     */
    public static void showSelectDatePickerViewBefore(Context context, String titleStr
            , DateSelectListener selectListener) {
        Calendar calendar = Calendar.getInstance();
        // 开始时间是当前时间减去10年，
        showSelectDatePickerView(context, selectListener, titleStr
                , calendar.get(Calendar.YEAR) - 10, calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE)
                , calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE)
                , calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
    }

    public static void showSelectDatePickerViewBefore(Context context, String titleStr, String selDateStr
            , DateSelectListener selectListener) {
        Calendar calendar = Calendar.getInstance();
        Calendar selCalendar = null;
        if (!StrUtil.isEmpty(selDateStr)) {
            Date selDate = DateUtil.getDateByFormat(selDateStr, "yyyyMMdd");
            selCalendar = Calendar.getInstance();
            selCalendar.setTime(selDate);
        } else {
            selCalendar = calendar;
        }
        // 开始时间是当前时间减去10年，结束时间当前时间+10年,默认选中当前时间
        showSelectDatePickerView(context, selectListener, titleStr
                , calendar.get(Calendar.YEAR) - 10, calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE)
                , calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE)
                , selCalendar.get(Calendar.YEAR), selCalendar.get(Calendar.MONTH), selCalendar.get(Calendar.DATE));
    }

    public static void showSelectDatePickerViewBefore(Context context, String titleStr
            , String startDateStr, String selDateStr, DateSelectListener selectListener) {

        Calendar calendar = Calendar.getInstance();
        // 开始时间是当前时间减去10年，结束时间当前时间+10年,默认选中当前时间
        Date startDate = DateUtil.getDateByFormat(startDateStr, "yyyyMMdd");
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(startDate);
        Calendar selCalendar = null;
        if (!StrUtil.isEmpty(selDateStr)) {
            Date selDate = DateUtil.getDateByFormat(selDateStr, "yyyyMMdd");
            selCalendar = Calendar.getInstance();
            selCalendar.setTime(selDate);
        } else {
            selCalendar = calendar;
        }

        showSelectDatePickerView(context, selectListener, titleStr
                , startCalendar.get(Calendar.YEAR), startCalendar.get(Calendar.MONTH), startCalendar.get(Calendar.DATE)
                , calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE)
                , selCalendar.get(Calendar.YEAR), selCalendar.get(Calendar.MONTH), selCalendar.get(Calendar.DATE));
    }


    public static void showSelectDatePickerView(Context context, String titleStr
            , DateSelectListener selectListener, Date startDate, Date endDate, Date selectedDate) {
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(startDate);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(endDate);
        Calendar selectedCalendar = Calendar.getInstance();
        selectedCalendar.setTime(selectedDate);

        showSelectDatePickerView(context, selectListener, titleStr
                , startCalendar.get(Calendar.YEAR), startCalendar.get(Calendar.MONTH), startCalendar.get(Calendar.DATE)
                , endCalendar.get(Calendar.YEAR), endCalendar.get(Calendar.MONTH), endCalendar.get(Calendar.DATE)
                , selectedCalendar.get(Calendar.YEAR), selectedCalendar.get(Calendar.MONTH), selectedCalendar.get(Calendar.DATE));
    }


    public static void showSelectDatePickerView(Context context, DateSelectListener selectListener, String titleStr
            , int startYear, int startMonth, int startDay
            , int endYear, int endMonth, int endDay
            , int selectedYear, int selectedMonth, int selectedDay) {
        Calendar selectedCalendar = Calendar.getInstance();
        selectedCalendar.set(selectedYear, selectedMonth, selectedDay);
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.set(startYear, startMonth, startDay);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.set(endYear, endMonth, endDay);
        new TimePickerView.Builder(context, (date, v) -> {//选中事件回调
            // 这里回调过来的v,就是show()方法里面所添加的 View 参数，如果show的时候没有添加参数，v则为null
            selectListener.result(DateUtil.getStringByFormat(date, "yyyy-MM-dd"));
            /*btn_Time.setText(getTime(date));*/
            LogUtil.d("PickerViewUtil", DateUtil.getStringByFormat(date, DateUtil.dateFormatYMD));
        })
                .setType(TimePickerView.Type.YEAR_MONTH_DAY)
                .setLabel("年", "月", "日", "", "", "") //设置空字符串以隐藏单位提示   hide label
                .setTitleText(titleStr)
                .setContentSize(16)
                .isDialog(true)
                .setTextColorCenter(context.getResources().getColor(R.color.pickerview_timebtn_nor))
                .setDividerColor(context.getResources().getColor(R.color.pickerview_timebtn_nor))
                .setDate(selectedCalendar)
                .setRangDate(startCalendar, endCalendar)
                .build().show();
    }


    public static void showSelectTime(Context context, DateSelectListener selectListener, String titleStr) {
        new TimePickerView.Builder(context, (date, v) -> {//选中事件回调
            // 这里回调过来的v,就是show()方法里面所添加的 View 参数，如果show的时候没有添加参数，v则为null
            selectListener.result(DateUtil.getStringByFormat(date, "HH:mm"));
            /*btn_Time.setText(getTime(date));*/
            LogUtil.d("PickerViewUtil", DateUtil.getStringByFormat(date, DateUtil.dateFormatYMD));
        })
                .setType(TimePickerView.Type.HOURS_MINS)
                .setLabel("年", "月", "日", "时   ", "分   ", "   ") //设置空字符串以隐藏单位提示   hide label
                .setTitleText(titleStr)
                .setContentSize(16)
                .isDialog(true)
                .setTextColorCenter(context.getResources().getColor(R.color.pickerview_timebtn_nor))
                .setDividerColor(context.getResources().getColor(R.color.pickerview_timebtn_nor))
                .setTitleBgColor(context.getResources().getColor(R.color.pickerview_timebtn_nor))
                .setTitleColor(Color.WHITE)
                .isCyclic(true)
                .build().show();
    }

    public interface DateSelectListener {
        void result(String dateStr);
    }

    public interface SelectResultListener<T> {
        void result(T t);
    }
}
