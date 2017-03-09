package com.coolweather.android;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.coolweather.android.util.ActivityController;
import com.coolweather.android.util.Utils;

/**
 * Created by lenovo on 2017/3/8.
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize();
        ActivityController.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityController.removeActivity(this);
    }

    private void initialize() {
        Utils.getEnvConfig(this).putValue("key", "ab5fd7f9c85d41a3a7d191e2eb5d2af7", false);
    }
}
