package com.coolweather.android.http;

import com.coolweather.android.model.ScenicLineBean;

import java.util.List;

/**
 * Created by lenovo on 2017/4/15.
 */

public class BaseScenicWeatherResponse extends BaseResponse<List<ScenicLineBean>> {

    public int total;

    @Override
    public String toString() {
        return "BaseResponse{" +
                "status='" + status + '\'' +
                ", msg='" + msg + '\'' +
                ", total=" + total +
                ", data=" + data.toString() +
                '}';
    }
}
