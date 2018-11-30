package com.waterbase.utile;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.waterbase.widget.glide.GlideCircleTransform;


/**
 * 图片处理工具类
 * Created by Water on 2017/6/20.
 */

public class GlideUtile {

//    @BindingAdapter({"informationUrl"})
    public static void loadImage(ImageView imageView, String url, int loadingRes, int errorRes) {
        Glide.with(imageView.getContext())
                .load(url)
                .placeholder(loadingRes) // 加载时的图片
                .error(errorRes) // 错误是的图片
                .fitCenter()
                .into(imageView);
    }

    //    @BindingAdapter({"informationUrl"})

    /**
     * purpose: 自动拉伸
     * @param imageView
     * @param url
     * @param loadingRes
     * @param errorRes
     */
    public static void loadImageStretch(ImageView imageView, String url, int loadingRes, int errorRes) {

        Glide.with(imageView.getContext())
                .load(url)
                .placeholder(loadingRes) // 加载时的图片
                .error(errorRes) // 错误是的图片
                .centerCrop()
                .into(imageView);
    }

    /**
     * 加载网络圆形图片
     *
     * @param imageView
     * @param url
     * @param loadingRes
     * @param errorRes
     */
    public static void loadCircleImage(ImageView imageView, String url, int loadingRes, int errorRes) {
        Glide.with(imageView.getContext())
                .load(url)
                .placeholder(loadingRes) // 加载时的图片
                .error(errorRes) // 错误是的图片
                .transform(new GlideCircleTransform(imageView.getContext()))
                .into(imageView);
    }

    /**
     * 加载本地资源文件
     *
     * @param imageView
     * @param resourceId
     */
    public static void loadCircleImage(ImageView imageView, int resourceId) {
        Glide.with(imageView.getContext())
                .load(resourceId)
                .transform(new GlideCircleTransform(imageView.getContext()))
                .into(imageView);
    }

//    @BindingAdapter({"informationUrl","round"})
//    public static void loadRoundImage(final ImageView imageView, String url,int round) {
//
//        Glide.with(imageView.getContext())
//                .load(url)
//                .transform(new GlideRoundTransform(imageView.getContext(),round))
//                .placeholder(R.mipmap.shanpin) // 加载时的图片
//                .error(R.mipmap.shanpin) // 错误是的图片
//                .into(imageView);
//    }


}
