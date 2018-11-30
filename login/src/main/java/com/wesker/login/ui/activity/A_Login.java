package com.wesker.login.ui.activity;

import android.os.Bundle;

import com.global.bean.AppUpdate;
import com.global.ui.activity.TitleActivity;
import com.waterbase.utile.ToastUtil;
import com.wesker.login.R;
import com.wesker.login.databinding.ALoginBinding;
import com.wesker.login.ui.mvpView.ALoginView;
import com.wesker.login.ui.presenter.ALoginPresenter;

public class A_Login extends TitleActivity implements ALoginView {

    private ALoginPresenter mPresenter;
    private ALoginBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = setView(R.layout.a_login);
        initTitle();
        initView();
        initData();
        initListener();
    }

    private void initListener() {

    }

    private void initData() {
        mPresenter = new ALoginPresenter(this, this, this);
        mBinding.setClick(v -> mPresenter.click(v,mBinding.etAccount.getText().toString(),mBinding.etPassword.getText().toString(),1));
    }

    private void initView() {

    }

    private void initTitle() {
        setTitleText("车位共享");
        setLeftOneImagePic(R.mipmap.ic_back);
    }

    @Override
    public void loginSuccess(Object response) {
        ToastUtil.showToast(mContext,"click--->" + response.toString());
        mBinding.tvObject.setText(response.toString());
    }

    @Override
    public void getApkUrlSuccess(AppUpdate appUpdate) {
        mPresenter.startDownload(appUpdate,appUpdate.getAndroidDownloadUrl());
    }
}



