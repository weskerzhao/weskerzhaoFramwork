package com.wesker.login.ui.presenter;

import android.os.Build;
import android.view.View;

import com.global.bean.AppUpdate;
import com.global.util.MD5Util;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.waterbase.http.HttpManager;
import com.waterbase.http.common.DefaultObserver;
import com.waterbase.ui.BaseActivity;
import com.waterbase.utile.ToastUtil;
import com.wesker.login.R;
import com.wesker.login.api.RetrofitHelper;
import com.wesker.login.ui.mvpView.ALoginView;

import java.util.HashMap;

import androidkun.com.versionupdatelibrary.entity.VersionUpdateConfig;


/**
 * 作者：Laughing on 2018/11/26 12:03
 * 邮箱：719240226@qq.com
 */
public class ALoginPresenter {
    private final HttpManager mManager;
    private ALoginView mView;
    private BaseActivity activity;
    private LifecycleProvider lifecycleProvider;
    private boolean haveInstallPermission;

    public ALoginPresenter(ALoginView mView, BaseActivity activity, LifecycleProvider lifecycleProvider) {
        this.mView = mView;
        this.activity = activity;
        this.lifecycleProvider = lifecycleProvider;
        mManager = new HttpManager(activity, lifecycleProvider);

    }

    public void click(View view, String telephone, String password, int type) {
        if (view.getId() == R.id.tv_login) {
            sendHttpLogin(telephone, password, type);
        } else if (view.getId() == R.id.tv_down_load) {
            getApkUrl();
        }
    }

    public void startDownload(AppUpdate appUpdate, String apkUrl) {
        //版本判断
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //获取是否有安装未知来源应用的权限
            haveInstallPermission = activity.getPackageManager().canRequestPackageInstalls();
        }

        ToastUtil.showToast(activity, "正在后台下载app，稍后请您安装...");
        VersionUpdateConfig.getInstance()//获取配置实例
                .setContext(activity)//设置上下文
                .setDownLoadURL(apkUrl)//设置文件下载链接
                .setNewVersion(appUpdate.getAndroidVersion())//设置即将下载的APK的版本号,避免重复下载
                //.setFileSavePath(savePath)//设置文件保存路径（可不设置）
                .setNotificationIconRes(R.mipmap.ic_logo)//设置通知图标
                .setNotificationSmallIconRes(R.mipmap.ic_logo)//设置通知小图标
                .setNotificationTitle("悠车位App下载")//设置通知标题
                .startDownLoad();//开始下载

    }

    public void sendHttpLogin(final String telephone, final String password, final int type) {
        HashMap<String, Object> map = new HashMap<>();
        if (type == 1) {
            //密码 登录
            map.put("telephone", telephone);
            map.put("password", MD5Util.MD5(password));
            map.put("action", "1");
            mManager.doHttpDeal(RetrofitHelper.getApiService().login(map)
                    , new DefaultObserver<Object>(activity) {
                        @Override
                        public void onSuccess(Object response) {
                            mView.loginSuccess(response);
                        }
                    });
        }
    }

    /**
     * 获取apk 下载信息
     */
    public void getApkUrl() {
        HashMap<String, Object> map = new HashMap<>();
        mManager.doHttpDeal(RetrofitHelper.getApiService().getApkUrl(map)
                , new DefaultObserver<AppUpdate>(activity,"1") {
                    @Override
                    public void onSuccess(AppUpdate response) {
                        mView.getApkUrlSuccess(response);
                    }
                });
    }



}
