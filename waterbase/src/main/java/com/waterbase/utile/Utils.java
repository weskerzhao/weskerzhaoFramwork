package com.waterbase.utile;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.WindowManager;
import android.widget.EditText;

/**
 * 这里初始化一个ApplicationContext
 */
public class Utils {

    private static Context context;

    private Utils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(Context context) {
        Utils.context = context.getApplicationContext();
    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        if (context != null) return context;
        throw new NullPointerException("you should init first :  Utils-->context");
    }


    /**
     * EditText获取焦点并显示软键盘
     */
    public static void showSoftInputFromWindow(Activity activity, EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }

    /**
     * 获取当前版本信息
     */
    public static String getAppVersionName(Context context) {
        String versionName = "";
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            Log.e("VersionInfo", e.toString());

        }
        LogUtil.e("TAG", "getAppVersionName: " + versionName);
        return versionName;
    }

    /**
     * 判断包名是否存在
     *
     * @param context
     * @param packageName 高德：com.autonavi.minimap
     *                    百度：com.baidu.BaiduMap
     *                    微信com.tentcent.mm
     *                    QQcom.tencent.mobileqq
     */
    public static boolean checkApkExist(Context context, String packageName) {
        if (packageName == null || "".equals(packageName))
            return false;
        try {
            ApplicationInfo info = context.getPackageManager().getApplicationInfo(packageName,
                    PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    public static int getAppVersionCode(Context context) {
        int versionCode = 0;
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionCode = pi.versionCode;
            if (versionCode == 0) {
                return 0;
            }
        } catch (Exception e) {
            Log.e("VersionInfo", e.toString());

        }
        LogUtil.e("TAG", "getAppVersionCode: " + versionCode);

        return versionCode;
    }

}