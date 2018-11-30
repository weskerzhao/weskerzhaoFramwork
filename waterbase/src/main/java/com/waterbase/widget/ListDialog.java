package com.waterbase.widget;

import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;

import com.waterbase.R;
import com.waterbase.databinding.DialogListBinding;
import com.waterbase.ui.BaseApplication;
import com.waterbase.utile.ViewUtil;

/**
 * Created by Water on 2018/5/8.
 */

public class ListDialog extends Dialog {
    public ListDialog(@NonNull Context context) {
        super(context);
    }

    public ListDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    public static class Builder {
        private Context context;
        private String title;
        private RecyclerView.Adapter adapter;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setAdapter(RecyclerView.Adapter adapter) {
            this.adapter = adapter;
            return this;
        }

        /**
         * Set the Dialog title from resource
         *
         * @param title
         * @return
         */
        public Builder setTitle(int title) {
            this.title = (String) context.getText(title);
            return this;
        }

        /**
         * Set the Dialog title from String
         *
         * @param title
         * @return
         */

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public ListDialog create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // instantiate the dialog with the custom Theme
            final ListDialog dialog = new ListDialog(context, R.style.custom_dialog2);
            View layout = inflater.inflate(R.layout.dialog_list, null);
            DialogListBinding binding = DataBindingUtil.bind(layout);
            binding.setTitle(title);
            binding.recyclerview.setLayoutManager(new LinearLayoutManager(context));
            binding.recyclerview.setAdapter(adapter);
            dialog.addContentView(layout, new LayoutParams(
                    LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            dialog.setContentView(layout);
            return dialog;
        }
    }

    public void show() {
        super.show();
        /**
         * 设置宽度全屏，要设置在show的后面
         */
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.gravity = Gravity.CENTER;
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        int margin = (int) ViewUtil.dp2px(BaseApplication.getAppContext(), 12);
        getWindow().getDecorView().setPadding(margin, 0, margin, 0);
        getWindow().setAttributes(layoutParams);
    }
}
