package com.wesker.weskerdemo.ui.activity;

import android.os.Bundle;

import com.global.ui.activity.TitleActivity;
import com.waterbase.utile.ToastUtil;
import com.wesker.weskerdemo.R;
import com.wesker.weskerdemo.databinding.ActivityMainBinding;
import com.wesker.weskerdemo.ui.mvpView.MainView;
import com.wesker.weskerdemo.ui.presenter.MainPresenter;

public class MainActivity extends TitleActivity implements MainView {

    private MainPresenter mPresenter;
    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = setView(R.layout.activity_main);
        initTitle();
        initView();
        initData();
        initListener();

    }

    private void initListener() {
        mBinding.setClick(v -> mPresenter.click(v));
    }

    private void initData() {
        mPresenter = new MainPresenter(this, this, this);

    }

    private void initView() {

    }

    private void initTitle() {
        setTitleText("主页");
        setLeftOneImagePic(com.wesker.login.R.mipmap.ic_back);

    }

    @Override
    public void queryParkingLotByCityNameSuccess(Object response) {
        ToastUtil.showToast(mContext,"click--->" + response.toString());
    }
}



