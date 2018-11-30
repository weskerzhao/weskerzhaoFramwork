package com.wesker.login.ui.mvpView;

import com.global.bean.AppUpdate; /**
 * 作者：Laughing on 2018/11/26 12:04
 * 邮箱：719240226@qq.com
 */
public interface ALoginView {
    void loginSuccess(Object response);

    void getApkUrlSuccess(AppUpdate response);
}
