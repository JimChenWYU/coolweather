package com.coolweather.android.location;

import android.content.Context;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClientOption;

/**
 * Created by lenovo on 2017/4/11.
 */

public class LocationClient {
    private com.baidu.location.LocationClient realClient;

    private static volatile LocationClient proxyClient;

    private LocationClient(Context context) {
        realClient = new com.baidu.location.LocationClient(context);
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setCoorType("bd09ll");
        option.setIsNeedAddress(true);
        realClient.setLocOption(option);
    }

    public static LocationClient get(Context context) {
        if (proxyClient == null) {
            synchronized (com.baidu.location.LocationClient.class) {
                if (proxyClient == null) {
                    proxyClient = new LocationClient(context.getApplicationContext());
                }
            }
        }
        return proxyClient;
    }

    public void locate(final BDLocationListener bdLocationListener) {
        final BDLocationListener realListener = new BDLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation bdLocation) {
                bdLocationListener.onReceiveLocation(bdLocation);

                realClient.unRegisterLocationListener(this);
                stop();
            }

            @Override
            public void onConnectHotSpotMessage(String s, int i) {
                bdLocationListener.onConnectHotSpotMessage(s, i);
            }
        };
        realClient.registerLocationListener(realListener);
        if (!realClient.isStarted()) {
            realClient.start();
        }
    }

    public BDLocation getLastKnowLocation() {
        return realClient.getLastKnownLocation();
    }

    private void stop() {
        realClient.stop();
    }
}
