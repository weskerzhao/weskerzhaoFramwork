package com.waterbase.utile;

import android.app.Activity;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.waterbase.R;


/**
 * 创建一个PopupWindow
 * Created by water on 2016/1/20.
 */
public class PopupWindowUtile {

    public static final int BOTTOM = 100; // 正下方
    public static final int BOTTOM_LEFT_ALIGN = 101; // 下方左对齐
    public static final int BOTTOM_RIGHT_ALIGN = 102; // 下方右对齐
    public static final int LEFT = 110; // 左边上下居中
    public static final int LEFT_TOP_ALIGN = 111; // 左边顶部对齐
    public static final int LEFT_BOTTOM_ALIGN = 112; // 左边底部对齐
    public static final int RIGHT = 120; // 左边上下居中
    public static final int RIGHT_TOP_ALIGN = 121; // 左边顶部对齐
    public static final int RIGHT_BOTTOM_ALIGN = 122; // 左边底部对齐
    public static final int TOP = 130; // 左边上下居中
    public static final int TOP_LEFT_ALIGN = 131; // 左边顶部对齐
    public static final int TOP_RIGHT_ALIGN = 132; // 左边底部对齐

    private static final String TAG = "PopupWindowUtile";
    public static Activity context;
    public static PopupWindow popupWindow;

    /**
     * 默认弹出在屏幕底部点击窗口外消失
     *
     * @param context
     * @param view    Activity根布局
     * @param layout
     * @return
     */
    public static View showAdaptive(Activity context, View view, int layout) {
        return showAdaptive(context, view, layout, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0,
                R.style.dialogAnim_bottom_into, true, true);
    }

    /**
     * @param context
     * @param view             Activity根布局
     * @param layout
     * @param ChangeBackground 弹出Popup时 是否改变原来Activity的颜色
     * @return
     */
    public static View showAdaptive(Activity context, View view, int layout,
                                    boolean ChangeBackground) {
        return showAdaptive(context, view, layout, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0,
                R.style.dialogAnim_bottom_into, ChangeBackground, true);
    }

    /**
     * @param context
     * @param view    Activity根布局
     * @param layout
     * @param gravity
     * @return
     */
    public static View showAdaptive(Activity context, View view, int layout, int gravity) {
        return showAdaptive(context, view, layout, gravity, 0, 0,
                R.style.dialogAnim_bottom_into, true, true);
    }

    /**
     * @param context
     * @param view             Activity根布局
     * @param layout
     * @param gravity
     * @param ChangeBackground
     * @return
     */
    public static View showAdaptive(Activity context, View view, int layout,
                                    int gravity, boolean ChangeBackground) {
        return showAdaptive(context, view, layout, gravity, 0, 0, R.style.Right_into_the_left_out,
                ChangeBackground, true);
    }

    /**
     * @param context
     * @param view           Activity根布局
     * @param layout
     * @param gravity
     * @param animationStyle
     * @return
     */
    public static View showAdaptive(Activity context, View view, int layout,
                                    int gravity, int animationStyle) {
        return showAdaptive(context, view, layout, gravity, 0, 0, animationStyle, true, true);
    }

    /**
     * @param context
     * @param view             Activity根布局
     * @param layout
     * @param gravity
     * @param animationStyle
     * @param ChangeBackground
     * @return
     */
    public static View showAdaptive(Activity context, View view, int layout,
                                    int gravity, int animationStyle, boolean ChangeBackground) {
        return showAdaptive(context, view, layout, gravity, 0, 0, animationStyle, ChangeBackground, true);
    }

    /**
     * @param context
     * @param view    Activity根布局
     * @param layout
     * @param gravity
     * @param xOffset
     * @param yOffset
     * @return
     */
    public static View showAdaptive(Activity context, View view, int layout,
                                    int gravity, int xOffset, int yOffset) {
        return showAdaptive(context, view, layout, gravity, xOffset, yOffset,
                R.style.dialogAnim_bottom_into, true, true);
    }

    /**
     * @param context
     * @param view             Activity根布局
     * @param layout
     * @param gravity
     * @param xOffset
     * @param yOffset
     * @param ChangeBackground
     * @return
     */
    public static View showAdaptive(Activity context, View view, int layout,
                                    int gravity, int xOffset, int yOffset, boolean ChangeBackground) {
        return showAdaptive(context, view, layout, gravity, xOffset, yOffset,
                R.style.dialogAnim_bottom_into, ChangeBackground, true);
    }

    /**
     * @param context
     * @param view           Activity根布局
     * @param layout
     * @param gravity
     * @param xOffset
     * @param yOffset
     * @param animationStyle
     * @return
     */
    public static View showAdaptive(Activity context, View view, int layout,
                                    int gravity, int xOffset, int yOffset, int animationStyle) {
        return showAdaptive(context, view, layout, gravity, xOffset, yOffset,
                animationStyle, true, true);
    }

    /**
     * 在页面中创建一个自适应高度的PopupWindow
     *
     * @param context
     * @param view             Activity根布局
     * @param layout           弹出的布局
     * @param gravity          the gravity which controls the placement of the popup window
     * @param xOffset          the popup's xOffset location offset
     * @param yOffset          the popup's yOffset location offset
     * @param animationStyle   animation style to use when the popup appears
     *                         and disappears.  Set to -1 for the default animation, 0 for no
     *                         animation, or a resource identifier for an explicit animation.
     * @param ChangeBackground 是否改变背景透明度
     * @param Dismiss          点击窗口外是否消失
     * @return
     */
    public static View showAdaptive(Activity context, View view, int layout, int gravity, int xOffset,
                                    int yOffset, int animationStyle, boolean ChangeBackground, boolean Dismiss) {
        int xOffsetNew = ViewUtil.scaleValue(context, xOffset);
        int yOffsetNew = ViewUtil.scaleValue(context, yOffset);
        PopupWindowUtile.context = context;
        View popupWindow_view = LayoutInflater.from(context).inflate(
                layout, null);
        // 设置popupWindow的宽度和高度
        popupWindow = new PopupWindow(popupWindow_view, 0, 0, true);
        popupWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);

        // 弹出弹窗口背景变黑
        final WindowManager.LayoutParams params = context.getWindow().getAttributes();
        if (ChangeBackground) {
            params.alpha = 0.4f;
            context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            context.getWindow().setAttributes(params);
        }

        // 弹出动画
        popupWindow.setAnimationStyle(animationStyle);

        // 这样做是为了让返回键能有响应
        popupWindow_view.setFocusableInTouchMode(true);
        popupWindow_view.setOnKeyListener((v, keyCode, event) -> {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        PopupWindowUtile.closePopupWindow();
                        return true;
                    }
                    return false;
                }
        );

        // 弹窗消失的监听
        popupWindow.setOnDismissListener(() -> {
            if (ChangeBackground) {
                params.alpha = 1f;
                context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                context.getWindow().setAttributes(params);
            }
        });

        // 点击其他地方消失
        popupWindow_view.setOnTouchListener((v, event) -> {
            int y = (int) event.getY();
            int x = (int) event.getX();
            int top = popupWindow_view.getTop();
            int bottom = popupWindow_view.getBottom();
            int left = popupWindow_view.getLeft();
            int right = popupWindow_view.getRight();
            switch (event.getAction()) {
                case MotionEvent.ACTION_UP:
                    if (y < top || y > bottom || x < left || x > right) {
                        if (Dismiss)
                            PopupWindowUtile.closePopupWindow();
                        break;
                    }
            }
            return false;
        });
        popupWindow.showAtLocation(view, gravity, xOffsetNew, yOffsetNew);
        return popupWindow_view;
    }

    /**
     * 弹出一个在指定View的指定位置的PopupWindow
     *
     * @param context
     * @param view
     * @param layout
     * @param relativeLocation
     * @param ChangeBackground
     * @param Dismiss
     * @return
     */
    public static View showAdaptive2(Activity context, View view, int layout, int relativeLocation,
                                     boolean ChangeBackground, boolean Dismiss) {
        return showAdaptive2(context, view, layout, relativeLocation,
                0, 0, ChangeBackground, Dismiss);
    }

    /**
     * 弹出一个在指定View的指定位置的PopupWindow
     *
     * @param context
     * @param view
     * @param layout
     * @param relativeLocation 相对位置(参见本类静态常量)
     * @param xOffset          X轴偏移量
     * @param yOffset          Y轴偏移量
     * @param ChangeBackground 是否改变背景
     * @param Dismiss          点击外部是否消失
     * @return
     */
    public static View showAdaptive2(Activity context, View view, int layout, int relativeLocation,
                                     int xOffset, int yOffset, boolean ChangeBackground, boolean Dismiss) {

        PopupWindowUtile.context = context;
        View popupWindow_view = LayoutInflater.from(context).inflate(
                layout, null);
        // 设置popupWindow的宽度和高度
        popupWindow = new PopupWindow(popupWindow_view, 0, 0, true);
        popupWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        final WindowManager.LayoutParams params = context.getWindow().getAttributes();
        if (ChangeBackground) {
            // 弹出弹窗口背景变黑
            params.alpha = 0.4f;
            context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            context.getWindow().setAttributes(params);
        }
        // 弹出动画
        setAnimation(relativeLocation);
        // 这样做是为了让返回键能有响应
        popupWindow_view.setFocusableInTouchMode(true);
        popupWindow_view.setOnKeyListener((v, keyCode, event) -> {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        PopupWindowUtile.closePopupWindow();
                        return true;
                    }
                    return false;
                }
        );
        // 弹窗消失的监听
        popupWindow.setOnDismissListener(() -> {
            if (ChangeBackground) {
                params.alpha = 1f;
                context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                context.getWindow().setAttributes(params);
            }
        });
        // 点击其他地方消失
        popupWindow_view.setOnTouchListener((v, event) -> {
            int y = (int) event.getY();
            int x = (int) event.getX();
            int top = popupWindow_view.getTop();
            int bottom = popupWindow_view.getBottom();
            int left = popupWindow_view.getLeft();
            int right = popupWindow_view.getRight();
            switch (event.getAction()) {
                case MotionEvent.ACTION_UP:
                    if (y < top || y > bottom || x < left || x > right) {
                        if (Dismiss)
                            PopupWindowUtile.closePopupWindow();
                        break;
                    }
            }
            return false;
        });
        // 弹出PopupWindow
        showPopupWindow(view, popupWindow_view, relativeLocation, xOffset, yOffset);
        return popupWindow_view;
    }

    /**
     * 不同位置设置不同的动画
     *
     * @param relativeLocation
     */
    private static void setAnimation(int relativeLocation) {
        switch (relativeLocation) {
            case BOTTOM:
                popupWindow.setAnimationStyle(R.style.AnimationZoom);
                break;
            case BOTTOM_LEFT_ALIGN:
                popupWindow.setAnimationStyle(R.style.AnimationZoom1);
                break;
            case BOTTOM_RIGHT_ALIGN:
                popupWindow.setAnimationStyle(R.style.AnimationZoom2);
                break;
            case LEFT:
                popupWindow.setAnimationStyle(R.style.AnimationZoom3);
                break;
            case LEFT_TOP_ALIGN:
                popupWindow.setAnimationStyle(R.style.AnimationZoom2);
                break;
            case LEFT_BOTTOM_ALIGN:
                popupWindow.setAnimationStyle(R.style.AnimationZoom4);
                break;
            case RIGHT:
                popupWindow.setAnimationStyle(R.style.AnimationZoom5);
                break;
            case RIGHT_TOP_ALIGN:
                popupWindow.setAnimationStyle(R.style.AnimationZoom1);
                break;
            case RIGHT_BOTTOM_ALIGN:
                popupWindow.setAnimationStyle(R.style.AnimationZoom6);
                break;
            case TOP:
                popupWindow.setAnimationStyle(R.style.AnimationZoom7);
                break;
            case TOP_LEFT_ALIGN:
                popupWindow.setAnimationStyle(R.style.AnimationZoom8);
                break;
            case TOP_RIGHT_ALIGN:
                popupWindow.setAnimationStyle(R.style.AnimationZoom9);
                break;
        }
    }

    /**
     * popupWindow的位置指定
     *
     * @param view
     * @param popupWindow_view
     * @param relativeLocation
     */
    private static void showPopupWindow(View view, View popupWindow_view, int relativeLocation,
                                        int x, int y) {
        // 这个是为了更好的适配偏移量
        int xOffset = ViewUtil.scaleValue(context, x);
        int yOffset = ViewUtil.scaleValue(context, y);
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        ViewUtil.measureView(popupWindow_view);
        switch (relativeLocation) {
            case BOTTOM:
                popupWindow.showAtLocation(view, Gravity.NO_GRAVITY,
                        (location[0] + view.getWidth() / 2) - popupWindow_view.getMeasuredWidth() / 2 + xOffset,
                        location[1] + view.getHeight() + yOffset);
                break;
            case BOTTOM_LEFT_ALIGN:
                popupWindow.showAtLocation(view, Gravity.NO_GRAVITY,
                        location[0] + xOffset,
                        location[1] + view.getHeight() + yOffset);
                break;
            case BOTTOM_RIGHT_ALIGN:
                popupWindow.showAtLocation(view, Gravity.NO_GRAVITY,
                        location[0] + view.getWidth() - popupWindow_view.getMeasuredWidth() + xOffset,
                        location[1] + view.getHeight() + yOffset);
                break;
            case LEFT:
                popupWindow.showAtLocation(view, Gravity.NO_GRAVITY,
                        location[0] - popupWindow_view.getMeasuredWidth() + xOffset,
                        (location[1] + view.getHeight() / 2) - popupWindow_view.getMeasuredHeight() / 2 + yOffset);
                break;
            case LEFT_TOP_ALIGN:
                popupWindow.showAtLocation(view, Gravity.NO_GRAVITY,
                        location[0] - popupWindow_view.getMeasuredWidth() + xOffset,
                        location[1] + yOffset);
                break;
            case LEFT_BOTTOM_ALIGN:
                popupWindow.showAtLocation(view, Gravity.NO_GRAVITY,
                        location[0] - popupWindow_view.getMeasuredWidth() + xOffset,
                        location[1] + view.getHeight() - popupWindow_view.getMeasuredHeight() + yOffset);
                break;
            case RIGHT:
                popupWindow.showAtLocation(view, Gravity.NO_GRAVITY,
                        location[0] + view.getWidth() + xOffset,
                        (location[1] + view.getHeight() / 2) - popupWindow_view.getMeasuredHeight() / 2 + yOffset);
                break;
            case RIGHT_TOP_ALIGN:
                popupWindow.showAtLocation(view, Gravity.NO_GRAVITY,
                        location[0] + view.getWidth() + xOffset,
                        location[1] + yOffset);
                break;
            case RIGHT_BOTTOM_ALIGN:
                popupWindow.showAtLocation(view, Gravity.NO_GRAVITY,
                        location[0] + view.getWidth() + xOffset,
                        location[1] + view.getHeight() - popupWindow_view.getMeasuredHeight() + yOffset);
                break;
            case TOP:
                popupWindow.showAtLocation(view, Gravity.NO_GRAVITY,
                        (location[0] + view.getWidth() / 2) - popupWindow_view.getMeasuredWidth() / 2 + xOffset,
                        location[1] - popupWindow_view.getMeasuredHeight() + yOffset);
                break;
            case TOP_LEFT_ALIGN:
                popupWindow.showAtLocation(view, Gravity.NO_GRAVITY,
                        location[0] + xOffset,
                        location[1] - popupWindow_view.getMeasuredHeight() + yOffset);
                break;
            case TOP_RIGHT_ALIGN:
                popupWindow.showAtLocation(view, Gravity.NO_GRAVITY,
                        location[0] + view.getWidth() - popupWindow_view.getMeasuredWidth() + xOffset,
                        location[1] - popupWindow_view.getMeasuredHeight() + yOffset);
                break;
        }
    }

    /**
     * 一个自定义高度和宽度的
     *
     * @param context
     * @param view
     * @param layout
     * @param width
     * @param height
     * @param gravity
     * @param xOffset
     * @param yOffset
     * @param animationStyle
     * @param ChangeBackground
     * @param Dismiss
     * @return
     */
    public static View showPopupWindow(Activity context, View view, int layout, int width, int height, int gravity, int xOffset,
                                       int yOffset, int animationStyle, boolean ChangeBackground, boolean Dismiss) {
        int xOffsetNew = ViewUtil.scaleValue(context, xOffset);
        int yOffsetNew = ViewUtil.scaleValue(context, yOffset);
        PopupWindowUtile.context = context;
        View popupWindow_view = LayoutInflater.from(context).inflate(
                layout, null);
        // 设置popupWindow的宽度和高度
        popupWindow = new PopupWindow(popupWindow_view, 0, 0, true);
        popupWindow.setWidth(width);
        popupWindow.setHeight(height);

        // 弹出弹窗口背景变黑
        final WindowManager.LayoutParams params = context.getWindow().getAttributes();
        if (ChangeBackground) {
            params.alpha = 0.4f;
            context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            context.getWindow().setAttributes(params);
        }

        // 弹出动画
        popupWindow.setAnimationStyle(animationStyle);

        // 这样做是为了让返回键能有响应
        popupWindow_view.setFocusableInTouchMode(true);
        popupWindow_view.setOnKeyListener((v, keyCode, event) -> {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        PopupWindowUtile.closePopupWindow();
                        return true;
                    }
                    return false;
                }
        );

        // 弹窗消失的监听
        popupWindow.setOnDismissListener(() -> {
            if (ChangeBackground) {
                params.alpha = 1f;
                context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                context.getWindow().setAttributes(params);
            }
        });

        // 点击其他地方消失
        popupWindow_view.setOnTouchListener((v, event) -> {
            int y = (int) event.getY();
            int x = (int) event.getX();
            int top = popupWindow_view.getTop();
            int bottom = popupWindow_view.getBottom();
            int left = popupWindow_view.getLeft();
            int right = popupWindow_view.getRight();
            switch (event.getAction()) {
                case MotionEvent.ACTION_UP:
                    if (y < top || y > bottom || x < left || x > right) {
                        if (Dismiss)
                            PopupWindowUtile.closePopupWindow();
                        break;
                    }
            }
            return false;
        });
//        popupWindow.showAtLocation(view, gravity, xOffsetNew, yOffsetNew);

        int[] location = new int[2];
        view.getLocationOnScreen(location);
        ViewUtil.measureView(popupWindow_view);
        switch (gravity) {
            case BOTTOM:
                popupWindow.showAtLocation(view, Gravity.NO_GRAVITY,
                        (location[0] + view.getWidth() / 2) - popupWindow_view.getMeasuredWidth() / 2 + xOffsetNew,
                        location[1] + view.getHeight() + yOffset);
                break;
            case BOTTOM_LEFT_ALIGN:
                popupWindow.showAtLocation(view, Gravity.NO_GRAVITY,
                        location[0] + xOffsetNew,
                        location[1] + view.getHeight() + yOffset);
                break;
            case BOTTOM_RIGHT_ALIGN:
                popupWindow.showAtLocation(view, Gravity.NO_GRAVITY,
                        location[0] + view.getWidth() - popupWindow_view.getMeasuredWidth() + xOffsetNew,
                        location[1] + view.getHeight() + yOffset);
                break;
            case LEFT:
                popupWindow.showAtLocation(view, Gravity.NO_GRAVITY,
                        location[0] - popupWindow_view.getMeasuredWidth() + xOffsetNew,
                        (location[1] + view.getHeight() / 2) - popupWindow_view.getMeasuredHeight() / 2 + yOffset);
                break;
            case LEFT_TOP_ALIGN:
                popupWindow.showAtLocation(view, Gravity.NO_GRAVITY,
                        location[0] - popupWindow_view.getMeasuredWidth() + xOffsetNew,
                        location[1] + yOffset);
                break;
            case LEFT_BOTTOM_ALIGN:
                popupWindow.showAtLocation(view, Gravity.NO_GRAVITY,
                        location[0] - popupWindow_view.getMeasuredWidth() + xOffsetNew,
                        location[1] + view.getHeight() - popupWindow_view.getMeasuredHeight() + yOffset);
                break;
            case RIGHT:
                popupWindow.showAtLocation(view, Gravity.NO_GRAVITY,
                        location[0] + view.getWidth() + xOffsetNew,
                        (location[1] + view.getHeight() / 2) - popupWindow_view.getMeasuredHeight() / 2 + yOffset);
                break;
            case RIGHT_TOP_ALIGN:
                popupWindow.showAtLocation(view, Gravity.NO_GRAVITY,
                        location[0] + view.getWidth() + xOffset,
                        location[1] + yOffset);
                break;
            case RIGHT_BOTTOM_ALIGN:
                popupWindow.showAtLocation(view, Gravity.NO_GRAVITY,
                        location[0] + view.getWidth() + xOffsetNew,
                        location[1] + view.getHeight() - popupWindow_view.getMeasuredHeight() + yOffset);
                break;
            case TOP:
                popupWindow.showAtLocation(view, Gravity.NO_GRAVITY,
                        (location[0] + view.getWidth() / 2) - popupWindow_view.getMeasuredWidth() / 2 + xOffsetNew,
                        location[1] - popupWindow_view.getMeasuredHeight() + yOffset);
                break;
            case TOP_LEFT_ALIGN:
                popupWindow.showAtLocation(view, Gravity.NO_GRAVITY,
                        location[0] + xOffsetNew,
                        location[1] - popupWindow_view.getMeasuredHeight() + yOffset);
                break;
            case TOP_RIGHT_ALIGN:
                popupWindow.showAtLocation(view, Gravity.NO_GRAVITY,
                        location[0] + view.getWidth() - popupWindow_view.getMeasuredWidth() + xOffsetNew,
                        location[1] - popupWindow_view.getMeasuredHeight() + yOffset);
                break;
        }
        return popupWindow_view;
    }

    public static boolean closePopupWindow() {
        Log.d(TAG, "closePopupWindow");
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
            popupWindow = null;
            return true;
        }
        return false;
    }


    public static View showAdaptive3(Activity context, View view, View bg, int layout, int relativeLocation,
                                     boolean ChangeBackground, boolean Dismiss) {

        PopupWindowUtile.context = context;
        View popupWindow_view = LayoutInflater.from(context).inflate(
                layout, null);
        // 设置popupWindow的宽度和高度
        popupWindow = new PopupWindow(popupWindow_view, 0, 0, true);
        float width = UiUtil.getScreenWidth(context);
        popupWindow.setWidth((int) width);
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        final WindowManager.LayoutParams params = context.getWindow().getAttributes();
        if (ChangeBackground) {
            // 弹出弹窗口背景变黑
            params.alpha = 0.4f;
            context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            context.getWindow().setAttributes(params);
        }
        // 弹出动画
        setAnimation(relativeLocation);
        // 这样做是为了让返回键能有响应
        popupWindow_view.setFocusableInTouchMode(true);
        popupWindow_view.setOnKeyListener((v, keyCode, event) -> {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        PopupWindowUtile.closePopupWindow();
                        return true;
                    }
                    return false;
                }
        );
        // 弹窗消失的监听
        popupWindow.setOnDismissListener(() -> {
            if (bg != null) {
                bg.setVisibility(View.GONE);
            }
            if (ChangeBackground) {
                params.alpha = 1f;
                context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                context.getWindow().setAttributes(params);
            }
        });
        // 点击其他地方消失
        popupWindow_view.setOnTouchListener((v, event) -> {
            int y = (int) event.getY();
            int x = (int) event.getX();
            int top = popupWindow_view.getTop();
            int bottom = popupWindow_view.getBottom();
            int left = popupWindow_view.getLeft();
            int right = popupWindow_view.getRight();
            switch (event.getAction()) {
                case MotionEvent.ACTION_UP:
                    if (y < top || y > bottom || x < left || x > right) {
                        if (Dismiss)
                            PopupWindowUtile.closePopupWindow();
                        break;
                    }
            }
            return false;
        });
        // 弹出PopupWindow
        popupWindow.showAsDropDown(view);
        bg.setVisibility(View.VISIBLE);
        return popupWindow_view;
    }
}
