package com.coolweather.android;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.coolweather.android.util.Utils;

/**
 * Created by lenovo on 2017/3/8.
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize();
    }

    private void initialize() {
        Utils.getEnvConfig(this).putValue("key", "ab5fd7f9c85d41a3a7d191e2eb5d2af7", true);
    }
}
