package com.global.util;

import android.widget.ImageView;

import com.global.R;
import com.waterbase.utile.GlideUtile;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by edz on 2018/5/16.
 */

public class ImageUtils {
    /**
     * 图片转为上传参数LMultipartBody.Part
     * @param path
     * */
    public static MultipartBody.Part fileToMultipartBodyPart(String path) {
        File file = new File(path);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"),file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
        return part;
    }

    /**
     * 将String参数转为上传参数RequestBody
     * @param param
     * */
    public static RequestBody convertToRequestBody(String param) {
//        RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain"), param);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), param);
        return requestBody;
    }

    /**
     * 加载网络圆形图片
     *
     * @param imageView
     * @param url
     */
    public static void loadCircleImage(ImageView imageView, String url) {
        GlideUtile.loadCircleImage(imageView, url, R.mipmap.ic_head_normal, R.mipmap.ic_head_normal);
    }

    /**
     * 加载网络方形图片
     *
     * @param imageView
     * @param url
     */
    public static void loadImage(ImageView imageView, String url) {
        GlideUtile.loadImage(imageView, url, R.mipmap.ic_head_normal, R.mipmap.ic_head_normal);
    }

}
