package com.coolweather.android.http;

import com.coolweather.android.http.api.RegionController;
import com.coolweather.android.http.api.WeatherController;

/**
 * Created by lenovo on 2017/4/11.
 */

public class ApiFactory {
    protected static final Object monitor = new Object();

    private static WeatherController weatherController;

    private static RegionController regionController;

    public static WeatherController getWeatherController() {
        if (weatherController == null) {
            synchronized (monitor) {
                weatherController = RetrofitManager.getInstance().create(WeatherController.class);
            }
        }
        return weatherController;
    }

    public static RegionController getRegionController() {
        if (regionController == null) {
            synchronized (monitor) {
                regionController = RetrofitManager.getInstance().create(RegionController.class);
            }
        }
        return regionController;
    }
}
