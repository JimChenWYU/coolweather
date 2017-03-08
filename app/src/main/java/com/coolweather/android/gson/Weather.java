package com.coolweather.android.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by lenovo on 2017/3/8.
 */

public class Weather {

    /**
     * aqi : {}
     * basic : {}
     * daily_forecast : []
     * now : {}
     * status : ok
     * suggestion : {}
     */

    private AQI aqi;
    private Basic basic;
    private Now now;
    private String status;
    private Suggestion suggestion;

    @SerializedName("daily_forecast")
    private List<Forecast> forecastList;

    public AQI getAqi() {
        return aqi;
    }

    public Basic getBasic() {
        return basic;
    }

    public Now getNow() {
        return now;
    }

    public String getStatus() {
        return status;
    }

    public Suggestion getSuggestion() {
        return suggestion;
    }

    public List<Forecast> getForecastList() {
        return forecastList;
    }
}
