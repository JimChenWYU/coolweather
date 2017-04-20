package com.coolweather.android.model;

import java.io.Serializable;

/**
 * Created by lenovo on 2017/4/15.
 */

public class ScenicLineBean implements Serializable {


    /**
     * id : CN10101010001A
     * name : 北京明城墙遗址公园
     * city : 北京
     * province : 北京
     */

    private String id;
    private String name;
    private String city;
    private String province;

    public ScenicLineBean(ScenicBaseDetail line) {
        id = line.getScenicId();
        province = line.getProvince();
        city = line.getCity();
        name = line.getName();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
