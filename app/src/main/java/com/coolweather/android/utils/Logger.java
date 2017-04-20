package com.coolweather.android.utils;

import android.support.annotation.IntDef;
import android.util.Log;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by lenovo on 2017/4/11.
 */

public class Logger {

    @Level
    private static int level;

    /**
     * Priority constant for the println method; use Log.v.
     */
    public static final int VERBOSE = 2;

    /**
     * Priority constant for the println method; use Log.d.
     */
    public static final int DEBUG = 3;

    /**
     * Priority constant for the println method; use Log.i.
     */
    public static final int INFO = 4;

    /**
     * Priority constant for the println method; use Log.w.
     */
    public static final int WARN = 5;

    /**
     * Priority constant for the println method; use Log.e.
     */
    public static final int ERROR = 6;

    public static final int NOTHING = 7;

    /**
     * 限定level范围
     */
    @IntDef({VERBOSE, DEBUG, INFO, WARN, ERROR, NOTHING})
    @Retention(RetentionPolicy.SOURCE)
    private @interface Level{
        int value() default VERBOSE;
    }

    public static void setLevel(@Level int level) {
        Logger.level = level;
    }

    public static void v(String tag, String msg) {
        if (level <= VERBOSE) {
            Log.v(tag, msg);
        }
    }

    public static <T> void d(String tag, T msg) {
        if (level <= DEBUG) {
            Log.d(tag, String.valueOf(msg));
        }
    }

    public static <T> void i(String tag, T msg) {
        if (level <= INFO) {
            Log.i(tag, String.valueOf(msg));
        }
    }

    public static <T> void w(String tag, T msg) {
        if (level <= WARN) {
            Log.w(tag, String.valueOf(msg));
        }
    }

    public static <T> void e(String tag, T msg) {
        if (level <= ERROR) {
            Log.e(tag, String.valueOf(msg));
        }
    }
}
