package com.coolweather.android.even;

/**
 * Created by lenovo on 2017/4/20.
 */

public class ThemeChangedEvent {
    private int themeColor;

    public ThemeChangedEvent(int themeColor) {
        this.themeColor = themeColor;
    }

    public int getThemeColor() {
        return themeColor;
    }
}
