package com.global.ustewardWidget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.global.R;


/**
 * 提示对话框
 * Created by Guangkuo on 2018/1/5.
 */
public class PromptDialog extends Dialog {
    /**
     * 构造函数
     *
     * @param context 上下文
     */
    public PromptDialog(@NonNull Context context) {
        super(context);
    }

    /**
     * 构造函数
     *
     * @param context    上下文
     * @param themeResId 主题
     */
    public PromptDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    /**
     * 构造器
     */
    public static class Builder {
        private Context context;
        private int iconRes;
        private String title;
        private String content;
        private String positive;
        private String negative;
        private OnClickListener positiveListener;
        private OnClickListener negativeListener;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setIcon(@DrawableRes int drawableRes) {
            this.iconRes = drawableRes;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setContent(String content) {
            this.content = content;
            return this;
        }

        public Builder setPositive(String positive) {
            this.positive = positive;
            return this;
        }

        public Builder setNegative(String negative) {
            this.negative = negative;
            return this;
        }

        public Builder setPositiveListener(OnClickListener positiveListener) {
            this.positiveListener = positiveListener;
            return this;
        }

        public Builder setNegativeListener(OnClickListener negativeListener) {
            this.negativeListener = negativeListener;
            return this;
        }

        public PromptDialog create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (inflater == null) {
                throw new RuntimeException("PromptDialog Builder create RuntimeException");
            }
            // instantiate the dialog with the custom Theme
            final PromptDialog dialog = new PromptDialog(context, R.style.Dialog);
            View view = inflater.inflate(R.layout.dialog_prompt, null);
            dialog.addContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            // set the dialog icon
            if (iconRes == 0) {
                view.findViewById(R.id.ivPrompt).setVisibility(View.GONE);
            } else {
                ((ImageView) view.findViewById(R.id.ivPrompt)).setImageResource(iconRes);
            }
            // set the dialog title
            if (TextUtils.isEmpty(title)) {
                view.findViewById(R.id.tvPromptTitle).setVisibility(View.GONE);
            } else {
                ((TextView) view.findViewById(R.id.tvPromptTitle)).setText(title);
            }
            // set the content message
            ((TextView) view.findViewById(R.id.tvPromptContent)).setText(content);
            // set the confirm button
            boolean isPositiveNull = TextUtils.isEmpty(positive);
            boolean isNegativeNull = TextUtils.isEmpty(negative);
            if (isPositiveNull) {
                view.findViewById(R.id.btnPromptPositive).setVisibility(View.GONE);
            } else {
                Button btnPromptPositive = view.findViewById(R.id.btnPromptPositive);
                btnPromptPositive.setText(positive);
                if (positiveListener != null) {
                    btnPromptPositive.setOnClickListener(v -> positiveListener.onClick(dialog, DialogInterface.BUTTON_POSITIVE));
                } else {
                    btnPromptPositive.setOnClickListener(v -> dialog.dismiss());
                }
                if (isNegativeNull) {
                    btnPromptPositive.setBackgroundResource(R.drawable.shape_dialog_single_btn);
                }
            }
            // set the cancel button
            if (isNegativeNull) {
                view.findViewById(R.id.btnPromptNegative).setVisibility(View.GONE);
            } else {
                Button btnPromptNegative = view.findViewById(R.id.btnPromptNegative);
                btnPromptNegative.setText(negative);
                if (negativeListener != null) {
                    btnPromptNegative.setOnClickListener(v -> negativeListener.onClick(dialog, DialogInterface.BUTTON_NEGATIVE));
                } else {
                    btnPromptNegative.setOnClickListener(v -> dialog.dismiss());
                }
                if (isPositiveNull) {
                    btnPromptNegative.setBackgroundResource(R.drawable.shape_dialog_single_btn);
                }
            }
            dialog.setContentView(view);
            setWindowWidth(dialog);
            return dialog;
        }

        /**
         * 设置 dialog 窗口宽度
         */
        private void setWindowWidth(@NonNull PromptDialog dialog) {
            Window window = dialog.getWindow();
            if (window == null) {
                return;
            }
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.width = ConvertUtils.dp2px(270);
            window.setAttributes(lp);
        }
    }
}
