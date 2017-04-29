package com.coolweather.android.model;

import java.io.Serializable;

/**
 * Created by lenovo on 2017/4/20.
 */

public class GirlsGank implements Serializable{
    /**
     * _id : 58f8043e421aa9544ed88993
     * createdAt : 2017-04-20T08:43:42.333Z
     * desc : 4-20
     * publishedAt : 2017-04-20T14:03:06.490Z
     * source : chrome
     * type : 福利
     * url : http://7xi8d6.com1.z0.glb.clouddn.com/2017-04-20-17932350_1238028809599424_2089669844847820800_n.jpg
     * used : true
     * who : daimajia
     */

    private String _id;
    private String createdAt;
    private String desc;
    private String publishedAt;
    private String source;
    private String type;
    private String url;
    private boolean used;
    private String who;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }
}
