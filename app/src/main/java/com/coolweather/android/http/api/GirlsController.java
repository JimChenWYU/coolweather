package com.coolweather.android.http.api;

import com.coolweather.android.http.BaseGirlsGankResponse;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by lenovo on 2017/4/20.
 */

public interface GirlsController {
    @GET(ApiGlobal.GANK_GIRLS)
    Observable<BaseGirlsGankResponse> getGirlsGank();
}
