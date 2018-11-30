package com.waterbase.widget;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.DrawableRes;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.waterbase.R;
import com.waterbase.global.AppConfig;
import com.waterbase.ui.BaseApplication;
import com.waterbase.utile.ResourceHelper;
import com.waterbase.utile.ToastUtil;

import java.io.File;
import java.util.List;


/**
 * 弹出提示对话框
 * Created by Guangkuo on 2017/4/25.
 */
public class PopupDialog {
    private static PopupDialog popupDialog;
    public PopupWindow pop;
    private LinearLayout ll_popup;// 选择图片提示框
    public ListView lsvPopup;// 列表提示框
    public RelativeLayout rlPopup;// 普通提示框
    //    public RelativeLayout rlPayPrompt;// 未付款提醒
    public RelativeLayout rlCarSelect;// 车辆选择弹框
    public LinearLayout rel_select;
    //    public WheelMain wheelMainDate;

    public static final int PHOTO_REQUEST_CAMERA = 1;// 拍照
    public static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    public static final int PHOTO_REQUEST_CUT = 3;// 结果
    public static final String PHOTO_FILE_NAME = AppConfig.headName;
    public CustomNumberPicker carWheel;

    /**
     * 单例
     */
    public static PopupDialog getInstance() {
        if (popupDialog == null) {
            synchronized (PopupDialog.class) {
                if (popupDialog == null) {
                    popupDialog = new PopupDialog();
                }
            }
        }
        return popupDialog;
    }

    /**
     * 显示列表选择框
     *
     * @param activity
     * @param list
     * @param onItemClickListener
     */
    public void showPopupWindow(final Activity activity, List<String> list,
                                AdapterView.OnItemClickListener onItemClickListener) {
        // pop 正在显示
        if (pop != null && pop.isShowing()) {
            pop.dismiss();
        }

//        closeKeyboard(activity);// 关闭软键盘
        pop = new PopupWindow(activity);
        View view = activity.getLayoutInflater().inflate(R.layout.popup_windows, null);
        lsvPopup = (ListView) view.findViewById(R.id.lsvPopup);
        lsvPopup.setVisibility(View.VISIBLE);
        pop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        pop.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setFocusable(true);
        pop.setOutsideTouchable(true);
        pop.setContentView(view);
        RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent);
        parent.setOnClickListener(new CancelOnClickListener(lsvPopup));
        lsvPopup.setAdapter(new ArrayAdapter<>(activity, R.layout.item_pop_list, list));
        lsvPopup.setOnItemClickListener(onItemClickListener);
        lsvPopup.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.activity_translate_in));
        pop.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }

    /**
     * 选择头像提示框
     *
     * @param activity
     */
    public void showPopupWindow(Activity activity) {
        // pop 正在显示
        if (pop != null && pop.isShowing()) {
            pop.dismiss();
        }
//        closeKeyboard(activity);// 关闭软键盘
        pop = new PopupWindow(activity);
        View view = activity.getLayoutInflater().inflate(R.layout.popup_windows, null);
        ll_popup = (LinearLayout) view.findViewById(R.id.ll_popup);
        ll_popup.setVisibility(View.VISIBLE);
        pop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        pop.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setFocusable(true);
        pop.setOutsideTouchable(true);
        pop.setContentView(view);
        RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent);
        Button bt1 = (Button) view.findViewById(R.id.item_popupwindows_camera);
        Button bt2 = (Button) view.findViewById(R.id.item_popupwindows_Photo);
        Button bt3 = (Button) view.findViewById(R.id.item_popupwindows_cancel);
        parent.setOnClickListener(new CancelOnClickListener(ll_popup));
        // 从相机获取
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (hasSdcard()) {// 判断存储卡是否可以用，可用进行存储
                    File file = new File(Environment.getExternalStorageDirectory(), PHOTO_FILE_NAME);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                }
                activity.startActivityForResult(intent, PHOTO_REQUEST_CAMERA);
                pop.dismiss();
                ll_popup.clearAnimation();


            }
        });


        // 从相册获取
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                activity.startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        bt3.setOnClickListener(new CancelOnClickListener(ll_popup));

        ll_popup.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.activity_translate_in));
        pop.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }


    /**
     * 用户提示框
     *
     * @param activity
     * @param title
     * @param content
     * @param btnLeftListener
     * @param btnRightListener
     */
    public void showPopupWindow(Activity activity, @DrawableRes int image, CharSequence title,
                                CharSequence content, CharSequence leftBtn, CharSequence rightBtn,
                                View.OnClickListener btnLeftListener, View.OnClickListener btnRightListener) {
        // pop 正在显示
        if (pop != null && pop.isShowing()) {
            pop.dismiss();
        }

//        closeKeyboard(activity);// 关闭软键盘
        pop = new PopupWindow(activity);
        View view = activity.getLayoutInflater().inflate(R.layout.popup_windows_updata, null);
        rlPopup = (RelativeLayout) view.findViewById(R.id.rl_prompt);
        rlPopup.setVisibility(View.VISIBLE);
        pop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        pop.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        pop.setBackgroundDrawable(null);
        pop.setFocusable(true);
        pop.setOutsideTouchable(true);
        pop.setContentView(view);
        // RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent);
        ImageView mIvIcon = (ImageView) view.findViewById(R.id.iv_icon);
        TextView mTvTitle = (TextView) view.findViewById(R.id.tv_title);
        TextView mTvContent = (TextView) view.findViewById(R.id.tv_content);
        Button btnLeft = (Button) view.findViewById(R.id.btnLeft);
        Button btnRight = (Button) view.findViewById(R.id.btnRight);
        if (image == 0) {
            mIvIcon.setVisibility(View.GONE);
        } else {
            mIvIcon.setImageResource(image);
        }
        if (TextUtils.isEmpty(title)) {
            mTvTitle.setVisibility(View.GONE);
            view.findViewById(R.id.line).setVisibility(View.INVISIBLE);
        } else {
            mTvTitle.setText(title);
        }
        mTvContent.setText(content);
        //        mTvContent.setGravity(contentGravity);
        mTvContent.setMovementMethod(LinkMovementMethod.getInstance());
        btnLeft.setText(leftBtn);
        if (btnLeftListener == null) {
            btnLeft.setOnClickListener(new CancelOnClickListener(rlPopup));
        } else {
            btnLeft.setOnClickListener(btnLeftListener);
        }

        if (TextUtils.isEmpty(rightBtn)) {
            btnRight.setVisibility(View.GONE);
            btnLeft.setBackgroundResource(R.drawable.shape_button_orange_popup);
            btnLeft.setTextColor(ResourceHelper.getColor(R.color.white));
        } else {
            btnRight.setText(rightBtn);
            if (btnRightListener == null) {
                btnRight.setOnClickListener(new CancelOnClickListener(rlPopup));
            } else {
                btnRight.setOnClickListener(btnRightListener);
            }
        }

        if (TextUtils.isEmpty(leftBtn) && null == btnLeftListener) {
            btnLeft.setVisibility(View.GONE);
            btnRight.setBackgroundResource(R.drawable.shape_button_orange_popup);
        }
        // parent.setOnClickListener(new CancelOnClickListener(rlPopup));
        rlPopup.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.activity_translate_in));
        pop.showAtLocation(view, Gravity.CENTER, 0, 0);
    }

    public void showNotLoginOrSomeBodyLoginPopupWindow(Activity activity, @DrawableRes int image, CharSequence title,
                                                       CharSequence content, CharSequence leftBtn, CharSequence rightBtn,
                                                       View.OnClickListener btnLeftListener, View.OnClickListener btnRightListener) {
        // pop 正在显示
        if (pop != null && pop.isShowing()) {
            pop.dismiss();
        }

//        closeKeyboard(activity);// 关闭软键盘
        pop = new PopupWindow(activity);
        View view = activity.getLayoutInflater().inflate(R.layout.popup_windows_not_login_or_somebody_login, null);
        rlPopup = (RelativeLayout) view.findViewById(R.id.rl_not_login_or_somebody_login_prompt);
        rlPopup.setVisibility(View.VISIBLE);
        pop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        pop.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        pop.setBackgroundDrawable(null);
        pop.setFocusable(true);
        pop.setOutsideTouchable(true);
        pop.setContentView(view);
        // RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent);
        ImageView mIvIcon = (ImageView) view.findViewById(R.id.iv_not_login_or_somebody_login_icon);
        TextView mTvTitle = (TextView) view.findViewById(R.id.tv_not_login_or_somebody_login_title);
        TextView mTvContent = (TextView) view.findViewById(R.id.tv_not_login_or_somebody_login_content);
        Button btnLeft = (Button) view.findViewById(R.id.btnLeft_not_login_or_somebody_login);
        Button btnRight = (Button) view.findViewById(R.id.btnRight_not_login_or_somebody_login);
        if (image == 0) {
            mIvIcon.setVisibility(View.GONE);
        } else {
            mIvIcon.setImageResource(image);
        }
        if (TextUtils.isEmpty(title)) {
            mTvTitle.setVisibility(View.GONE);
            view.findViewById(R.id.line).setVisibility(View.INVISIBLE);
        } else {
            mTvTitle.setText(title);
        }
        mTvContent.setText(content);
        //        mTvContent.setGravity(contentGravity);
        mTvContent.setMovementMethod(LinkMovementMethod.getInstance());
        btnLeft.setText(leftBtn);
        if (btnLeftListener == null) {
            btnLeft.setOnClickListener(new CancelOnClickListener(rlPopup));
        } else {
            btnLeft.setOnClickListener(btnLeftListener);
        }

        if (TextUtils.isEmpty(rightBtn)) {
            btnRight.setVisibility(View.GONE);
            btnLeft.setBackgroundResource(R.drawable.shape_button_orange_popup);
            btnLeft.setTextColor(ResourceHelper.getColor(R.color.white));
        } else {
            btnRight.setText(rightBtn);
            if (btnRightListener == null) {
                btnRight.setOnClickListener(new CancelOnClickListener(rlPopup));
            } else {
                btnRight.setOnClickListener(btnRightListener);
            }
        }

        if (TextUtils.isEmpty(leftBtn) && null == btnLeftListener) {
            btnLeft.setVisibility(View.GONE);
            btnRight.setBackgroundResource(R.drawable.shape_button_orange_popup);
        }
        // parent.setOnClickListener(new CancelOnClickListener(rlPopup));
        rlPopup.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.activity_translate_in));
        pop.showAtLocation(view, Gravity.CENTER, 0, 0);
    }

    //    /**
    //     * 免费金额提示框
    //     *
    //     * @param activity
    //     * @param title
    //     * @param btnRightListener
    //     */
    //    public void showPopupWindow(final Activity activity, @DrawableRes int image, String title, View.OnClickListener btnRightListener) {
    //        closeKeyboard(activity);// 关闭软键盘
    //        pop = new PopupWindow(activity);
    //        View view = activity.getLayoutInflater().inflate(R.layout.popup_windows, null);
    //        rlPopup = (RelativeLayout) view.findViewById(R.id.rl_prompt_free);
    //        rlPopup.setVisibility(View.VISIBLE);
    //        pop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
    //        pop.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
    //        pop.setBackgroundDrawable(new BitmapDrawable());
    //        pop.setFocusable(true);
    //        pop.setOutsideTouchable(true);
    //        pop.setContentView(view);
    //        ImageView mIbCancle = (ImageView) view.findViewById(R.id.ib_free_cancle);
    //        ImageView mIvIcon = (ImageView) view.findViewById(R.id.iv_icon_free);
    //        TextView mTvTitle = (TextView) view.findViewById(R.id.tv_title_free);
    //        mIvIcon.setImageResource(image);
    //        mTvTitle.setText(title);
    //
    //        if (btnRightListener != null) {
    //            mIbCancle.setOnClickListener(new CancelOnClickListener(rlPopup));
    //        }
    //        rlPopup.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.activity_translate_in));
    //        pop.showAtLocation(view, Gravity.CENTER, 0, 0);
    //
    //    }

    /**
     * 登陆支付宝账号
     *
     * @param activity
     */
    @SuppressLint("WrongConstant")
    public void showAlipayLoginPopupWindow(final Activity activity, final AlipayLoginListener loginListener) {
        // pop 正在显示
        if (pop != null && pop.isShowing()) {
            pop.dismiss();
        }
//        closeKeyboard(activity);// 关闭软键盘

        final View mView = activity.getLayoutInflater().inflate(R.layout.popup_windows, null);
        LinearLayout mAlipay = (LinearLayout) mView.findViewById(R.id.paystyle);
        mAlipay.setVisibility(View.VISIBLE);
        final EditText alipayAccount = (EditText) mView.findViewById(R.id.et_alipay_accounts);
        final EditText alipayRealname = (EditText) mView.findViewById(R.id.et_alipay_name);
        Button loginAlipay = (Button) mView.findViewById(R.id.alipay_login);

        pop = new PopupWindow(mView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        pop.setTouchable(true);
        pop.setFocusable(true);
        pop.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);
        pop.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        pop.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        loginAlipay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = alipayAccount.getText().toString().trim();
                String realname = alipayRealname.getText().toString().trim();
                if (!TextUtils.isEmpty(account) && !TextUtils.isEmpty(realname)) {
                    //                    if (RegexUtil.match(RegexUtil.REGEX_MOBILE, account) || RegexUtil.match(RegexUtil.REGEX_EMAIL, account)) {
                    loginListener.getAlipayAccountAndRealname(account, realname);
                    Intent intent = null;
                    try {
                        intent = new Intent(activity, Class.forName("com.dgk.mycenter.ui.activity.WalletPwdActivity"));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    intent.putExtra("from_popWindow", "INPUT_PWD_FRAGMENT");
                    activity.startActivityForResult(intent, 100);
                    Log.i("哈哈哈", "onClick: " + account + "," + realname);
//                    closeKeyboard(activity);// 关闭软键盘
                    pop.dismiss();
                    mView.clearAnimation();
                    //                    } else {
                    //                        showToastLong("请输入正确的支付宝账号！");
                    //                    }
                } else {
                    ToastUtil.showToast(BaseApplication.getAppContext(), "支付宝账号和姓名不能为空！");
                }
            }
        });
        mView.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.activity_translate_in));
        pop.showAtLocation(mView, Gravity.BOTTOM, 0, 0);
    }

    /**
     * 车辆选择器
     *
     * @param activity
     */
    public void showCarBottoPopupWindow(final Activity activity, String[] carsNum, View.OnClickListener btnRightListener, NumberPicker.OnValueChangeListener changeListener) {
        // pop 正在显示
        if (pop != null && pop.isShowing()) {
            pop.dismiss();
        }

//        closeKeyboard(activity);// 关闭软键盘
        View view = activity.getLayoutInflater().inflate(R.layout.popup_windows, null);
        pop = new PopupWindow(activity);
        rlCarSelect = (RelativeLayout) view.findViewById(R.id.ll_cars_num);
        carWheel = (CustomNumberPicker) view.findViewById(R.id.ll_cars);
        rlCarSelect.setVisibility(View.VISIBLE);
        carWheel.setDisplayedValues(carsNum);
        carWheel.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        carWheel.setMinValue(0);
        carWheel.setMaxValue(carsNum.length - 1);
        carWheel.setOnValueChangedListener(changeListener);
        CustomNumberPicker.setNumberPickerDividerColor(carWheel);
        pop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        pop.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setFocusable(true);
        pop.setOutsideTouchable(true);
        pop.setContentView(view);
        Button tv_cancle = (Button) view.findViewById(R.id.bt_exit);
        Button tv_ensure = (Button) view.findViewById(R.id.bt_commit);
        tv_cancle.setOnClickListener(new CancelOnClickListener(rlCarSelect));
        tv_ensure.setOnClickListener(btnRightListener);
        rlCarSelect.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.activity_translate_in));
        pop.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }

    /**
     * 车辆选择器
     *
     * @param activity
     */
    public void showCarBottoPopupWindowTop(final Activity activity, String[] carsNum, View.OnClickListener btnRightListener, NumberPicker.OnValueChangeListener changeListener, View v) {
        // pop 正在显示
        if (pop != null && pop.isShowing()) {
            pop.dismiss();
        }

//        closeKeyboard(activity);// 关闭软键盘
        View view = activity.getLayoutInflater().inflate(R.layout.popup_windows, null);
        pop = new PopupWindow(activity);
        rlCarSelect = (RelativeLayout) view.findViewById(R.id.ll_cars_num);
        carWheel = (CustomNumberPicker) view.findViewById(R.id.ll_cars);
        rlCarSelect.setVisibility(View.VISIBLE);
        carWheel.setDisplayedValues(carsNum);
        carWheel.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        carWheel.setMinValue(0);
        carWheel.setMaxValue(carsNum.length - 1);
        carWheel.setOnValueChangedListener(changeListener);
        CustomNumberPicker.setNumberPickerDividerColor(carWheel);
        pop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        pop.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setFocusable(true);
        pop.setOutsideTouchable(true);
        pop.setContentView(view);

        Button tv_cancle = (Button) view.findViewById(R.id.bt_exit);
        Button tv_ensure = (Button) view.findViewById(R.id.bt_commit);
        tv_cancle.setOnClickListener(new CancelOnClickListener(rlCarSelect));
        tv_ensure.setOnClickListener(btnRightListener);
        rlCarSelect.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.activity_translate_in));
        pop.showAtLocation(v, Gravity.BOTTOM, 0, 0);
    }

//    /**
//     * 显示优惠券列表选择框
//     *
//     * @param activity
//     * @param list
//     * @param listener
//     */
//    public void showPopupCouponWindow(final Activity activity, List<CouponBean> list, int clickPosition, final ConfirmClickListener listener) {
//        // pop 正在显示
//        if (pop != null && pop.isShowing()) {
//            pop.dismiss();
//        }
//        closeKeyboard(activity);// 关闭软键盘
//        pop = new PopupWindow(activity);
//        final View view = activity.getLayoutInflater().inflate(R.layout.popup_coupon, null);
//        final RecyclerView listView = (RecyclerView) view.findViewById(R.id.lv_count);
//        TextView btnCancel = (TextView) view.findViewById(R.id.btn_cancel);
//        TextView btnConfirm = (TextView) view.findViewById(R.id.btn_confirm);
//        pop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
//        pop.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
//        pop.setBackgroundDrawable(new ColorDrawable(0xC9000000));
//        pop.setContentView(view);
//        listView.setLayoutManager(new LinearLayoutManager(activity));
//        final CouponAdapter mAdapter = new CouponAdapter(activity, list, listView);
//        mAdapter.setCheckBox(true);
//        mAdapter.setClickPosition(clickPosition);
//        listView.setAdapter(mAdapter);
//        view.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.activity_translate_in));
//        pop.showAtLocation(view, Gravity.BOTTOM, 0, 0);
//
//        btnCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                pop.dismiss();
//                view.clearAnimation();
//            }
//        });
//        btnConfirm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int Position = mAdapter.getClickPosition();
//                LogUtil.e("PopupDialog", "Position = " + Position);
//                listener.clickListener(Position);
//                pop.dismiss();
//                view.clearAnimation();
//            }
//        });
//    }

    public interface ConfirmClickListener {
        void clickListener(int position);
    }

    public interface AlipayLoginListener {
        void getAlipayAccountAndRealname(String account, String realname);
    }

//    /**
//     * 重新登录提示
//     *
//     * @param ac
//     */
//    public void showPopupReLoginPrompt(final Activity ac) {
//        PopupDialog.getInstance().showPopupWindow(ac, 0, null, new SpannableString("您的账号\n在其他地方登录，请重新登录！"), null, "确定",
//                null, new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        UserUtils.getInstance().reLogin(ac);// 退出登录跳转的登录界面
//                        PopupDialog.getInstance().pop.dismiss();
//                        PopupDialog.getInstance().rlPopup.clearAnimation();
//                    }
//                });
//    }

    public class CancelOnClickListener implements View.OnClickListener {
        ViewGroup mViewGroup;

        public CancelOnClickListener(ViewGroup viewGroup) {
            mViewGroup = viewGroup;
        }

        @Override
        public void onClick(View v) {
            pop.dismiss();
            mViewGroup.clearAnimation();
        }
    }

    private boolean hasSdcard() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

//    /**
//     * 关闭软键盘
//     *
//     * @param activity
//     */
//    private void closeKeyboard(Activity activity) {
//        View view = activity.getWindow().peekDecorView();
//        if (view != null) {
//            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
//            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
//        }
//    }
}
