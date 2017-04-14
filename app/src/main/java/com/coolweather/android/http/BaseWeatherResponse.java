package com.coolweather.android.http;

import com.coolweather.android.model.HeWeather5;

import java.util.List;

/**
 * Created by lenovo on 2017/4/11.
 */

public class BaseWeatherResponse extends BaseResponse<HeWeather5.DataBean> {

    @Override
    public String toString() {
        return "BaseWeatherResponse{" +
                "status='" + status + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data.toString() +
                '}';
    }
}
