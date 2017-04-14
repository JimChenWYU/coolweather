package com.coolweather.android.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Closeable;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * Created by lenovo on 2017/4/11.
 */

public class HeWeather5 {

    /**
     * status : ok
     * msg : 查询成功
     * data : {"daily_forecast":[{"wind":{"dir":"无持续风向","deg":"220","sc":"微风","spd":"1"},"hum":"87","pcpn":"5.1","astro":{"mr":"18:52","sr":"06:09","ms":"06:13","ss":"18:46"},"uv":"3","tmp":{"min":"17","max":"24"},"pop":"100","pres":"1010","date":"2017-04-11","cond":{"code_d":"302","txt_n":"雷阵雨","code_n":"302","txt_d":"雷阵雨"},"vis":"16"},{"wind":{"dir":"北风","deg":"18","sc":"微风","spd":"4"},"hum":"84","pcpn":"20.0","astro":{"mr":"19:43","sr":"06:08","ms":"06:50","ss":"18:46"},"uv":"2","tmp":{"min":"17","max":"20"},"pop":"100","pres":"1014","date":"2017-04-12","cond":{"code_d":"307","txt_n":"大雨","code_n":"307","txt_d":"大雨"},"vis":"15"},{"wind":{"dir":"无持续风向","deg":"60","sc":"微风","spd":"2"},"hum":"84","pcpn":"3.9","astro":{"mr":"20:34","sr":"06:07","ms":"07:28","ss":"18:46"},"uv":"4","tmp":{"min":"19","max":"21"},"pop":"100","pres":"1014","date":"2017-04-13","cond":{"code_d":"307","txt_n":"阵雨","code_n":"300","txt_d":"大雨"},"vis":"17"},{"wind":{"dir":"无持续风向","deg":"136","sc":"微风","spd":"0"},"hum":"84","pcpn":"1.4","astro":{"mr":"21:25","sr":"06:06","ms":"08:07","ss":"18:47"},"uv":"6","tmp":{"min":"20","max":"23"},"pop":"100","pres":"1014","date":"2017-04-14","cond":{"code_d":"300","txt_n":"阵雨","code_n":"300","txt_d":"阵雨"},"vis":"16"},{"wind":{"dir":"无持续风向","deg":"146","sc":"微风","spd":"0"},"hum":"82","pcpn":"5.0","astro":{"mr":"22:15","sr":"06:06","ms":"08:48","ss":"18:47"},"uv":"10","tmp":{"min":"22","max":"26"},"pop":"100","pres":"1013","date":"2017-04-15","cond":{"code_d":"300","txt_n":"多云","code_n":"101","txt_d":"阵雨"},"vis":"16"},{"wind":{"dir":"无持续风向","deg":"143","sc":"微风","spd":"1"},"hum":"77","pcpn":"10.0","astro":{"mr":"23:04","sr":"06:05","ms":"09:32","ss":"18:48"},"uv":"5","tmp":{"min":"22","max":"28"},"pop":"100","pres":"1013","date":"2017-04-16","cond":{"code_d":"101","txt_n":"多云","code_n":"101","txt_d":"多云"},"vis":"16"},{"wind":{"dir":"无持续风向","deg":"214","sc":"微风","spd":"1"},"hum":"78","pcpn":"8.8","astro":{"mr":"23:53","sr":"06:04","ms":"10:17","ss":"18:48"},"uv":"5","tmp":{"min":"20","max":"28"},"pop":"100","pres":"1010","date":"2017-04-17","cond":{"code_d":"101","txt_n":"小雨","code_n":"305","txt_d":"多云"},"vis":"17"}],"now":{"wind":{"dir":"东北风","deg":"350","sc":"3-4","spd":"20"},"hum":"79","pcpn":"0","fl":"21","tmp":"22","pres":"1008","cond":{"code":"300","txt":"阵雨"},"vis":"7"},"basic":{"update":{"utc":"2017-04-11 06:56","loc":"2017-04-11 14:56"},"id":"CN101280101","lon":"113.280637","cnty":"中国","lat":"23.125178","city":"广州"},"aqi":{"city":{"co":"1","so2":"9","o3":"11","no2":"51","aqi":"64","pm10":"77","pm25":"38","qlty":"良"}},"suggestion":{"drsg":{"brf":"较舒适","txt":"建议着薄外套、开衫牛仔衫裤等服装。年老体弱者应适当添加衣物，宜着夹克衫、薄毛衣等。"},"trav":{"brf":"一般","txt":"温度适宜，风不大，有降水，旅游指数一般，外出请尽量避开降雨时段，若外出，请注意防雷防雨。"},"uv":{"brf":"弱","txt":"紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。"},"flu":{"brf":"易发","txt":"相对于今天将会出现大幅度降温，空气湿度较大，易发生感冒，请注意适当增加衣服。"},"comf":{"brf":"较舒适","txt":"白天有降雨，但会使人们感觉有些热，不过大部分人仍会有比较舒适的感觉。"},"sport":{"brf":"较不宜","txt":"有降水，推荐您在室内进行健身休闲运动；若坚持户外运动，须注意携带雨具并注意避雨防滑。"},"air":{"brf":"中","txt":"气象条件对空气污染物稀释、扩散和清除无明显影响，易感人群应适当减少室外活动时间。"},"cw":{"brf":"不宜","txt":"不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。"}},"hourly_forecast":[{"wind":{"dir":"北风","deg":"353","sc":"微风","spd":"12"},"hum":"87","tmp":"21","pop":"95","pres":"1008","date":"2017-04-11 16:00","cond":{"code":"300","txt":"阵雨"}},{"wind":{"dir":"北风","deg":"356","sc":"微风","spd":"13"},"hum":"85","tmp":"20","pop":"96","pres":"1009","date":"2017-04-11 19:00","cond":{"code":"305","txt":"小雨"}},{"wind":{"dir":"东南风","deg":"129","sc":"微风","spd":"10"},"hum":"79","tmp":"17","pop":"89","pres":"1011","date":"2017-04-11 22:00","cond":{"code":"309","txt":"毛毛雨/细雨"}}]}
     */

    private String status;
    private String msg;
    private DataBean data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable, Cloneable, MultiItemEntity {
        /**
         * daily_forecast : [{"wind":{"dir":"无持续风向","deg":"220","sc":"微风","spd":"1"},"hum":"87","pcpn":"5.1","astro":{"mr":"18:52","sr":"06:09","ms":"06:13","ss":"18:46"},"uv":"3","tmp":{"min":"17","max":"24"},"pop":"100","pres":"1010","date":"2017-04-11","cond":{"code_d":"302","txt_n":"雷阵雨","code_n":"302","txt_d":"雷阵雨"},"vis":"16"},{"wind":{"dir":"北风","deg":"18","sc":"微风","spd":"4"},"hum":"84","pcpn":"20.0","astro":{"mr":"19:43","sr":"06:08","ms":"06:50","ss":"18:46"},"uv":"2","tmp":{"min":"17","max":"20"},"pop":"100","pres":"1014","date":"2017-04-12","cond":{"code_d":"307","txt_n":"大雨","code_n":"307","txt_d":"大雨"},"vis":"15"},{"wind":{"dir":"无持续风向","deg":"60","sc":"微风","spd":"2"},"hum":"84","pcpn":"3.9","astro":{"mr":"20:34","sr":"06:07","ms":"07:28","ss":"18:46"},"uv":"4","tmp":{"min":"19","max":"21"},"pop":"100","pres":"1014","date":"2017-04-13","cond":{"code_d":"307","txt_n":"阵雨","code_n":"300","txt_d":"大雨"},"vis":"17"},{"wind":{"dir":"无持续风向","deg":"136","sc":"微风","spd":"0"},"hum":"84","pcpn":"1.4","astro":{"mr":"21:25","sr":"06:06","ms":"08:07","ss":"18:47"},"uv":"6","tmp":{"min":"20","max":"23"},"pop":"100","pres":"1014","date":"2017-04-14","cond":{"code_d":"300","txt_n":"阵雨","code_n":"300","txt_d":"阵雨"},"vis":"16"},{"wind":{"dir":"无持续风向","deg":"146","sc":"微风","spd":"0"},"hum":"82","pcpn":"5.0","astro":{"mr":"22:15","sr":"06:06","ms":"08:48","ss":"18:47"},"uv":"10","tmp":{"min":"22","max":"26"},"pop":"100","pres":"1013","date":"2017-04-15","cond":{"code_d":"300","txt_n":"多云","code_n":"101","txt_d":"阵雨"},"vis":"16"},{"wind":{"dir":"无持续风向","deg":"143","sc":"微风","spd":"1"},"hum":"77","pcpn":"10.0","astro":{"mr":"23:04","sr":"06:05","ms":"09:32","ss":"18:48"},"uv":"5","tmp":{"min":"22","max":"28"},"pop":"100","pres":"1013","date":"2017-04-16","cond":{"code_d":"101","txt_n":"多云","code_n":"101","txt_d":"多云"},"vis":"16"},{"wind":{"dir":"无持续风向","deg":"214","sc":"微风","spd":"1"},"hum":"78","pcpn":"8.8","astro":{"mr":"23:53","sr":"06:04","ms":"10:17","ss":"18:48"},"uv":"5","tmp":{"min":"20","max":"28"},"pop":"100","pres":"1010","date":"2017-04-17","cond":{"code_d":"101","txt_n":"小雨","code_n":"305","txt_d":"多云"},"vis":"17"}]
         * now : {"wind":{"dir":"东北风","deg":"350","sc":"3-4","spd":"20"},"hum":"79","pcpn":"0","fl":"21","tmp":"22","pres":"1008","cond":{"code":"300","txt":"阵雨"},"vis":"7"}
         * basic : {"update":{"utc":"2017-04-11 06:56","loc":"2017-04-11 14:56"},"id":"CN101280101","lon":"113.280637","cnty":"中国","lat":"23.125178","city":"广州"}
         * aqi : {"city":{"co":"1","so2":"9","o3":"11","no2":"51","aqi":"64","pm10":"77","pm25":"38","qlty":"良"}}
         * suggestion : {"drsg":{"brf":"较舒适","txt":"建议着薄外套、开衫牛仔衫裤等服装。年老体弱者应适当添加衣物，宜着夹克衫、薄毛衣等。"},"trav":{"brf":"一般","txt":"温度适宜，风不大，有降水，旅游指数一般，外出请尽量避开降雨时段，若外出，请注意防雷防雨。"},"uv":{"brf":"弱","txt":"紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。"},"flu":{"brf":"易发","txt":"相对于今天将会出现大幅度降温，空气湿度较大，易发生感冒，请注意适当增加衣服。"},"comf":{"brf":"较舒适","txt":"白天有降雨，但会使人们感觉有些热，不过大部分人仍会有比较舒适的感觉。"},"sport":{"brf":"较不宜","txt":"有降水，推荐您在室内进行健身休闲运动；若坚持户外运动，须注意携带雨具并注意避雨防滑。"},"air":{"brf":"中","txt":"气象条件对空气污染物稀释、扩散和清除无明显影响，易感人群应适当减少室外活动时间。"},"cw":{"brf":"不宜","txt":"不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。"}}
         * hourly_forecast : [{"wind":{"dir":"北风","deg":"353","sc":"微风","spd":"12"},"hum":"87","tmp":"21","pop":"95","pres":"1008","date":"2017-04-11 16:00","cond":{"code":"300","txt":"阵雨"}},{"wind":{"dir":"北风","deg":"356","sc":"微风","spd":"13"},"hum":"85","tmp":"20","pop":"96","pres":"1009","date":"2017-04-11 19:00","cond":{"code":"305","txt":"小雨"}},{"wind":{"dir":"东南风","deg":"129","sc":"微风","spd":"10"},"hum":"79","tmp":"17","pop":"89","pres":"1011","date":"2017-04-11 22:00","cond":{"code":"309","txt":"毛毛雨/细雨"}}]
         */
        public static final int TYPE_NOW = 1;
        public static final int TYPE_DAILYFORECAST = 2;
        public static final int TYPE_SUGGESTION = 3;

        private int itemType = 0;

        private NowBean now;
        private BasicBean basic;
        private AqiBean aqi;
        private SuggestionBean suggestion;
        private List<DailyForecastBean> daily_forecast;
        private List<HourlyForecastBean> hourly_forecast;

        @Override
        public Object clone() {
            DataBean object = null;
            try {
                object = (DataBean) super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return object;
        }

        @Override
        public int getItemType() {
            return itemType;
        }

        public void setItemType(int type) {
            this.itemType = type;
        }

        public NowBean getNow() {
            return now;
        }

        public void setNow(NowBean now) {
            this.now = now;
        }

        public BasicBean getBasic() {
            return basic;
        }

        public void setBasic(BasicBean basic) {
            this.basic = basic;
        }

        public AqiBean getAqi() {
            return aqi;
        }

        public void setAqi(AqiBean aqi) {
            this.aqi = aqi;
        }

        public SuggestionBean getSuggestion() {
            return suggestion;
        }

        public void setSuggestion(SuggestionBean suggestion) {
            this.suggestion = suggestion;
        }

        public List<DailyForecastBean> getDaily_forecast() {
            return daily_forecast;
        }

        public void setDaily_forecast(List<DailyForecastBean> daily_forecast) {
            this.daily_forecast = daily_forecast;
        }

        public List<HourlyForecastBean> getHourly_forecast() {
            return hourly_forecast;
        }

        public void setHourly_forecast(List<HourlyForecastBean> hourly_forecast) {
            this.hourly_forecast = hourly_forecast;
        }

        public static class NowBean implements Serializable, MultiItemEntity{
            /**
             * wind : {"dir":"东北风","deg":"350","sc":"3-4","spd":"20"}
             * hum : 79
             * pcpn : 0
             * fl : 21
             * tmp : 22
             * pres : 1008
             * cond : {"code":"300","txt":"阵雨"}
             * vis : 7
             */

            private WindBean wind;
            private String hum;
            private String pcpn;
            private String fl;
            private String tmp;
            private String pres;
            private CondBean cond;
            private String vis;

            public WindBean getWind() {
                return wind;
            }

            public void setWind(WindBean wind) {
                this.wind = wind;
            }

            public String getHum() {
                return hum;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public String getPcpn() {
                return pcpn;
            }

            public void setPcpn(String pcpn) {
                this.pcpn = pcpn;
            }

            public String getFl() {
                return fl;
            }

            public void setFl(String fl) {
                this.fl = fl;
            }

            public String getTmp() {
                return tmp;
            }

            public void setTmp(String tmp) {
                this.tmp = tmp;
            }

            public String getPres() {
                return pres;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }

            public CondBean getCond() {
                return cond;
            }

            public void setCond(CondBean cond) {
                this.cond = cond;
            }

            public String getVis() {
                return vis;
            }

            public void setVis(String vis) {
                this.vis = vis;
            }

            @Override
            public int getItemType() {
                return TYPE_NOW;
            }

            public static class WindBean implements Serializable{
                /**
                 * dir : 东北风
                 * deg : 350
                 * sc : 3-4
                 * spd : 20
                 */

                private String dir;
                private String deg;
                private String sc;
                private String spd;

                public String getDir() {
                    return dir;
                }

                public void setDir(String dir) {
                    this.dir = dir;
                }

                public String getDeg() {
                    return deg;
                }

                public void setDeg(String deg) {
                    this.deg = deg;
                }

                public String getSc() {
                    return sc;
                }

                public void setSc(String sc) {
                    this.sc = sc;
                }

                public String getSpd() {
                    return spd;
                }

                public void setSpd(String spd) {
                    this.spd = spd;
                }
            }

            public static class CondBean implements Serializable{
                /**
                 * code : 300
                 * txt : 阵雨
                 */

                private String code;
                private String txt;

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }
        }

        public static class BasicBean implements Serializable{
            /**
             * update : {"utc":"2017-04-11 06:56","loc":"2017-04-11 14:56"}
             * id : CN101280101
             * lon : 113.280637
             * cnty : 中国
             * lat : 23.125178
             * city : 广州
             */

            private UpdateBean update;
            private String id;
            private String lon;
            private String cnty;
            private String lat;
            private String city;

            public UpdateBean getUpdate() {
                return update;
            }

            public void setUpdate(UpdateBean update) {
                this.update = update;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getLon() {
                return lon;
            }

            public void setLon(String lon) {
                this.lon = lon;
            }

            public String getCnty() {
                return cnty;
            }

            public void setCnty(String cnty) {
                this.cnty = cnty;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public static class UpdateBean implements Serializable{
                /**
                 * utc : 2017-04-11 06:56
                 * loc : 2017-04-11 14:56
                 */

                private String utc;
                private String loc;

                public String getUtc() {
                    return utc;
                }

                public void setUtc(String utc) {
                    this.utc = utc;
                }

                public String getLoc() {
                    return loc;
                }

                public void setLoc(String loc) {
                    this.loc = loc;
                }
            }
        }

        public static class AqiBean implements Serializable{
            /**
             * city : {"co":"1","so2":"9","o3":"11","no2":"51","aqi":"64","pm10":"77","pm25":"38","qlty":"良"}
             */

            private CityBean city;

            public CityBean getCity() {
                return city;
            }

            public void setCity(CityBean city) {
                this.city = city;
            }

            public static class CityBean implements Serializable{
                /**
                 * co : 1
                 * so2 : 9
                 * o3 : 11
                 * no2 : 51
                 * aqi : 64
                 * pm10 : 77
                 * pm25 : 38
                 * qlty : 良
                 */

                private String co;
                private String so2;
                private String o3;
                private String no2;
                private String aqi;
                private String pm10;
                private String pm25;
                private String qlty;

                public String getCo() {
                    return co;
                }

                public void setCo(String co) {
                    this.co = co;
                }

                public String getSo2() {
                    return so2;
                }

                public void setSo2(String so2) {
                    this.so2 = so2;
                }

                public String getO3() {
                    return o3;
                }

                public void setO3(String o3) {
                    this.o3 = o3;
                }

                public String getNo2() {
                    return no2;
                }

                public void setNo2(String no2) {
                    this.no2 = no2;
                }

                public String getAqi() {
                    return aqi;
                }

                public void setAqi(String aqi) {
                    this.aqi = aqi;
                }

                public String getPm10() {
                    return pm10;
                }

                public void setPm10(String pm10) {
                    this.pm10 = pm10;
                }

                public String getPm25() {
                    return pm25;
                }

                public void setPm25(String pm25) {
                    this.pm25 = pm25;
                }

                public String getQlty() {
                    return qlty;
                }

                public void setQlty(String qlty) {
                    this.qlty = qlty;
                }
            }
        }

        public static class SuggestionBean implements Serializable, MultiItemEntity{
            /**
             * drsg : {"brf":"较舒适","txt":"建议着薄外套、开衫牛仔衫裤等服装。年老体弱者应适当添加衣物，宜着夹克衫、薄毛衣等。"}
             * trav : {"brf":"一般","txt":"温度适宜，风不大，有降水，旅游指数一般，外出请尽量避开降雨时段，若外出，请注意防雷防雨。"}
             * uv : {"brf":"弱","txt":"紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。"}
             * flu : {"brf":"易发","txt":"相对于今天将会出现大幅度降温，空气湿度较大，易发生感冒，请注意适当增加衣服。"}
             * comf : {"brf":"较舒适","txt":"白天有降雨，但会使人们感觉有些热，不过大部分人仍会有比较舒适的感觉。"}
             * sport : {"brf":"较不宜","txt":"有降水，推荐您在室内进行健身休闲运动；若坚持户外运动，须注意携带雨具并注意避雨防滑。"}
             * air : {"brf":"中","txt":"气象条件对空气污染物稀释、扩散和清除无明显影响，易感人群应适当减少室外活动时间。"}
             * cw : {"brf":"不宜","txt":"不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。"}
             */

            private DrsgBean drsg;
            private TravBean trav;
            private UvBean uv;
            private FluBean flu;
            private ComfBean comf;
            private SportBean sport;
            private AirBean air;
            private CwBean cw;

            public DrsgBean getDrsg() {
                return drsg;
            }

            public void setDrsg(DrsgBean drsg) {
                this.drsg = drsg;
            }

            public TravBean getTrav() {
                return trav;
            }

            public void setTrav(TravBean trav) {
                this.trav = trav;
            }

            public UvBean getUv() {
                return uv;
            }

            public void setUv(UvBean uv) {
                this.uv = uv;
            }

            public FluBean getFlu() {
                return flu;
            }

            public void setFlu(FluBean flu) {
                this.flu = flu;
            }

            public ComfBean getComf() {
                return comf;
            }

            public void setComf(ComfBean comf) {
                this.comf = comf;
            }

            public SportBean getSport() {
                return sport;
            }

            public void setSport(SportBean sport) {
                this.sport = sport;
            }

            public AirBean getAir() {
                return air;
            }

            public void setAir(AirBean air) {
                this.air = air;
            }

            public CwBean getCw() {
                return cw;
            }

            public void setCw(CwBean cw) {
                this.cw = cw;
            }

            @Override
            public int getItemType() {
                return TYPE_SUGGESTION;
            }

            public static class DrsgBean implements Serializable{
                /**
                 * brf : 较舒适
                 * txt : 建议着薄外套、开衫牛仔衫裤等服装。年老体弱者应适当添加衣物，宜着夹克衫、薄毛衣等。
                 */

                private String brf;
                private String txt;

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class TravBean implements Serializable{
                /**
                 * brf : 一般
                 * txt : 温度适宜，风不大，有降水，旅游指数一般，外出请尽量避开降雨时段，若外出，请注意防雷防雨。
                 */

                private String brf;
                private String txt;

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class UvBean implements Serializable{
                /**
                 * brf : 弱
                 * txt : 紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。
                 */

                private String brf;
                private String txt;

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class FluBean implements Serializable{
                /**
                 * brf : 易发
                 * txt : 相对于今天将会出现大幅度降温，空气湿度较大，易发生感冒，请注意适当增加衣服。
                 */

                private String brf;
                private String txt;

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class ComfBean implements Serializable{
                /**
                 * brf : 较舒适
                 * txt : 白天有降雨，但会使人们感觉有些热，不过大部分人仍会有比较舒适的感觉。
                 */

                private String brf;
                private String txt;

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class SportBean implements Serializable{
                /**
                 * brf : 较不宜
                 * txt : 有降水，推荐您在室内进行健身休闲运动；若坚持户外运动，须注意携带雨具并注意避雨防滑。
                 */

                private String brf;
                private String txt;

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class AirBean implements Serializable{
                /**
                 * brf : 中
                 * txt : 气象条件对空气污染物稀释、扩散和清除无明显影响，易感人群应适当减少室外活动时间。
                 */

                private String brf;
                private String txt;

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class CwBean implements Serializable{
                /**
                 * brf : 不宜
                 * txt : 不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。
                 */

                private String brf;
                private String txt;

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }
        }

        public static class DailyForecastBean implements Serializable, MultiItemEntity{
            /**
             * wind : {"dir":"无持续风向","deg":"220","sc":"微风","spd":"1"}
             * hum : 87
             * pcpn : 5.1
             * astro : {"mr":"18:52","sr":"06:09","ms":"06:13","ss":"18:46"}
             * uv : 3
             * tmp : {"min":"17","max":"24"}
             * pop : 100
             * pres : 1010
             * date : 2017-04-11
             * cond : {"code_d":"302","txt_n":"雷阵雨","code_n":"302","txt_d":"雷阵雨"}
             * vis : 16
             */

            private WindBeanX wind;
            private String hum;
            private String pcpn;
            private AstroBean astro;
            private String uv;
            private TmpBean tmp;
            private String pop;
            private String pres;
            private String date;
            private CondBeanX cond;
            private String vis;

            public WindBeanX getWind() {
                return wind;
            }

            public void setWind(WindBeanX wind) {
                this.wind = wind;
            }

            public String getHum() {
                return hum;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public String getPcpn() {
                return pcpn;
            }

            public void setPcpn(String pcpn) {
                this.pcpn = pcpn;
            }

            public AstroBean getAstro() {
                return astro;
            }

            public void setAstro(AstroBean astro) {
                this.astro = astro;
            }

            public String getUv() {
                return uv;
            }

            public void setUv(String uv) {
                this.uv = uv;
            }

            public TmpBean getTmp() {
                return tmp;
            }

            public void setTmp(TmpBean tmp) {
                this.tmp = tmp;
            }

            public String getPop() {
                return pop;
            }

            public void setPop(String pop) {
                this.pop = pop;
            }

            public String getPres() {
                return pres;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public CondBeanX getCond() {
                return cond;
            }

            public void setCond(CondBeanX cond) {
                this.cond = cond;
            }

            public String getVis() {
                return vis;
            }

            public void setVis(String vis) {
                this.vis = vis;
            }

            @Override
            public int getItemType() {
                return TYPE_DAILYFORECAST;
            }

            public static class WindBeanX implements Serializable{
                /**
                 * dir : 无持续风向
                 * deg : 220
                 * sc : 微风
                 * spd : 1
                 */

                private String dir;
                private String deg;
                private String sc;
                private String spd;

                public String getDir() {
                    return dir;
                }

                public void setDir(String dir) {
                    this.dir = dir;
                }

                public String getDeg() {
                    return deg;
                }

                public void setDeg(String deg) {
                    this.deg = deg;
                }

                public String getSc() {
                    return sc;
                }

                public void setSc(String sc) {
                    this.sc = sc;
                }

                public String getSpd() {
                    return spd;
                }

                public void setSpd(String spd) {
                    this.spd = spd;
                }
            }

            public static class AstroBean implements Serializable{
                /**
                 * mr : 18:52
                 * sr : 06:09
                 * ms : 06:13
                 * ss : 18:46
                 */

                private String mr;
                private String sr;
                private String ms;
                private String ss;

                public String getMr() {
                    return mr;
                }

                public void setMr(String mr) {
                    this.mr = mr;
                }

                public String getSr() {
                    return sr;
                }

                public void setSr(String sr) {
                    this.sr = sr;
                }

                public String getMs() {
                    return ms;
                }

                public void setMs(String ms) {
                    this.ms = ms;
                }

                public String getSs() {
                    return ss;
                }

                public void setSs(String ss) {
                    this.ss = ss;
                }
            }

            public static class TmpBean implements Serializable{
                /**
                 * min : 17
                 * max : 24
                 */

                private String min;
                private String max;

                public String getMin() {
                    return min;
                }

                public void setMin(String min) {
                    this.min = min;
                }

                public String getMax() {
                    return max;
                }

                public void setMax(String max) {
                    this.max = max;
                }
            }

            public static class CondBeanX implements Serializable{
                /**
                 * code_d : 302
                 * txt_n : 雷阵雨
                 * code_n : 302
                 * txt_d : 雷阵雨
                 */

                private String code_d;
                private String txt_n;
                private String code_n;
                private String txt_d;

                public String getCode_d() {
                    return code_d;
                }

                public void setCode_d(String code_d) {
                    this.code_d = code_d;
                }

                public String getTxt_n() {
                    return txt_n;
                }

                public void setTxt_n(String txt_n) {
                    this.txt_n = txt_n;
                }

                public String getCode_n() {
                    return code_n;
                }

                public void setCode_n(String code_n) {
                    this.code_n = code_n;
                }

                public String getTxt_d() {
                    return txt_d;
                }

                public void setTxt_d(String txt_d) {
                    this.txt_d = txt_d;
                }
            }
        }

        public static class HourlyForecastBean implements Serializable{
            /**
             * wind : {"dir":"北风","deg":"353","sc":"微风","spd":"12"}
             * hum : 87
             * tmp : 21
             * pop : 95
             * pres : 1008
             * date : 2017-04-11 16:00
             * cond : {"code":"300","txt":"阵雨"}
             */

            private WindBeanXX wind;
            private String hum;
            private String tmp;
            private String pop;
            private String pres;
            private String date;
            private CondBeanXX cond;

            public WindBeanXX getWind() {
                return wind;
            }

            public void setWind(WindBeanXX wind) {
                this.wind = wind;
            }

            public String getHum() {
                return hum;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public String getTmp() {
                return tmp;
            }

            public void setTmp(String tmp) {
                this.tmp = tmp;
            }

            public String getPop() {
                return pop;
            }

            public void setPop(String pop) {
                this.pop = pop;
            }

            public String getPres() {
                return pres;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public CondBeanXX getCond() {
                return cond;
            }

            public void setCond(CondBeanXX cond) {
                this.cond = cond;
            }

            public static class WindBeanXX implements Serializable{
                /**
                 * dir : 北风
                 * deg : 353
                 * sc : 微风
                 * spd : 12
                 */

                private String dir;
                private String deg;
                private String sc;
                private String spd;

                public String getDir() {
                    return dir;
                }

                public void setDir(String dir) {
                    this.dir = dir;
                }

                public String getDeg() {
                    return deg;
                }

                public void setDeg(String deg) {
                    this.deg = deg;
                }

                public String getSc() {
                    return sc;
                }

                public void setSc(String sc) {
                    this.sc = sc;
                }

                public String getSpd() {
                    return spd;
                }

                public void setSpd(String spd) {
                    this.spd = spd;
                }
            }

            public static class CondBeanXX implements Serializable{
                /**
                 * code : 300
                 * txt : 阵雨
                 */

                private String code;
                private String txt;

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }
        }
    }
}
