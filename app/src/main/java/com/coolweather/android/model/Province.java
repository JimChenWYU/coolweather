package com.coolweather.android.model;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by lenovo on 2017/4/13.
 */

public class Province extends DataSupport {
    private int id;

    private String name;

    private int provinceCode;

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

    public int getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(int provinceCode) {
        this.provinceCode = provinceCode;
    }

    public static boolean saveAll(List<ProvinceBean> dataList) {
        if (dataList != null) {
            for (ProvinceBean p : dataList) {
                if (p != null) {
                    Province province = new Province();
                    province.setName(p.getName());
                    province.setProvinceCode(p.getId());
                    province.save();
                }
            }
            return true;
        }
        return false;
    }
}
