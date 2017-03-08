package com.coolweather.android.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by lenovo on 2017/3/8.
 */

public class Utils {

    private static volatile EnvConfig envConfig;

    public static EnvConfig getEnvConfig(Context context) {
        if (envConfig == null) {
            synchronized (EnvConfig.class) {
                if (envConfig == null) {
                    envConfig = new EnvConfig(context);
                }
            }
        }
        return envConfig;
    }

    public static class EnvConfig {
        private SharedPreferences sharedPreferences;

        private SharedPreferences.Editor editor;

        private Context context;

        public EnvConfig(Context context) {
            this.context = context;
            sharedPreferences = context.getSharedPreferences(getClass().getName(), 0);
            editor = sharedPreferences.edit();
        }

        public boolean putValue(String key, String value, boolean replace) {
            if (editor == null) {
                editor = sharedPreferences.edit();
            }

            if (replace) {
                editor.putString(key, value);
                return editor.commit();
            } else {
                String _value = sharedPreferences.getString(key, null);
                if (_value == null) {
                    editor.putString(key, value);
                    return editor.commit();
                }
                return false;
            }
        }

        public String getValue(String key) {
            return sharedPreferences.getString(key, null);
        }
    }
}
