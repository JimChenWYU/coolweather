package com.coolweather.android.http.api;

import com.coolweather.android.http.BaseBingPicResponse;
import com.coolweather.android.http.BaseWeatherResponse;
import com.coolweather.android.model.HeWeather5;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by lenovo on 2017/4/11.
 */

public interface WeatherController {

    @GET(ApiGlobal.HE_WEATHER_BASE_URL)
    Observable<BaseWeatherResponse> getWeather(@Query("city") String city);

    @GET(ApiGlobal.BING_PIC_URL)
    Observable<BaseBingPicResponse> getBingPic();
}
