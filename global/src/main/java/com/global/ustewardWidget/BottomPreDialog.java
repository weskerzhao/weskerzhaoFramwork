package com.global.ustewardWidget;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.global.R;


/**
 * Created by Okamiy on 2018/2/5.
 * Email: 542839122@qq.com
 * 权限选择框
 */

public class BottomPreDialog extends Dialog {
    private Context context;

    public BottomPreDialog(@NonNull Context context) {
        super(context);
    }

    /**
     * 构造函数
     *
     * @param context    上下文
     * @param themeResId 主题
     */
    public BottomPreDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    /**
     * 构造器
     */
    public static class Builder {
        private Context context;
        private OnSelectPremissionListener selectListener;
        private String mUserAuthority;

        private boolean isOne = false;
        private boolean isTwo = false;
        private boolean isThree = false;
        private boolean isFour = false;

        public Builder(Context context, String userAuthority,boolean oneIsCheck, boolean twoIsCheck, boolean threeIsCheck, boolean fourIsCheck) {
            this.context = context;
            this.mUserAuthority = userAuthority;
            this.isOne = oneIsCheck;
            this.isTwo = twoIsCheck;
            this.isThree = threeIsCheck;
            this.isFour = fourIsCheck;
        }

        public Builder setSelectListener(OnSelectPremissionListener selectListener) {
            this.selectListener = selectListener;
            return this;
        }


        public BottomPreDialog create() {
            BottomPreDialog bottomDialog = new BottomPreDialog(context, R.style.BottomDialog);
            View contentView = LayoutInflater.from(context).inflate(R.layout.dialog_pup, null);
            TextView cancle = contentView.findViewById(R.id.tv_cancle);
            TextView select = contentView.findViewById(R.id.tv_select);
            TextView commit = contentView.findViewById(R.id.tv_ensure);
            RelativeLayout checkLayoutOne = contentView.findViewById(R.id.white_layout);
            CheckBox checkBoxOne = contentView.findViewById(R.id.white_check);//白名单
            checkBoxOne.setChecked(isOne);
            RelativeLayout checkLayoutTwo = contentView.findViewById(R.id.order_layout);
            CheckBox checkBoxTwo = contentView.findViewById(R.id.order_check);//车位预约
            checkBoxTwo.setChecked(isThree);
            RelativeLayout checkLayoutThree = contentView.findViewById(R.id.card_layout);
            CheckBox checkBoxThree = contentView.findViewById(R.id.card_check);//悠停卡
            checkBoxThree.setChecked(isFour);
            RelativeLayout checkLayoutNew = contentView.findViewById(R.id.pay_layout);
            CheckBox checkBoxNew = contentView.findViewById(R.id.pay_check);//现金缴费
            checkBoxNew.setChecked(isTwo);

            showUserAuthority(checkLayoutOne, checkLayoutNew, checkLayoutTwo, checkLayoutThree);

            checkLayoutOne.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkBoxOne.isChecked()) {
                        checkBoxOne.setChecked(false);
                        setSelectContent(context, select, checkBoxOne, checkBoxNew, checkBoxTwo, checkBoxThree);
                    } else {
                        checkBoxOne.setChecked(true);
                        setSelectContent(context, select, checkBoxOne, checkBoxNew, checkBoxTwo, checkBoxThree);
                    }
                }
            });
            checkLayoutNew.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkBoxNew.isChecked()) {
                        checkBoxNew.setChecked(false);
                        setSelectContent(context, select, checkBoxOne, checkBoxNew, checkBoxTwo, checkBoxThree);
                    } else {
                        checkBoxNew.setChecked(true);
                        setSelectContent(context, select, checkBoxOne, checkBoxNew, checkBoxTwo, checkBoxThree);
                    }
                }
            });
            checkLayoutTwo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkBoxTwo.isChecked()) {
                        checkBoxTwo.setChecked(false);
                        setSelectContent(context, select, checkBoxOne, checkBoxNew, checkBoxTwo, checkBoxThree);
                    } else {
                        checkBoxTwo.setChecked(true);
                        setSelectContent(context, select, checkBoxOne, checkBoxNew, checkBoxTwo, checkBoxThree);
                    }
                }
            });
            checkLayoutThree.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkBoxThree.isChecked()) {
                        checkBoxThree.setChecked(false);
                        setSelectContent(context, select, checkBoxOne, checkBoxNew, checkBoxTwo, checkBoxThree);
                    } else {
                        checkBoxThree.setChecked(true);
                        setSelectContent(context, select, checkBoxOne, checkBoxNew, checkBoxTwo, checkBoxThree);
                    }
                }
            });

            cancle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bottomDialog.cancel();
                }
            });
            commit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (selectListener != null) {
                        selectListener.selectPremission(checkBoxOne.isChecked(), checkBoxNew.isChecked(), checkBoxTwo.isChecked(), checkBoxThree.isChecked());
                    }
                    bottomDialog.cancel();
                }
            });

            bottomDialog.setContentView(contentView);
            ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
            layoutParams.width = context.getResources().getDisplayMetrics().widthPixels;
            contentView.setLayoutParams(layoutParams);
            bottomDialog.getWindow().setGravity(Gravity.BOTTOM);
            bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
            bottomDialog.show();
            return bottomDialog;
        }

        /**
         * 显示权限
         */
        private void showUserAuthority(RelativeLayout checkLayoutOne, RelativeLayout checkLayoutTwo, RelativeLayout checkLayoutThree, RelativeLayout checkLayoutFour) {
            String[] num = mUserAuthority.split(",");
            for (int i = 0; i < num.length; i++) {
                if ("1".equals(num[i])) {
                    checkLayoutOne.setVisibility(View.VISIBLE);
                    continue;
                }
                if ("2".equals(num[i])) {
                    checkLayoutTwo.setVisibility(View.VISIBLE);
                    continue;
                }
                if ("3".equals(num[i])) {
                    checkLayoutThree.setVisibility(View.VISIBLE);
                    continue;
                }
                if ("4".equals(num[i])) {
                    checkLayoutFour.setVisibility(View.VISIBLE);
                    continue;
                }
            }
        }
    }

    private static void setSelectContent(Context context, TextView select, CheckBox checkBoxOne, CheckBox checkBoxNew, CheckBox checkBoxTwo, CheckBox checkBoxThree) {
        if (select != null) {
            int selseNum = 0;
            if (checkBoxOne.isChecked()) {
                selseNum++;
            }
            if (checkBoxNew.isChecked()) {
                selseNum++;
            }
            if (checkBoxTwo.isChecked()) {
                selseNum++;
            }
            if (checkBoxThree.isChecked()) {
                selseNum++;
            }
            select.setText(String.format(context.getResources().getString(R.string.select_num), selseNum));
        }
    }

    /**
     * 选择权限监听
     */
    public interface OnSelectPremissionListener {
        void selectPremission(boolean oneIsCheck, boolean checkBoxNew, boolean twoIsCheck, boolean threeIsCheck);
    }
}
