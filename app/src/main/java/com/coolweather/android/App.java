package com.coolweather.android;

import android.app.Application;
import android.content.Context;

import com.coolweather.android.utils.Logger;

import org.litepal.LitePal;

/**
 * Created by lenovo on 2017/4/11.
 */

public class App extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        initialize();
    }

    private void initialize() {
        mContext = getApplicationContext();
        LitePal.initialize(mContext);
        if (!BuildConfig.DEBUG) {
            Logger.setLevel(Logger.NOTHING);
        }
    }

    public static Context getContext() {
        return mContext;
    }
}
