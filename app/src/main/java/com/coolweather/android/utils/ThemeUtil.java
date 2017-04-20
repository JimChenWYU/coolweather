package com.coolweather.android.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.TypedValue;

import com.coolweather.android.R;

/**
 * 多主题切换工具
 * Created by lenovo on 2017/4/11.
 */

public class ThemeUtil {

    public static int getThemeColor(Context context, int attrRes) {
        TypedArray typedArray = context.obtainStyledAttributes(new int[]{attrRes});
        int color = typedArray.getColor(0, 0xffffff);
        typedArray.recycle();
        return color;
    }

    public static int getCurrentColorPrimary(Context context) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
        return typedValue.resourceId;
    }
}
