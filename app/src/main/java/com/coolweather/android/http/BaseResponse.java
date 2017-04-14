package com.coolweather.android.http;

import com.coolweather.android.model.HeWeather5;

/**
 * Created by lenovo on 2017/4/12.
 */

public abstract class BaseResponse<T> {
    public String status;

    public String msg;

    public T data;
}
