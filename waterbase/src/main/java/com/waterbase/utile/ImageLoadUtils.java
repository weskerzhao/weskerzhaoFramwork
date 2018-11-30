package com.waterbase.utile;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.waterbase.R;

/**
 * 用来帮助databinding 加载图片（类名无所谓）
 * 作者：Laughing on 2018/8/2 10:23
 * 邮箱：719240226@qq.com
 */
public class ImageLoadUtils {
    @BindingAdapter({"imageUrl"})   //自定义属性给了ImageView
    public static void loadImage(ImageView imageView, String url) {
        if (StrUtil.isEmpty(url)) {
            imageView.setImageResource(R.mipmap.ic_logo);
        } else {
            Glide.with(imageView.getContext())
                    .load(url)
                    .into(imageView);
        }

    }

    @BindingAdapter({"imageUrl2"})   //自定义属性给了ImageView
    public static void loadImage2(ImageView imageView, String url) {
        if (StrUtil.isEmpty(url)) {
            imageView.setVisibility(View.GONE);//隐藏ImageView控件
//            imageView.setImageResource(R.mipmap.ic_logo);
            return;
        } else {
            Glide.with(imageView.getContext())
                    .load(url)
                    .into(imageView);
        }

    }

}
