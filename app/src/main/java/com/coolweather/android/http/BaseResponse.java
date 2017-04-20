package com.coolweather.android.http;

/**
 * Created by lenovo on 2017/4/12.
 */

public abstract class BaseResponse<T> {
    public String status;

    public String msg;

    public T data;
}
