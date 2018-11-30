package com.waterbase.utile;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 * 获取屏幕信息工具类
 */
public class UiUtil {

    /**
     * 获得状态栏高度
     *
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
//        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
//        if (resourceId > 0) {
//            result = context.getResources().getDimensionPixelSize(resourceId);
//        }

        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            result = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取屏幕宽度
     *
     * @param activity
     * @return 单位为px
     */
    public static int getScreenWidth(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    /**
     * 获取屏幕宽度
     *
     * @param context
     * @return 单位为px
     */
    public static int getScreenWidth(Context context) {
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        float density = dm.density;
        int width = dm.widthPixels;
        return dm.widthPixels;
    }

    /**
     * 将设计宽度转化为世纪宽度
     *
     * @param activity
     * @param px
     * @return
     */
    public static int getWidthByPx(Activity activity, int px) {
        int realWidth = getScreenWidth(activity);//手机实际宽度
        int designWidth = 720;
        return realWidth / designWidth * px;
    }

    /**
     * 获取屏幕密度
     *
     * @param activity
     * @return
     */
    public static float getScreenDensity(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.density;
    }

    /**
     * 获取屏幕密度DPI
     *
     * @param activity
     * @return
     */
    public static float getScreenDensityDpi(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.densityDpi;
    }

    /**
     * 获取屏幕高度
     *
     * @param activity
     * @return
     */
    public static int getScreenHeight(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    public static int getScreenHeight(Context context) {
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        float density = dm.density;
        int width = dm.widthPixels;
        return dm.heightPixels;
    }
}
