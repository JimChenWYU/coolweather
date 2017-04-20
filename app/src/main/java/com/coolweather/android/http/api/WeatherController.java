package com.coolweather.android.http.api;

import com.coolweather.android.http.BaseBingPicResponse;
import com.coolweather.android.http.BaseScenicDetailResponse;
import com.coolweather.android.http.BaseScenicWeatherResponse;
import com.coolweather.android.http.BaseWeatherResponse;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by lenovo on 2017/4/11.
 */

public interface WeatherController {

    @GET(ApiGlobal.HE_WEATHER_BASE_URL)
    Observable<BaseWeatherResponse> getWeather(@Query("city") String city);

    @GET(ApiGlobal.HE_SCENIC_WEATHER_URL + "{page}/")
    Observable<BaseScenicWeatherResponse> getScenics(@Path("page") int page);
    @GET(ApiGlobal.HE_SCENIC_WEATHER_URL + "{page}/")
    Observable<BaseScenicWeatherResponse> getScenics(@Path("page") int page, @QueryMap Map<String, String> options);


    @GET(ApiGlobal.HE_SCENIC_DETAIL_WEATHER_URL)
    Observable<BaseScenicDetailResponse> getScenicDetailWeather(@Query("city") String city);

    @GET(ApiGlobal.BING_PIC_URL)
    Observable<BaseBingPicResponse> getBingPic();
}
