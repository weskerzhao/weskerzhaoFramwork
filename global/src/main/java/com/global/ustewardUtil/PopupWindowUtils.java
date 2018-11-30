package com.global.ustewardUtil;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v7.content.res.AppCompatResources;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.global.R;
import com.waterbase.ui.BaseApplication;


/**
 * PopupWindow 弹框
 * <p>
 * Created by Guangkuo on 2018/2/9.
 */
public class PopupWindowUtils {
    /**
     * 显示文本提示框
     *
     * @param activity 上下文
     * @param content  内容
     */
    public static void showTextPopupWindow(@NonNull Activity activity, View parent, String content) {
        if (TextUtils.isEmpty(content)) {
            return;
        }
        View popup_text = activity.getLayoutInflater().inflate(R.layout.popup_text, null);
        PopupWindow popupWindow = new PopupWindow(popup_text, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        // 设置PopupWindow可点击
        popupWindow.setTouchable(true);
        // 设置允许在外点击消失
        popupWindow.setOutsideTouchable(true);
//        popupWindow.setBackgroundDrawable(new ColorDrawable(ResUtils.getColor(R.color.half_transparent)));
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.argb(128, 0, 0, 0)));

        TextView tvContent = popup_text.findViewById(R.id.tvContent);
        tvContent.setText(content);
        popup_text.setOnClickListener(v -> {
            popupWindow.dismiss();
            setArrow((TextView) parent, R.mipmap.icon_qx_down);
        });
        setArrow((TextView) parent, R.mipmap.icon_qx_up);
        // PopupWindow的显示及位置设置
        LogUtils.i(ScreenUtils.getScreenWidth(), popupWindow.getContentView().getWidth());
        popupWindow.showAtLocation(parent, Gravity.NO_GRAVITY, 0, 0);
    }

    /**
     * 设置箭头
     *
     * @param arrowIcon 箭头图标
     */
    public static void setArrow(TextView textView, @DrawableRes int arrowIcon) {
        Drawable drawable = null;
        if (arrowIcon != 0) {
//            drawable = ResUtils.getDrawable(arrowIcon);
            drawable = AppCompatResources.getDrawable(BaseApplication.getAppContext(), arrowIcon);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        }
        textView.setCompoundDrawables(null, null, drawable, null);
    }
}
