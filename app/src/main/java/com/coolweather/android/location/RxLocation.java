package com.coolweather.android.location;

import android.Manifest;
import android.app.Activity;
import android.content.Context;

import com.baidu.location.BDLocation;
import com.tbruyelle.rxpermissions.RxPermissions;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by lenovo on 2017/4/11.
 */

public class RxLocation {
    private volatile static RxLocation instance = null;

    private RxLocation() {}

    public static RxLocation get() {
        if (instance == null) {
            synchronized (RxLocation.class) {
                if (instance == null) {
                    instance = new RxLocation();
                }
            }
        }
        return instance;
    }

    public Observable<BDLocation> locate(final Activity context) {
        return new RxPermissions(context)
                .request(Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.READ_PHONE_STATE)
                .flatMap(new Func1<Boolean, Observable<BDLocation>>() {
                    @Override
                    public Observable<BDLocation> call(Boolean aBoolean) {
                        return Observable.create(new LocationOnSubscribe(context));
                    }
                });
    }

    public Observable<BDLocation> locateLastKnow(final Context context) {
        return Observable.create(new LocationLastKnownOnSubscribe(context));
    }
}
