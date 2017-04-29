package com.coolweather.android.model;

import com.bumptech.glide.Glide;
import com.google.gson.annotations.SerializedName;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by lenovo on 2017/4/13.
 */

public class City extends DataSupport {
    private int id;

    private String name;

    @SerializedName("id")
    private int cityCode;

    private int provinceId;

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

    public int getCityCode() {
        return cityCode;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public static boolean saveAll(List<CityBean> dataList, int provinceId) {
        if (dataList != null) {
            for (CityBean c : dataList) {
                if (c != null) {
                    City city = new City();
                    city.setName(c.getName());
                    city.setCityCode(c.getId());
                    city.setProvinceId(provinceId);
                    city.save();
                }
            }
            return true;
        }
        return false;
    }
}
