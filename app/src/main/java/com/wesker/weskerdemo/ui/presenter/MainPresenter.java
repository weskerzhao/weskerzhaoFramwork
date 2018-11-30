package com.wesker.weskerdemo.ui.presenter;

import android.content.Intent;
import android.view.View;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.waterbase.http.HttpManager;
import com.waterbase.http.common.DefaultObserver;
import com.waterbase.ui.BaseActivity;
import com.waterbase.utile.ToastUtil;
import com.wesker.weskerdemo.R;
import com.wesker.weskerdemo.ui.api.RetrofitHelper;
import com.wesker.weskerdemo.ui.mvpView.MainView;

import java.util.HashMap;

/**
 * 作者：Laughing on 2018/11/26 10:17
 * 邮箱：719240226@qq.com
 */
public class MainPresenter {
    private MainView mView;
    private BaseActivity activity;
    private HttpManager mManager;

    public MainPresenter(MainView mView, BaseActivity activity, LifecycleProvider lifecycleProvider) {
        this.mView = mView;
        this.activity = activity;
        mManager = new HttpManager(activity, lifecycleProvider);

    }

    public void click(View view) {
        if (view.getId() == R.id.tv_request_net) {
            ToastUtil.showToast(activity, "click---> 点击成功");
            queryParkingLotByCityName();
        } else if (view.getId() == R.id.tv_login){
            try {
                Intent intent = new Intent(activity,Class.forName("com.wesker.login.ui.activity.A_Login"));
                activity.startActivity(intent);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void queryParkingLotByCityName() {
        HashMap map = new HashMap();
        map.put("cityName", "西安市");
        map.put("lat", 34.267701);
        map.put("lon", 108.920558);
        map.put("pageSize", 10);
        map.put("pageNumber", 1);
        mManager.doHttpDeal(RetrofitHelper.getApiService().queryParkingLotByCityName(map)
                , new DefaultObserver<Object>(activity) {
                    @Override
                    public void onSuccess(Object response) {
                        mView.queryParkingLotByCityNameSuccess(response);
                    }
                });
    }
}


