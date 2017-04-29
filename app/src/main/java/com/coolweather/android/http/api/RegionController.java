package com.coolweather.android.http.api;

import com.coolweather.android.http.BaseCityResponse;
import com.coolweather.android.http.BaseCountyResponse;
import com.coolweather.android.http.BaseProvinceResponse;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by lenovo on 2017/4/13.
 */

public interface RegionController {
    @GET(ApiGlobal.REGION_URL)
    Observable<BaseProvinceResponse> getProvince();

    @GET(ApiGlobal.REGION_URL + "{provinceCode}")
    Observable<BaseCityResponse> getCity(@Path("provinceCode") int provinceCode);

    @GET(ApiGlobal.REGION_URL + "{provinceCode}/{cityCode}/")
    Observable<BaseCountyResponse> getCounty(@Path("provinceCode") int provinceCode, @Path("cityCode") int cityCode);
}
