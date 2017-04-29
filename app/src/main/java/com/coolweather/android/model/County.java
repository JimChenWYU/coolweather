package com.coolweather.android.model;

import com.google.gson.annotations.SerializedName;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by lenovo on 2017/4/13.
 */

public class County extends DataSupport {
    private int id;

    private String name;

    private int countyCode;

    private String weatherId;

    private int cityId;

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

    public int getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(int countyCode) {
        this.countyCode = countyCode;
    }

    public String getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(String weatherId) {
        this.weatherId = weatherId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public static boolean saveAll(List<CountyBean> dataList, int cityId) {
        if (dataList != null) {
            for (CountyBean c : dataList) {
                County county = new County();
                county.setName(c.getName());
                county.setWeatherId(c.getWeather_id());
                county.setCityId(cityId);
                county.save();
            }
            return true;
        }
        return false;
    }
}
