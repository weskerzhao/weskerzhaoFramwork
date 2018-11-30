package com.waterbase.http.common;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.google.gson.JsonParseException;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.waterbase.R;
import com.waterbase.global.AppConfig;
import com.waterbase.http.exception.ServerResponseException;
import com.waterbase.ui.BaseActivity;
import com.waterbase.utile.LogUtil;
import com.waterbase.utile.PreferencesManager;
import com.waterbase.utile.ToastUtil;
import com.waterbase.utile.Utils;
import com.waterbase.widget.PopupDialog;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.text.ParseException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by zhpan on 2017/4/18.
 */
public abstract class DefaultObserver<T> implements Observer<T> {

    private BaseActivity mActivity;
    private Context context;
    //-1:显示登录提示框 1:不显示登录提示框 2:直接跳入登录页面
    private String dialog = "-1";

//    public DefaultObserver() {
//
//    }

    public DefaultObserver(BaseActivity activity) {

        this.mActivity = activity;
    }

    public DefaultObserver(BaseActivity activity, String dialog) {
        this.mActivity = activity;
        this.dialog = dialog;
    }

//    public DefaultObserver(Context context) {
//        this.context = context;
//    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T response) {
        onSuccess(response);
    }

    /**
     * 对于错误框架统一进行处理
     *
     * @param e
     */
    @Override
    public void onError(Throwable e) {
        if (e != null) {
            LogUtil.e("Retrofit", "----------DefaultObserver---onError------->" + e.getMessage());
            if (e instanceof HttpException) {     //   HTTP错误
                onException(ExceptionReason.BAD_NETWORK);
            } else if (e instanceof ConnectException
                    || e instanceof UnknownHostException) {   //   连接错误
                onException(ExceptionReason.CONNECT_ERROR);
            } else if (e instanceof InterruptedIOException) {   //  连接超时
                onException(ExceptionReason.CONNECT_TIMEOUT);
            } else if (e instanceof JsonParseException
                    || e instanceof JSONException
                    || e instanceof ParseException) {   //  解析错误
                onException(ExceptionReason.PARSE_ERROR);
            } else if (e instanceof ServerResponseException) {
                onFail(Integer.parseInt(e.getCause().getMessage()), e.getMessage());//网络访问失败

            } else {
                onException(ExceptionReason.UNKNOWN_ERROR);
            }
        }

    }

    @Override
    public void onComplete() {
    }

    /**
     * 请求成功
     *
     * @param response 服务器返回的数据
     */
    abstract public void onSuccess(T response);

    /**
     * 服务器返回数据，但响应码不为0
     *
     */
    /**
     * 服务器返回数据，但响应码不为1000
     */
    public void onFail(int errorCode, String cause) {
        LogUtil.e("Retrofit", "---DefaultObserver---onFail---------errorCode=>" + errorCode + "  cause=" + cause);
        //token 过期重新登录 与 token 为空
        if (errorCode == ErrorCode.TOKEN_PAST || errorCode == ErrorCode.RE_LOGIN) {

//            WindowUtils.showPopupWindow(mActivity);//自定义弹出框
            PreferencesManager.getInstance(mActivity).remove(AppConfig.PREF_USER_TOKEN);
            PopupDialog.getInstance().showNotLoginOrSomeBodyLoginPopupWindow(mActivity, 0, "提示",
                    "您的账号在其他设备上登录，请重新登录!",
                    "", "确认", null, (View v) -> {
                        goLoginActivity();
                        PopupDialog.getInstance().pop.dismiss();
                        PopupDialog.getInstance().rlPopup.clearAnimation();
                    });


        } else if ((errorCode == 1 && cause.equals("token不能为空"))) {
            //打印消息
            if ("-1".equals(dialog)) {
                PopupDialog.getInstance().showNotLoginOrSomeBodyLoginPopupWindow(mActivity, 0, "提示",
                        "您还未登录，请您先登录!",
                        "", "确认", null, (View v) -> {
                            goLoginActivity();
                            PopupDialog.getInstance().pop.dismiss();
                            PopupDialog.getInstance().rlPopup.clearAnimation();
                        });

            }
            if ("2".equals(dialog)) {
                goLoginActivity2();
            }
        } else if (cause.equals("token不能为空") || cause.equals("token失效!"))

        {
            //不打印消息
        } else

        {
            //打印错误消息
            ToastUtil.showLongToast(Utils.getContext(), cause);
        }
    }

    /**
     * 跳转到登录页面
     */
    private void goLoginActivity() {
        try {
            Intent intent = new Intent(context, Class.forName("com.wxkj.login.ui.activity.A_Login"));
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.putExtra("isRefresh", true);
//            ToastUtil.showLongToast(context, "您的账号在其他设备上登录，请重新登录!");
            context.startActivity(intent);

            return;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //不弹出版本框
    private void goLoginActivity2() {
        try {
            Intent intent = new Intent(context, Class.forName("com.wxkj.login.ui.activity.A_Login"));
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("refreshAPK", true);
//            ToastUtil.showLongToast(context, "您的账号在其他设备上登录，请重新登录!");
            context.startActivity(intent);

            return;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * 请求异常
     *
     * @param reason
     */
    public void onException(ExceptionReason reason) {
        switch (reason) {
            case CONNECT_ERROR:
                ToastUtil.showToast(Utils.getContext(), R.string.connect_error);

                break;

            case CONNECT_TIMEOUT:
                ToastUtil.showToast(Utils.getContext(), R.string.connect_timeout);

                break;

            case BAD_NETWORK:
                ToastUtil.showToast(Utils.getContext(), R.string.bad_network);

                break;

            case PARSE_ERROR:
                ToastUtil.showToast(Utils.getContext(), R.string.parse_error);

                break;

            case UNKNOWN_ERROR:
            default:
                ToastUtil.showToast(Utils.getContext(), R.string.unknown_error);

                break;
        }
    }

    /**
     * 请求网络失败原因
     */
    public enum ExceptionReason {
        /**
         * 解析数据失败
         */
        PARSE_ERROR,
        /**
         * 网络问题
         */
        BAD_NETWORK,
        /**
         * 连接错误
         */
        CONNECT_ERROR,
        /**
         * 连接超时
         */
        CONNECT_TIMEOUT,
        /**
         * 未知错误
         */
        UNKNOWN_ERROR,
    }
}

