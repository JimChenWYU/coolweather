package com.coolweather.android.model;

/**
 * Created by lenovo on 2017/4/13.
 */

public class CountyBean {

    /**
     * id : 1
     * name : 北京
     * weather_id : CN101010100
     */

    private int id;
    private String name;
    private String weather_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeather_id() {
        return weather_id;
    }

    public void setWeather_id(String weather_id) {
        this.weather_id = weather_id;
    }
}