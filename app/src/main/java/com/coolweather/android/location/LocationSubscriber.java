package com.coolweather.android.location;

import android.support.annotation.Nullable;

import com.baidu.location.BDLocation;

import java.util.logging.Logger;

import rx.Subscriber;

/**
 * Created by lenovo on 2017/4/11.
 */

public abstract class LocationSubscriber extends Subscriber<BDLocation> {

    @Override
    public void onCompleted() {
        onLocatedCompleted();
    }

    @Override
    public void onError(Throwable e) {
        onLocatedFail(null, e);
    }

    @Override
    public void onNext(BDLocation bdLocation) {
        if (bdLocation != null) {
            onLocatedSuccess(bdLocation);
        } else {
            onLocatedFail(null, null);
        }
    }

    protected abstract void onLocatedSuccess(@Nullable BDLocation bdLocation);

    protected abstract void onLocatedFail(BDLocation bdLocation, Throwable e);

    protected abstract void onLocatedCompleted();
}
