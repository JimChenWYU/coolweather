package com.coolweather.android.http;

import com.coolweather.android.http.api.GirlsController;
import com.coolweather.android.http.api.RegionController;
import com.coolweather.android.http.api.WeatherController;

/**
 * Created by lenovo on 2017/4/11.
 */

public class ApiFactory {
    protected static final Object monitor = new Object();

    private volatile static WeatherController weatherController;

    private volatile static RegionController regionController;

    private volatile static GirlsController girlsController;

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

    public static GirlsController getGirlsController() {
        if (girlsController == null) {
            synchronized (monitor) {
                girlsController = RetrofitManager.getInstance().create(GirlsController.class);
            }
        }
        return girlsController;
    }
}
