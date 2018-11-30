package com.global.widget;

import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

import com.global.R;
import com.global.databinding.ListDialogPlusPlusBinding;


/**
 * Created by Water on 2018/5/8.
 */

public class ListDialogPlusPlus extends Dialog {
    public ListDialogPlusPlus(@NonNull Context context) {
        super(context);
    }

    public ListDialogPlusPlus(@NonNull Context context, int themeResId) {
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

        public ListDialogPlusPlus create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // instantiate the dialog with the custom Theme
            final ListDialogPlusPlus dialog = new ListDialogPlusPlus(context, R.style.custom_dialog2);
            View layout = inflater.inflate(R.layout.list_dialog_plus_plus, null);
            ListDialogPlusPlusBinding binding = DataBindingUtil.bind(layout);
            binding.setTitle(title);
            binding.recyclerview.setLayoutManager(new LinearLayoutManager(context));
            binding.recyclerview.setAdapter(adapter);
            dialog.addContentView(layout, new LayoutParams(
                    LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            dialog.setContentView(layout);
            return dialog;
        }
    }
}
