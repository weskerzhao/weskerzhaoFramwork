package com.global.util;

import com.waterbase.ui.BaseApplication;
import com.waterbase.utile.PreferencesManager;
import com.waterbase.utile.StrUtil;

/**
 * 作者：Laughing on 2018/5/27 14:21
 * 邮箱：719240226@qq.com
 */

public class UserIdUtil {
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
