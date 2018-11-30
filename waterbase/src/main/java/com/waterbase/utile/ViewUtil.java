package com.waterbase.utile;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.os.Build.VERSION;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.waterbase.global.AppConfig;

/**
 * 处理View的工具类
 */
public class ViewUtil {
    /**
     * 无效值
     */
    public static final int INVALID = Integer.MIN_VALUE;

    /**
     * 描述：重置AbsListView的高度. item 的最外层布局要用
     * RelativeLayout,如果计算的不准，就为RelativeLayout指定一个高度
     *
     * @param listView      the abs list view
     * @param lineNumber    每行几个 ListView一行一个item
     * @param verticalSpace the vertical space
     */
    public static void setListViewHeight(ListView listView,
                                         int lineNumber, int verticalSpace) {

        int totalHeight = getAbsListViewHeight(listView, lineNumber,
                verticalSpace);
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight;
        ((MarginLayoutParams) params).setMargins(0, 0, 0, 0);
        listView.setLayoutParams(params);
    }

    /**
     * 动态设置控件高度和屏幕宽度的比例
     *
     * @param activity
     * @param view     需要设置大小的view
     * @param ratio    比例：宽/高的值
     */
    public static void setViewHeightAboutWindow(Activity activity, View view, double ratio) {

        DisplayMetrics dm = new DisplayMetrics();
        // 取得窗口属性
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        // 窗口的宽度
        int screenWidth = dm.widthPixels;
        // 窗口高度
        int screenHeight = dm.heightPixels;
        LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) view
                .getLayoutParams(); // 取控件textView当前的布局参数
        linearParams.height = (int) (screenWidth / ratio);// 控件的高强制设成20
        linearParams.width = screenWidth;// 控件的宽强制设成30
        view.setLayoutParams(linearParams); // 使设置好的布局参数应用到控件</pre>
    }

    public static void setViewHeight(View view, float height) {

        LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) view
                .getLayoutParams(); // 取控件textView当前的布局参数
        linearParams.height = (int) height;// 控件的高强制设成20
        view.setLayoutParams(linearParams); // 使设置好的布局参数应用到控件</pre>
    }

    /**
     * 动态设置控件高度和自身宽度的比例
     *
     * @param view  需要设置大小的view
     * @param ratio 比例：宽/高的值
     */
    public static void setViewHeightAboutSelf(final View view, final double ratio) {

        ViewTreeObserver vto = view.getViewTreeObserver();
        vto.addOnPreDrawListener(() -> {
            int screenHeight = view.getMeasuredHeight();
            int screenWidth = view.getMeasuredWidth();
            ViewGroup.LayoutParams linearParams = view
                    .getLayoutParams(); // 取控件textView当前的布局参数
            linearParams.height = (int) (screenWidth / ratio);// 控件的高强制设成20
            linearParams.width = screenWidth;// 控件的宽强制设成30
            view.setLayoutParams(linearParams); // 使设置好的布局参数应用到控件</pre>
            return true;
        });
    }

    /**
     * 重新绘制listview的高度，用来设置ScordView中的listview，注意这个listview必须是自己重写的
     *
     * @param listView
     */
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) { // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);

            int desiredWidth = MeasureSpec.makeMeasureSpec(listView.getWidth(), MeasureSpec.AT_MOST);
            listItem.measure(desiredWidth, 0);

//            listItem.measure(0, 0); // 计算子项View 的宽高
            Log.d("---------", "listItem.getMeasuredHeight():" + listItem.getMeasuredHeight());
            totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
//         listView.getDividerHeight();获取子项间分隔符占用的高度
//         params.height;最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }


    /**
     * 描述：获取AbsListView的高度.
     *
     * @param absListView   the abs list view
     * @param lineNumber    每行几个 ListView一行一个item
     * @param verticalSpace the vertical space
     * @return the abs list view height
     */
    public static int getAbsListViewHeight(AbsListView absListView,
                                           int lineNumber, int verticalSpace) {
        int totalHeight = 0;
        int w = MeasureSpec.makeMeasureSpec(0,
                MeasureSpec.UNSPECIFIED);
        int h = MeasureSpec.makeMeasureSpec(0,
                MeasureSpec.UNSPECIFIED);
        absListView.measure(w, h);
        ListAdapter mListAdapter = absListView.getAdapter();
        if (mListAdapter == null) {
            return totalHeight;
        }

        int count = mListAdapter.getCount();
        if (absListView instanceof ListView) {
            for (int i = 0; i < count; i++) {
                View listItem = mListAdapter.getView(i, null, absListView);
                listItem.measure(w, h);
                totalHeight += listItem.getMeasuredHeight();
            }
            if (count == 0) {
                totalHeight = verticalSpace;
            } else {
                totalHeight = totalHeight
                        + (((ListView) absListView).getDividerHeight() * (count - 1));
            }

        } else if (absListView instanceof GridView) {
            int remain = count % lineNumber;
            if (remain > 0) {
                remain = 1;
            }
            if (mListAdapter.getCount() == 0) {
                totalHeight = verticalSpace;
            } else {
                View listItem = mListAdapter.getView(0, null, absListView);
                listItem.measure(w, h);
                int line = count / lineNumber + remain;
                totalHeight = line * listItem.getMeasuredHeight() + (line - 1)
                        * verticalSpace;
            }

        }
        return totalHeight;

    }

    /**
     * 测量这个view
     * 最后通过getMeasuredWidth()获取宽度和高度.
     *
     * @param view 要测量的view
     * @return 测量过的view
     */
    public static void measureView(View view) {
        ViewGroup.LayoutParams p = view.getLayoutParams();
        if (p == null) {
            p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0 + 0, p.width);
        int lpHeight = p.height;
        int childHeightSpec;
        if (lpHeight > 0) {
            childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight,
                    MeasureSpec.EXACTLY);
        } else {
            childHeightSpec = MeasureSpec.makeMeasureSpec(0,
                    MeasureSpec.UNSPECIFIED);
        }
        view.measure(childWidthSpec, childHeightSpec);
    }

    /**
     * 获得这个View的宽度
     * 测量这个view，最后通过getMeasuredWidth()获取宽度.
     *
     * @param view 要测量的view
     * @return 测量过的view的宽度
     */
    public static int getViewWidth(View view) {
        measureView(view);
        return view.getMeasuredWidth();
    }

    /**
     * 获得这个View的高度
     * 测量这个view，最后通过getMeasuredHeight()获取高度.
     *
     * @param view 要测量的view
     * @return 测量过的view的高度
     */
    public static int getViewHeight(View view) {
        measureView(view);
        return view.getMeasuredHeight();
    }

    /**
     * 从父亲布局中移除自己
     *
     * @param v
     */
    public static void removeSelfFromParent(View v) {
        ViewParent parent = v.getParent();
        if (parent != null) {
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(v);
            }
        }
    }


    /**
     * 描述：dip转换为px.
     *
     * @param context  the context
     * @param dipValue the dip value
     * @return px值
     */
    public static float dip2px(Context context, float dipValue) {
        DisplayMetrics mDisplayMetrics = AppUtil.getDisplayMetrics(context);
        return applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, mDisplayMetrics);
    }

    /**
     * 描述：px转换为dip.
     *
     * @param context the context
     * @param pxValue the px value
     * @return dip值
     */
    public static float px2dip(Context context, float pxValue) {
        DisplayMetrics mDisplayMetrics = AppUtil.getDisplayMetrics(context);
        return pxValue / mDisplayMetrics.density;
    }

    /**
     * 描述：sp转换为px.
     *
     * @param context the context
     * @param spValue the sp value
     * @return sp值
     */
    public static float sp2px(Context context, float spValue) {
        DisplayMetrics mDisplayMetrics = AppUtil.getDisplayMetrics(context);
        return applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue, mDisplayMetrics);
    }

    /**
     * 描述：dp转换为px.
     *
     * @param context
     * @param spValue
     * @return
     */
    public static float dp2px(Context context, float spValue) {
        DisplayMetrics mDisplayMetrics = AppUtil.getDisplayMetrics(context);
        return applyDimension(TypedValue.COMPLEX_UNIT_DIP, spValue, mDisplayMetrics);
    }

    /**
     * 描述：px转换为sp.
     *
     * @param context the context
     * @param pxValue the sp value
     * @return sp值
     */
    public static float px2sp(Context context, float pxValue) {
        DisplayMetrics mDisplayMetrics = AppUtil.getDisplayMetrics(context);
        return pxValue / mDisplayMetrics.scaledDensity;
    }

    /**
     * 描述：根据屏幕大小缩放.
     *
     * @param context the context
     * @param value   the px value
     * @return the int
     */
    public static int scaleValue(Context context, float value) {
        DisplayMetrics mDisplayMetrics = AppUtil.getDisplayMetrics(context);
        //为了兼容尺寸小密度大的情况
        int width = mDisplayMetrics.widthPixels;
        int height = mDisplayMetrics.heightPixels;
        //解决横屏比例问题
        if (width > height) {
            width = mDisplayMetrics.heightPixels;
            height = mDisplayMetrics.widthPixels;
        }
        if (mDisplayMetrics.scaledDensity == AppConfig.UI_DENSITY) {
            //密度
            if (width > AppConfig.UI_WIDTH) {
                value = value * (1.3f - 1.0f / mDisplayMetrics.scaledDensity);
            } else if (width < AppConfig.UI_WIDTH) {
                value = value * (1.0f - 1.0f / mDisplayMetrics.scaledDensity);
            }
        } else {
            //密度小屏幕大:缩小比例
            float offset = AppConfig.UI_DENSITY - mDisplayMetrics.scaledDensity;
            if (offset > 0.5f) {
                value = value * 0.9f;
            } else {
                value = value * 0.95f;
            }

        }
        return scale(mDisplayMetrics.widthPixels, mDisplayMetrics.heightPixels, value);
    }

    /**
     * 描述：根据屏幕大小缩放文本.
     *
     * @param context the context
     * @param value   the px value
     * @return the int
     */
    public static int scaleTextValue(Context context, float value) {
        return scaleValue(context, value);
    }

    /**
     * 描述：根据屏幕大小缩放.
     *
     * @param displayWidth  the display width
     * @param displayHeight the display height
     * @param pxValue       the px value
     * @return the int
     */
    public static int scale(int displayWidth, int displayHeight, float pxValue) {
        if (pxValue == 0) {
            return 0;
        }
        float scale = 1;
        try {
            int width = displayWidth;
            int height = displayHeight;
            //解决横屏比例问题
            if (width > height) {
                width = displayHeight;
                height = displayWidth;
            }
            float scaleWidth = (float) width / AppConfig.UI_WIDTH;
            float scaleHeight = (float) height / AppConfig.UI_HEIGHT;
            scale = Math.min(scaleWidth, scaleHeight);
        } catch (Exception e) {
        }
        return Math.round(pxValue * scale + 0.5f);
    }


    /**
     * TypedValue官方源码中的算法，任意单位转换为PX单位
     *
     * @param unit    TypedValue.COMPLEX_UNIT_DIP
     * @param value   对应单位的值
     * @param metrics 密度
     * @return px值
     */
    public static float applyDimension(int unit, float value,
                                       DisplayMetrics metrics) {
        switch (unit) {
            case TypedValue.COMPLEX_UNIT_PX:
                return value;
            case TypedValue.COMPLEX_UNIT_DIP:
                return value * metrics.density;
            case TypedValue.COMPLEX_UNIT_SP:
                return value * metrics.scaledDensity;
            case TypedValue.COMPLEX_UNIT_PT:
                return value * metrics.xdpi * (1.0f / 72);
            case TypedValue.COMPLEX_UNIT_IN:
                return value * metrics.xdpi;
            case TypedValue.COMPLEX_UNIT_MM:
                return value * metrics.xdpi * (1.0f / 25.4f);
        }
        return 0;
    }


    /**
     * 描述：View树递归调用做适配.
     * AppConfig.uiWidth = 1080;
     * AppConfig.uiHeight = 700;
     * scaleContentView((RelativeLayout)findViewById(R.id.rootLayout));
     * 要求布局中的单位都用px并且和美工的设计图尺寸一致，包括所有宽高，Padding,Margin,文字大小
     *
     * @param contentView
     */
    public static void scaleContentView(ViewGroup contentView) {
        ViewUtil.scaleView(contentView);
        if (contentView.getChildCount() > 0) {
            for (int i = 0; i < contentView.getChildCount(); i++) {
                View view = contentView.getChildAt(i);
                if (view instanceof ViewGroup) {
                    if (isNeedScale(view)) {
                        scaleContentView((ViewGroup) (view));
                    }
                } else {
                    scaleView(contentView.getChildAt(i));
                }
            }
        }
    }

    /**
     * 描述：是否需要Scale.
     *
     * @param view
     * @return
     */
    public static boolean isNeedScale(View view) {
        return true;
    }


    /**
     * 描述：View树递归调用做适配.
     * AppConfig.uiWidth = 1080;
     * AppConfig.uiHeight = 700;
     * scaleContentView(context,R.id.rootLayout);
     * 要求布局中的单位都用px并且和美工的设计图尺寸一致，包括所有宽高，Padding,Margin,文字大小
     *
     * @param parent
     * @param id
     */
    public static void scaleContentView(View parent, int id) {
        ViewGroup contentView = null;
        View view = parent.findViewById(id);
        if (view instanceof ViewGroup) {
            contentView = (ViewGroup) view;
            scaleContentView(contentView);
        }
    }

    /**
     * 描述：View树递归调用做适配.
     * AppConfig.uiWidth = 1080;
     * AppConfig.uiHeight = 700;
     * scaleContentView(context,R.id.rootLayout);
     * 要求布局中的单位都用px并且和美工的设计图尺寸一致，包括所有宽高，Padding,Margin,文字大小
     *
     * @param context
     * @param id
     */
    public static void scaleContentView(Context context, int id) {
        ViewGroup contentView = null;
        View view = ((Activity) context).findViewById(id);
        if (view instanceof ViewGroup) {
            contentView = (ViewGroup) view;
            scaleContentView(contentView);
        }
    }

    /**
     * 按比例缩放View，以布局中的尺寸为基准
     *
     * @param view
     */
    @SuppressLint("NewApi")
    public static void scaleView(View view) {
        if (!isNeedScale(view)) {
            return;
        }
        if (view instanceof TextView) {
            TextView textView = (TextView) view;
            setTextSize(textView, textView.getTextSize());
        }

        ViewGroup.LayoutParams params = (ViewGroup.LayoutParams) view.getLayoutParams();
        if (null != params) {
            int width = INVALID;
            int height = INVALID;
            if (params.width != ViewGroup.LayoutParams.WRAP_CONTENT
                    && params.width != ViewGroup.LayoutParams.MATCH_PARENT) {
                width = params.width;
            }

            if (params.height != ViewGroup.LayoutParams.WRAP_CONTENT
                    && params.height != ViewGroup.LayoutParams.MATCH_PARENT) {
                height = params.height;
            }

            //size
            setViewSize(view, width, height);

            // Padding
            setPadding(view, view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom());
        }

        // Margin
        if (view.getLayoutParams() instanceof MarginLayoutParams) {
            MarginLayoutParams mMarginLayoutParams = (MarginLayoutParams) view
                    .getLayoutParams();
            if (mMarginLayoutParams != null) {
                setMargin(view, mMarginLayoutParams.leftMargin, mMarginLayoutParams.topMargin, mMarginLayoutParams.rightMargin, mMarginLayoutParams.bottomMargin);
            }
        }

        if (VERSION.SDK_INT >= 16) {
            //最大最小宽高
            int minWidth = scaleValue(view.getContext(), view.getMinimumWidth());
            int minHeight = scaleValue(view.getContext(), view.getMinimumHeight());
            view.setMinimumWidth(minWidth);
            view.setMinimumHeight(minHeight);
        }
    }


    /**
     * 缩放文字大小
     *
     * @param textView button
     * @param size     sp值
     * @return
     */
    public static void setSPTextSize(TextView textView, float size) {
        float scaledSize = scaleTextValue(textView.getContext(), size);
        textView.setTextSize(scaledSize);
    }

    /**
     * 缩放文字大小,这样设置的好处是文字的大小不和密度有关，
     * 能够使文字大小在不同的屏幕上显示比例正确
     *
     * @param textView   button
     * @param sizePixels px值
     * @return
     */
    public static void setTextSize(TextView textView, float sizePixels) {
        float scaledSize = scaleTextValue(textView.getContext(), sizePixels);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, scaledSize);
    }

    /**
     * 缩放文字大小
     *
     * @param context
     * @param textPaint
     * @param sizePixels px值
     * @return
     */
    public static void setTextSize(Context context, TextPaint textPaint, float sizePixels) {
        float scaledSize = scaleTextValue(context, sizePixels);
        textPaint.setTextSize(scaledSize);
    }

    /**
     * 缩放文字大小
     *
     * @param context
     * @param paint
     * @param sizePixels px值
     * @return
     */
    public static void setTextSize(Context context, Paint paint, float sizePixels) {
        float scaledSize = scaleTextValue(context, sizePixels);
        paint.setTextSize(scaledSize);
    }

    /**
     * 设置View的PX尺寸
     *
     * @param view         如果是代码new出来的View，需要设置一个适合的LayoutParams
     * @param widthPixels
     * @param heightPixels
     */
    public static void setViewSize(View view, int widthPixels, int heightPixels) {
        int scaledWidth = scaleValue(view.getContext(), widthPixels);
        int scaledHeight = scaleValue(view.getContext(), heightPixels);
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (params == null) {
            LogUtil.e(ViewUtil.class, "setViewSize出错,如果是代码new出来的View，需要设置一个适合的LayoutParams");
            return;
        }
        if (widthPixels != INVALID) {
            params.width = scaledWidth;
        }
        if (heightPixels != INVALID && heightPixels != 1) {
            params.height = scaledHeight;
        }
        view.setLayoutParams(params);
    }

    /**
     * 设置PX padding.
     *
     * @param view   the view
     * @param left   the left padding in pixels
     * @param top    the top padding in pixels
     * @param right  the right padding in pixels
     * @param bottom the bottom padding in pixels
     */
    public static void setPadding(View view, int left,
                                  int top, int right, int bottom) {
        int scaledLeft = scaleValue(view.getContext(), left);
        int scaledTop = scaleValue(view.getContext(), top);
        int scaledRight = scaleValue(view.getContext(), right);
        int scaledBottom = scaleValue(view.getContext(), bottom);
        view.setPadding(scaledLeft, scaledTop, scaledRight, scaledBottom);
    }

    /**
     * 设置 PX margin.
     *
     * @param view   the view
     * @param left   the left margin in pixels
     * @param top    the top margin in pixels
     * @param right  the right margin in pixels
     * @param bottom the bottom margin in pixels
     */
    public static void setMargin(View view, int left, int top,
                                 int right, int bottom) {
        int scaledLeft = scaleValue(view.getContext(), left);
        int scaledTop = scaleValue(view.getContext(), top);
        int scaledRight = scaleValue(view.getContext(), right);
        int scaledBottom = scaleValue(view.getContext(), bottom);

        if (view.getLayoutParams() instanceof MarginLayoutParams) {
            MarginLayoutParams mMarginLayoutParams = (MarginLayoutParams) view
                    .getLayoutParams();
            if (mMarginLayoutParams != null) {
                if (left != INVALID) {
                    mMarginLayoutParams.leftMargin = scaledLeft;
                }
                if (right != INVALID) {
                    mMarginLayoutParams.rightMargin = scaledRight;
                }
                if (top != INVALID) {
                    mMarginLayoutParams.topMargin = scaledTop;
                }
                if (bottom != INVALID) {
                    mMarginLayoutParams.bottomMargin = scaledBottom;
                }
                view.setLayoutParams(mMarginLayoutParams);
            }
        }

    }


}
