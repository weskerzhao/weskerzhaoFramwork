package com.global.ustewardUtil;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;

import com.global.ustewardWidget.BottomPreDialog;
import com.global.ustewardWidget.PromptDialog;

/**
 * Dialog 工具类
 * Created by Guangkuo on 2018/2/1.
 */
public class DialogUtils {
    private static PromptDialog mPromptDialog;

//    /**
//     * 加载对话框
//     *
//     * @param context    上下文
//     * @param contentRes 内容资源
//     * @return MaterialDialog
//     */
//    public static LoadingDialog showLoadDialog(@NonNull Context context, @StringRes int contentRes) {
//        return showLoadDialog(context, ResUtils.getString(contentRes));
//    }
//
//    /**
//     * 加载对话框
//     *
//     * @param context 上下文
//     * @param content 内容
//     * @return MaterialDialog
//     */
//    public static LoadingDialog showLoadDialog(@NonNull Context context, String content) {
//        LoadingDialog loadingDialog = new LoadingDialog(context);
//        loadingDialog.setLoadingText(content).show();// 设置loading时显示的文字
//        return loadingDialog;
//    }
//
//    /**
//     * 提示对话框
//     *
//     * @param context          上下文
//     * @param drawableRes      图片
//     * @param title            标题
//     * @param content          内容
//     * @param positive         正按钮
//     * @param negative         负按钮
//     * @param positiveListener 正按钮监听
//     * @param negativeListener 负按钮监听
//     */
//    public static PromptDialog showPromptDialog(@NonNull Context context, @DrawableRes int drawableRes,
//                                                @StringRes int title, @StringRes int content, @StringRes int positive, @StringRes int negative,
//                                                DialogInterface.OnClickListener positiveListener, DialogInterface.OnClickListener negativeListener) {
//        String titleStr = title == 0 ? "" : ResUtils.getString(title);
//        String contentStr = content == 0 ? "" : ResUtils.getString(content);
//        String positiveStr = positive == 0 ? "" : ResUtils.getString(positive);
//        String negativeStr = negative == 0 ? "" : ResUtils.getString(negative);
//        return showPromptDialog(context, drawableRes, titleStr, contentStr, positiveStr, negativeStr, positiveListener, negativeListener);
//    }

    /**
     * 提示对话框
     *
     * @param context          上下文
     * @param drawableRes      图片
     * @param title            标题
     * @param content          内容
     * @param positive         正按钮
     * @param negative         负按钮
     * @param positiveListener 正按钮监听
     * @param negativeListener 负按钮监听
     */
    public static PromptDialog showPromptDialog(@NonNull Context context, @DrawableRes int drawableRes,
                                                String title, String content, String positive, String negative,
                                                DialogInterface.OnClickListener positiveListener, DialogInterface.OnClickListener negativeListener) {
        if (mPromptDialog != null && mPromptDialog.isShowing()) {
            mPromptDialog.dismiss();
        }
        mPromptDialog = new PromptDialog.Builder(context)
                .setIcon(drawableRes)
                .setTitle(title)
                .setContent(content)
                .setPositive(positive)
                .setNegative(negative)
                .setPositiveListener(positiveListener)
                .setNegativeListener(negativeListener)
                .create();
        return mPromptDialog;
    }

    /**
     * 底部权限弹框
     *
     * @param context
     * @param premissionListener 监听
     * @return
     */
    public static BottomPreDialog showBottomDialog(@NonNull Context context, boolean isOne, boolean isTwo, boolean isThree, boolean isFour, String userAuthority, BottomPreDialog.OnSelectPremissionListener premissionListener) {
        BottomPreDialog.Builder builder = new BottomPreDialog.Builder(context, userAuthority,isOne, isTwo, isThree, isFour);
        return builder.setSelectListener(premissionListener).create();
    }

}
