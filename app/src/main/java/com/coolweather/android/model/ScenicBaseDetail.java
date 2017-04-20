package com.coolweather.android.model;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by lenovo on 2017/4/19.
 */

public class ScenicBaseDetail extends DataSupport implements Serializable{

    /**
     * id : CN10101010001A
     * province:
     * city:
     * city : 北京明城墙遗址公园
     */

    private String scenicId;
    private String name;
    private String province;
    private String city;
    private boolean isFavorite;

    public String getScenicId() {
        return scenicId;
    }

    public void setScenicId(String scenicId) {
        this.scenicId = scenicId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    @Override
    public String toString() {
        return "ScenicBaseDetail{" +
                "scenicId='" + scenicId + '\'' +
                ", name='" + name + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", isFavorite=" + isFavorite +
                '}';
    }
}
