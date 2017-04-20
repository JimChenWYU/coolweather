package com.coolweather.android.utils;

import com.coolweather.android.App;
import com.coolweather.android.AppGlobal;
import com.coolweather.android.http.RetrofitManager;
import com.coolweather.android.model.HeWeather5;
import com.coolweather.android.model.WeatherBean;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by lenovo on 2017/4/11.
 */

public class WeatherUtil {

    private static WeatherUtil instance;

    private Map<String, WeatherBean> weatherBeanMap;

    private WeatherUtil() {
        weatherBeanMap = new HashMap<>();
        List<WeatherBean> list = RetrofitManager.gson().fromJson(readFromAssets(),
                new TypeToken<List<WeatherBean>>() {
                }.getType());
        for (WeatherBean bean : list) {
            weatherBeanMap.put(bean.getCode(), bean);
        }
    }

    private static WeatherUtil getInstance() {
        if (instance == null) {
            synchronized (WeatherUtil.class) {
                instance = new WeatherUtil();
            }
        }
        return instance;
    }

    private String readFromAssets() {
        try {
            InputStream is = App.getContext().getAssets().open(AppGlobal.WEATHER_SIGN);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            return new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static HeWeather5.DataBean.DailyForecastBean getYesterday() {
        return (HeWeather5.DataBean.DailyForecastBean) ACache.get(App.getContext())
                .getAsObject(TimeUtils.getPreviousDay(TimeUtils.getCurTimeString(TimeUtils.DATE_SDF), 1));
    }

    public static Observable<WeatherBean> getWeatherDict(final String code) {
        return Observable.create(new Observable.OnSubscribe<WeatherBean>() {
            @Override
            public void call(Subscriber<? super WeatherBean> subscriber) {
                subscriber.onNext(WeatherUtil.getInstance().weatherBeanMap.get(code));
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io());
    }

    public static void saveDailyHistory(final HeWeather5.DataBean weather) {
        Observable.just(weather).filter(new Func1<HeWeather5.DataBean, Boolean>() {
            @Override
            public Boolean call(HeWeather5.DataBean dataBean) {
                return dataBean != null;
            }
        }).map(new Func1<HeWeather5.DataBean, Boolean>() {
            @Override
            public Boolean call(HeWeather5.DataBean dataBean) {
                ACache mCache = ACache.get(App.getContext());
                for (HeWeather5.DataBean.DailyForecastBean bean : dataBean.getDaily_forecast()) {
                    mCache.put(bean.getDate(), bean, 7 * 24 * 60 * 60);
                }
                return true;
            }
        }).subscribeOn(Schedulers.io()).subscribe();
    }

    public static String getShareMessage(HeWeather5.DataBean weather) {
        StringBuffer message = new StringBuffer();
        message.append(weather.getBasic().getCity());
        message.append("天气：");
        message.append("\r\n");
        message.append(weather.getBasic().getUpdate().getLoc());
        message.append(" 发布：");
        message.append("\r\n");
        message.append(weather.getNow().getCond().getTxt());
        message.append("，");
        message.append(weather.getNow().getTmp()).append("℃");
        message.append("。");
        message.append("\r\n");
        message.append("PM2.5：").append(weather.getAqi().getCity().getPm25());
        message.append("，");
        message.append(weather.getAqi().getCity().getQlty());
        message.append("。");
        message.append("\r\n");
        message.append("今天：");
        message.append(weather.getDaily_forecast().get(0).getTmp().getMin()).append("℃-");
        message.append(weather.getDaily_forecast().get(0).getTmp().getMax()).append("℃");
        message.append("，");
        message.append(weather.getDaily_forecast().get(0).getCond().getTxt_d());
        message.append("，");
        message.append(weather.getDaily_forecast().get(0).getWind().getDir());
        message.append(weather.getDaily_forecast().get(0).getWind().getSc());
        message.append("级。");
        message.append("\r\n");
        message.append("明天：");
        message.append(weather.getDaily_forecast().get(1).getTmp().getMin()).append("℃-");
        message.append(weather.getDaily_forecast().get(1).getTmp().getMax()).append("℃");
        message.append("，");
        message.append(weather.getDaily_forecast().get(1).getCond().getTxt_d());
        message.append("，");
        message.append(weather.getDaily_forecast().get(1).getWind().getDir());
        message.append(weather.getDaily_forecast().get(1).getWind().getSc());
        message.append("级。");

        return message.toString();
    }
}
