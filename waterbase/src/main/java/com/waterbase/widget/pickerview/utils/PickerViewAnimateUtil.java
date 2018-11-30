package com.waterbase.widget.pickerview.utils;

import android.view.Gravity;

import com.waterbase.R;


/**
 * Created by Sai on 15/8/9.
 */
public class PickerViewAnimateUtil {
    private static final int INVALID = -1;
    /**
     * Get default animation resource when not defined by the user
     *
     * @param gravity       the gravity of the dialog
     * @param isInAnimation determine if is in or out animation. true when is is
     * @return the id of the animation resource
     */
    public static int getAnimationResource(int gravity, boolean isInAnimation) {
        switch (gravity) {
            case Gravity.BOTTOM:
                return isInAnimation ? R.anim.dialog_in_bottom : R.anim.dialog_out_bottom;
            case Gravity.CENTER:
                return isInAnimation ? R.anim.fade_in0 : R.anim.fade_out0;
        }
        return INVALID;
    }
}
