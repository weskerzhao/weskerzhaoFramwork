package com.global.takephoto.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.global.R;
import com.global.takephoto.view.ClipViewLayout;
import com.global.ui.activity.TitleActivity;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 该页面实现修剪图片的功能
 */
public class ClipImageActivity extends TitleActivity {

    private ClipViewLayout clipViewLayout1;
    private ClipViewLayout clipViewLayout2;
    private ImageView back;
    private TextView tv_ok;
    //类别 1：qq  2：微信
    private int type;


    /**
     * 标题栏处理
     */
    private void initTitle() {
        setTitleText("移动和缩放");//标题
        setRightTextViewVisibity(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_clip_image);
        initTitle();
        type = getIntent().getIntExtra("type", 1);
        initView();
        initListener();

    }

    private void initView() {
        clipViewLayout1 = (ClipViewLayout) findViewById(R.id.clipViewLayout1);
        clipViewLayout2 = (ClipViewLayout) findViewById(R.id.clipViewLayout2);
        back = (ImageView) findViewById(R.id.iv_back);
        setRightTextViewText("确定");
//        tv_ok = (TextView) findViewById(R.id.tv_ok);//保留原来逻辑
    }

    @Override
    protected void rightTextViewOnClick() {
        super.rightTextViewOnClick();
        generateUriAndReturn();

    }

    private void initListener() {
        back.setOnClickListener(v -> finish());
//        tv_ok.setOnClickListener(v -> generateUriAndReturn());//保留原来逻辑

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (type == 1) {
            clipViewLayout1.setVisibility(View.VISIBLE);
            clipViewLayout2.setVisibility(View.GONE);
            //设置图片资源
            clipViewLayout1.setImageSrc(getIntent().getData());
        } else {
            clipViewLayout2.setVisibility(View.VISIBLE);
            clipViewLayout1.setVisibility(View.GONE);
            clipViewLayout2.setImageSrc(getIntent().getData());
        }
    }


    /**
     * 生成Uri并且通过setResult返回给打开的Activity
     */
    private void generateUriAndReturn() {
        //调用返回剪切图
        Bitmap zoomedCropBitmap;
        if (type == 1) {
            zoomedCropBitmap = clipViewLayout1.clip();
        } else {
            zoomedCropBitmap = clipViewLayout2.clip();
        }
        if (zoomedCropBitmap == null) {
            Log.e("android", "zoomedCropBitmap == null");
            return;
        }
        Uri mSaveUri = Uri.fromFile(new File(getCacheDir(), "cropped_" + System.currentTimeMillis() + ".jpg"));
        if (mSaveUri != null) {
            OutputStream outputStream = null;
            try {
                outputStream = getContentResolver().openOutputStream(mSaveUri);
                if (outputStream != null) {
                    zoomedCropBitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream);
                }
            } catch (IOException ex) {
                Log.e("android", "Cannot open file: " + mSaveUri, ex);
            } finally {
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            Intent intent = new Intent();
            intent.setData(mSaveUri);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
