package com.global.ustewardUtil;

import android.app.Activity;
import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.text.InputType;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.global.R;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 自定义键盘工具类
 * <p>
 * Created by Guangkuo on 2017/6/26.
 */
public class KeyboardUtil {
    private Context mContext;
    private KeyboardView mKeyboardView;
    private EditText[] mEdits;
    private int currentEditText = 0;//默认当前光标在第一个EditText
    /**
     * 省份简称键盘
     */
    private Keyboard province_keyboard;
    /**
     * 数字与大写字母键盘
     */
    private Keyboard number_keyboar;
    /**
     * 大写字母键盘无数字
     */
    private Keyboard letters_no_number;

    public KeyboardUtil(Context context, EditText[] edits) {
        mContext = context;
        mEdits = edits;
        province_keyboard = new Keyboard(mContext, R.xml.province_abbreviation);
        number_keyboar = new Keyboard(mContext, R.xml.number_or_letters);
        letters_no_number = new Keyboard(mContext, R.xml.letters_no_number);
        mKeyboardView = ((Activity) context).findViewById(R.id.keyboard_view);
        mKeyboardView.setKeyboard(province_keyboard);
        mKeyboardView.setEnabled(true);
        mKeyboardView.setPreviewEnabled(false);
        mKeyboardView.setOnKeyboardActionListener(listener);
    }

    private KeyboardView.OnKeyboardActionListener listener = new KeyboardView.OnKeyboardActionListener() {
        @Override
        public void swipeUp() {
        }

        @Override
        public void swipeRight() {
        }

        @Override
        public void swipeLeft() {
        }

        @Override
        public void swipeDown() {
        }

        @Override
        public void onText(CharSequence text) {
        }

        @Override
        public void onRelease(int primaryCode) {
        }

        @Override
        public void onPress(int primaryCode) {
        }

        @Override
        public void onKey(int primaryCode, int[] keyCodes) {
            if (primaryCode == -3) {// 回退
                mEdits[currentEditText].setText("");//将当前EditText置为""并currentEditText-1
                currentEditText--;
                if (currentEditText < 1) {
                    // 切换为省份简称键盘
                    mKeyboardView.setKeyboard(province_keyboard);
                } else if (currentEditText == 1) {
                    // 切换为纯大写字母键盘
                    mKeyboardView.setKeyboard(letters_no_number);
                }
                if (currentEditText < 0) {
                    currentEditText = 0;
                }
            } else {
                if (currentEditText == 0) {
                    // 如果currentEditText==0代表当前为省份键盘,
                    // 按下一个按键后,设置相应的EditText的值
                    // 然后切换为字母数字键盘 currentEditText + 1
                    mEdits[0].setText(Character.toString((char) primaryCode));
                    currentEditText = 1;
                    // 切换为字母数字键盘
                    mKeyboardView.setKeyboard(letters_no_number);
                } else if (currentEditText == 1) {
                    mEdits[1].setText(Character.toString((char) primaryCode));
                    currentEditText = 2;
                    // 切换为字母数字键盘
                    mKeyboardView.setKeyboard(number_keyboar);
                } else {
                    // 第二位必须大写字母
                    mEdits[currentEditText].setText(Character.toString((char) primaryCode));
                    currentEditText++;
                    if (mEdits[mEdits.length - 1].isShown()) {
                        if (currentEditText > 7)
                            currentEditText = 7;
                    } else {
                        if (currentEditText > 6)
                            currentEditText = 6;
                    }
                }
            }
            mEdits[currentEditText].requestFocus();
        }
    };

    /**
     * 指定切换软键盘
     */
    public void changeKeyboard(int change) {
        if (change == 0) {
            mKeyboardView.setKeyboard(province_keyboard);
        } else if (change == 1) {
            mKeyboardView.setKeyboard(letters_no_number);
        } else {
            mKeyboardView.setKeyboard(number_keyboar);
        }
    }

    /**
     * 软键盘展示状态
     */
    public boolean isShow() {
        return mKeyboardView.getVisibility() == View.VISIBLE;
    }

    /**
     * 软键盘展示
     */
    public void showKeyboard() {
        int visibility = mKeyboardView.getVisibility();
        if (visibility == View.GONE || visibility == View.INVISIBLE) {
            mKeyboardView.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 软键盘隐藏
     */
    public void hideKeyboard() {
        int visibility = mKeyboardView.getVisibility();
        if (visibility == View.VISIBLE) {
            mKeyboardView.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * 禁掉系统软键盘
     */
    public void hideSoftInputMethod(EditText editText) {
        ((Activity) mContext).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        int currentVersion = android.os.Build.VERSION.SDK_INT;
        String methodName = null;
        if (currentVersion >= 16) {
            // 4.2
            methodName = "setShowSoftInputOnFocus";
        } else if (currentVersion >= 14) {
            // 4.0
            methodName = "setSoftInputShownOnFocus";
        }
        if (methodName == null) {
            editText.setInputType(InputType.TYPE_NULL);
        } else {
            Class<EditText> cls = EditText.class;
            Method setShowSoftInputOnFocus;
            try {
                setShowSoftInputOnFocus = cls.getMethod(methodName, boolean.class);
                setShowSoftInputOnFocus.setAccessible(true);
                setShowSoftInputOnFocus.invoke(editText, false);
            } catch (NoSuchMethodException e) {
                editText.setInputType(InputType.TYPE_NULL);
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    public void setCurrentEditText(int currentEditText) {
        this.currentEditText = currentEditText;
    }
}
