package com.coolweather.android.utils;

import android.content.Context;
import android.util.TypedValue;

import com.coolweather.android.R;

/**
 * 多主题切换工具
 * Created by lenovo on 2017/4/11.
 */

public class ThemeUtil {
    public static int getCurrentColorPrimary(Context context) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
        return typedValue.resourceId;
    }
}
