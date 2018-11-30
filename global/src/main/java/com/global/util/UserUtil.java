package com.global.util;

import com.waterbase.global.AppConfig;
import com.waterbase.ui.BaseApplication;
import com.waterbase.utile.PreferencesManager;
import com.waterbase.utile.StrUtil;

/**
 * 作者：Laughing on 2018/5/27 14:21
 * 邮箱：719240226@qq.com
 */

public class UserUtil {

    /**
     * 获取 userId
     *
     * @return
     */
    public static boolean userIsLogin() {
        //框架拦截未登录或token过期对话框
        return PreferencesManager.getInstance(BaseApplication.getAppContext()).get("isFlag", false);

    }

    /**
     * 获取 userId
     *
     * @return
     */
    public static String getUserId() {

        return PreferencesManager.getInstance(BaseApplication.getAppContext()).get("userId", "");

    }

    /**
     * 获取 userTelephone
     *
     * @return
     */
    public static String getUserTelephone() {

        return PreferencesManager.getInstance(BaseApplication.getAppContext()).get("mobile", "");

    }

    /**
     * 获取 userToken
     *
     * @return
     */
    public static String getUserToken() {
        String token = PreferencesManager.getInstance(BaseApplication.getAppContext())
                .get(AppConfig.PREF_USER_TOKEN, "");
        return token;
    }

    /**
     * 获取 停车场ID
     *
     * @return
     */
    public static String getParkingLotId() {
        String parkingLotId = PreferencesManager.getInstance(BaseApplication.getAppContext())
                .get(AppConfig.PREF_PARIKING_LOT_ID, "");
        return parkingLotId;
    }

    public static String getUserName() {
        return PreferencesManager.getInstance(BaseApplication.getAppContext())
                .get(AppConfig.PREF_USER_NAME, "");
    }

    /**
     * 获取 用户名权限
     */
    public static String getUserAuthority() {

        return PreferencesManager.getInstance(BaseApplication.getAppContext()).get(AppConfig.PREF_USER_AUTHORITY, "");
    }

    /**
     * 设置 用户头像
     *
     * @param url
     * @return
     */
    public static void setUserHeadImageUrl(String url) {
        PreferencesManager.getInstance(BaseApplication.getAppContext()).put("headPic", url);

    }

    /**
     * 获取 用户头像
     *
     * @return
     */
    public static String getUserHeadImageUrl() {
        return PreferencesManager.getInstance(BaseApplication.getAppContext()).get("headPic", "");

    }

    /**
     * 获取 用户NickName
     *
     * @return
     */
    public static String getUserNickName() {
        return PreferencesManager.getInstance(BaseApplication.getAppContext()).get("nickName", "");

    }

    /**
     * 获取 用户IdNumber
     *
     * @return
     */
    public static String getUserIdNumber() {
        return PreferencesManager.getInstance(BaseApplication.getAppContext()).get("idNumber", "");

    }


//    /**
//     * 获取 用户性别
//     */
//    public static String getUserSex() {
//        String sex = PreferencesManager.getInstance(BaseApplication.getAppContext()).get("sex", "");
//        return TransformUtil.sexTransform(sex);
//    }


//    /**
//     * 获取 用户邀请码
//     */
//    public static String getInviteNumber() {
//        String sex = PreferencesManager.getInstance(BaseApplication.getAppContext()).get("inviteNumber", "");
//        return TransformUtil.sexTransform(sex);
//    }

    /**
     * 获取 是否第一次预约停车
     *
     * @return
     */
    public static boolean isFirstOrder() {
        return PreferencesManager.getInstance(BaseApplication.getAppContext()).get("isFirstOrder", false);

    }

    /**
     * 获取 实名认证状态
     *
     * @return
     */
    public static String identifyStatus() {
        return PreferencesManager.getInstance(BaseApplication.getAppContext()).get("identifyStatus", "");

    }

    /**
     * 用户所在城市（没有市字）
     *
     * @return
     */
    public static String getLocationCity() {
        return PreferencesManager.getInstance(BaseApplication.getAppContext()).get("locationCity", "");

    }


    /**
     * 获取一个long 类型的userId
     *
     * @return
     */
    public static Long getUserIdLong() {
        PreferencesManager preferencesManager = PreferencesManager.getInstance(BaseApplication.getAppContext());
        // 获取userId
        String mUserId = preferencesManager.get("userId", "");
        if (!StrUtil.isEmpty(mUserId)) {
            long mUserIdLong = Long.parseLong(mUserId);
            return mUserIdLong;
        } else {
            return 3L;

        }

    }


}
