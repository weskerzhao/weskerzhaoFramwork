package com.waterbase.utile;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

/**
 * 资源助手（工具类）
 * Author: nanchen
 * Email: liushilin520@foxmail.com
 * Date: 2017-04-25  14:03
 */

public class ResourceHelper {

    public static Resources getResource() {
        return Utils.getContext().getResources();
    }

    public static String getString(int resId) {
        return getResource().getString(resId);
    }

    public static Drawable getDrawable(int resId) {
        return getResource().getDrawable(resId);
    }

    public static int getColor(int resId) {
        return getResource().getColor(resId);
    }

    public static float getDimens(int resId) {
        return getResource().getDimension(resId);
    }
}
