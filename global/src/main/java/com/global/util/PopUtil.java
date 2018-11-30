package com.global.util;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.view.View;
import android.widget.RelativeLayout;

import com.global.R;
import com.global.bean.Label;
import com.global.databinding.PopSeldateBinding;
import com.global.viewmodel.MyCenterSexViewModel;
import com.waterbase.utile.PopupWindowUtile;
import com.waterbase.utile.ToastUtil;
import com.waterbase.widget.pickerview.view.WheelOptions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Water on 2018/5/14.
 */

public class PopUtil {


    public static void selDate(Activity activity, View pv, View bg, SelDateListener dateListener) {
        List<Label> yearList = initYear();
        List<Label> monthList = initMonth();

        View view = PopupWindowUtile.showAdaptive3(activity, pv, bg, R.layout.pop_seldate
                , PopupWindowUtile.BOTTOM, false, true);
        // ----滚轮布局
        PopSeldateBinding binding = DataBindingUtil.bind(view);
        WheelOptions wheelOptions = new WheelOptions(binding.optionspicker, false);
        wheelOptions.setTextContentSize(16);
        wheelOptions.setCyclic(true, true, true);
        wheelOptions.setDividerColor(activity.getResources().getColor(R.color.main_color));

        wheelOptions.setNPicker(yearList, monthList, null);

        binding.setClick(v -> {
            if (v.getId() == R.id.btnSubmit) {
                int[] res = wheelOptions.getCurrentItems();
                PopupWindowUtile.closePopupWindow();
                dateListener.result(yearList.get(res[0]), monthList.get(res[1]));
            } else if (v.getId() == R.id.btnCancel) {
                PopupWindowUtile.closePopupWindow();
                dateListener.cancel();
            }
        });
    }


    private static List<Label> initYear() {
        List<Label> labelList = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < 20; i++) {
            labelList.add(new Label(calendar.get(Calendar.YEAR) - 10 + i, calendar.get(Calendar.YEAR) - 10 + i + "年"));
        }
        return labelList;
    }

    private static List<Label> initMonth() {
        List<Label> labelList = new ArrayList<>();
        labelList.add(new Label(0, "全部"));
        labelList.add(new Label(1, "1月"));
        labelList.add(new Label(2, "2月"));
        labelList.add(new Label(3, "3月"));
        labelList.add(new Label(4, "4月"));
        labelList.add(new Label(5, "5月"));
        labelList.add(new Label(6, "6月"));
        labelList.add(new Label(7, "7月"));
        labelList.add(new Label(8, "8月"));
        labelList.add(new Label(9, "9月"));
        labelList.add(new Label(10, "10月"));
        labelList.add(new Label(11, "11月"));
        labelList.add(new Label(12, "12月"));
        return labelList;
    }


    public interface SelDateListener {
        void result(Label year, Label month);
        void cancel();
    }
}
