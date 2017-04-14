package com.coolweather.android.location;

import android.content.Context;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by lenovo on 2017/4/11.
 */

public class LocationOnSubscribe implements Observable.OnSubscribe<BDLocation> {
    private final Context context;

    public LocationOnSubscribe(Context context) {
        this.context = context;
    }

    @Override
    public void call(final Subscriber<? super BDLocation> subscriber) {
        BDLocationListener bdLocationListener = new BDLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation bdLocation) {
                subscriber.onNext(bdLocation);
                subscriber.onCompleted();
            }

            @Override
            public void onConnectHotSpotMessage(String s, int i) {
            }
        };
        LocationClient.get(context).locate(bdLocationListener);
    }
}
