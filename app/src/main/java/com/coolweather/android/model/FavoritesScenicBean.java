package com.coolweather.android.model;

/**
 * Created by lenovo on 2017/4/19.
 */

public class FavoritesScenicBean {
    private String id;
    private String name;
    private String province;
    private String city;

    public FavoritesScenicBean() {
    }

    public FavoritesScenicBean(ScenicBaseDetail detail) {
        id = detail.getScenicId();
        name = detail.getName();
        province = detail.getProvince();
        city = detail.getCity();
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

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof FavoritesScenicBean)
                && id.equals(((FavoritesScenicBean) obj).getId());
    }

    @Override
    public String toString() {
        return "FavoritesScenicBean{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
