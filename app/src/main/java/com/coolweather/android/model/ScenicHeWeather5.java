package com.coolweather.android.model;

import java.util.List;

/**
 * Created by lenovo on 2017/4/16.
 */

public class ScenicHeWeather5 {

    /**
     * status : ok
     * msg : 查询成功
     * data : {"daily_forecast":[{"wind":{"dir":"无持续风向","sc":"微风"},"astro":{"sr":"05:34","ss":"18:53"},"tmp":{"min":"16"},"date":"2017-04-16","cond":{"txt_n":"小雨","code_n":"305"}},{"wind":{"dir":"西南风","sc":"微风"},"astro":{"sr":"05:33","ss":"18:54"},"tmp":{"min":"13","max":"29"},"date":"2017-04-17","cond":{"code_d":"305","txt_n":"小雨","code_n":"305","txt_d":"小雨"}},{"wind":{"dir":"西北风","sc":"微风"},"astro":{"sr":"05:31","ss":"18:55"},"tmp":{"min":"12","max":"23"},"date":"2017-04-18","cond":{"code_d":"101","txt_n":"小雨","code_n":"305","txt_d":"多云"}},{"wind":{"dir":"西风","sc":"微风"},"astro":{"sr":"05:30","ss":"18:56"},"tmp":{"min":"11","max":"24"},"date":"2017-04-19","cond":{"code_d":"101","txt_n":"多云","code_n":"101","txt_d":"多云"}},{"wind":{"dir":"东北风","sc":"微风"},"astro":{"sr":"05:28","ss":"18:57"},"tmp":{"min":"11","max":"21"},"date":"2017-04-20","cond":{"code_d":"104","txt_n":"阴","code_n":"104","txt_d":"阴"}},{"wind":{"dir":"西北风","sc":"微风"},"astro":{"sr":"05:27","ss":"18:58"},"tmp":{"min":"10","max":"24"},"date":"2017-04-21","cond":{"code_d":"100","txt_n":"多云","code_n":"101","txt_d":"晴"}},{"wind":{"dir":"西南风","sc":"微风"},"astro":{"sr":"05:26","ss":"18:59"},"tmp":{"min":"10","max":"28"},"date":"2017-04-22","cond":{"code_d":"101","txt_n":"多云","code_n":"101","txt_d":"多云"}}],"basic":{"update":{"utc":"2017-04-16 12:10","loc":"2017-04-16 20:10"},"id":"CN10101010001A","lon":"116.43","cnty":"中国","lat":"39.9","city":"北京明城墙遗址公园"}}
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

    public static class DataBean {
        /**
         * daily_forecast : [{"wind":{"dir":"无持续风向","sc":"微风"},"astro":{"sr":"05:34","ss":"18:53"},"tmp":{"min":"16"},"date":"2017-04-16","cond":{"txt_n":"小雨","code_n":"305"}},{"wind":{"dir":"西南风","sc":"微风"},"astro":{"sr":"05:33","ss":"18:54"},"tmp":{"min":"13","max":"29"},"date":"2017-04-17","cond":{"code_d":"305","txt_n":"小雨","code_n":"305","txt_d":"小雨"}},{"wind":{"dir":"西北风","sc":"微风"},"astro":{"sr":"05:31","ss":"18:55"},"tmp":{"min":"12","max":"23"},"date":"2017-04-18","cond":{"code_d":"101","txt_n":"小雨","code_n":"305","txt_d":"多云"}},{"wind":{"dir":"西风","sc":"微风"},"astro":{"sr":"05:30","ss":"18:56"},"tmp":{"min":"11","max":"24"},"date":"2017-04-19","cond":{"code_d":"101","txt_n":"多云","code_n":"101","txt_d":"多云"}},{"wind":{"dir":"东北风","sc":"微风"},"astro":{"sr":"05:28","ss":"18:57"},"tmp":{"min":"11","max":"21"},"date":"2017-04-20","cond":{"code_d":"104","txt_n":"阴","code_n":"104","txt_d":"阴"}},{"wind":{"dir":"西北风","sc":"微风"},"astro":{"sr":"05:27","ss":"18:58"},"tmp":{"min":"10","max":"24"},"date":"2017-04-21","cond":{"code_d":"100","txt_n":"多云","code_n":"101","txt_d":"晴"}},{"wind":{"dir":"西南风","sc":"微风"},"astro":{"sr":"05:26","ss":"18:59"},"tmp":{"min":"10","max":"28"},"date":"2017-04-22","cond":{"code_d":"101","txt_n":"多云","code_n":"101","txt_d":"多云"}}]
         * basic : {"update":{"utc":"2017-04-16 12:10","loc":"2017-04-16 20:10"},"id":"CN10101010001A","lon":"116.43","cnty":"中国","lat":"39.9","city":"北京明城墙遗址公园"}
         */

        private BasicBean basic;
        private List<DailyForecastBean> daily_forecast;

        public BasicBean getBasic() {
            return basic;
        }

        public void setBasic(BasicBean basic) {
            this.basic = basic;
        }

        public List<DailyForecastBean> getDaily_forecast() {
            return daily_forecast;
        }

        public void setDaily_forecast(List<DailyForecastBean> daily_forecast) {
            this.daily_forecast = daily_forecast;
        }

        public static class BasicBean {
            /**
             * update : {"utc":"2017-04-16 12:10","loc":"2017-04-16 20:10"}
             * id : CN10101010001A
             * lon : 116.43
             * cnty : 中国
             * lat : 39.9
             * city : 北京明城墙遗址公园
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

            public static class UpdateBean {
                /**
                 * utc : 2017-04-16 12:10
                 * loc : 2017-04-16 20:10
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

        public static class DailyForecastBean {
            /**
             * wind : {"dir":"无持续风向","sc":"微风"}
             * astro : {"sr":"05:34","ss":"18:53"}
             * tmp : {"min":"16"}
             * date : 2017-04-16
             * cond : {"txt_n":"小雨","code_n":"305"}
             */

            private WindBean wind;
            private AstroBean astro;
            private TmpBean tmp;
            private String date;
            private CondBean cond;

            public WindBean getWind() {
                return wind;
            }

            public void setWind(WindBean wind) {
                this.wind = wind;
            }

            public AstroBean getAstro() {
                return astro;
            }

            public void setAstro(AstroBean astro) {
                this.astro = astro;
            }

            public TmpBean getTmp() {
                return tmp;
            }

            public void setTmp(TmpBean tmp) {
                this.tmp = tmp;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public CondBean getCond() {
                return cond;
            }

            public void setCond(CondBean cond) {
                this.cond = cond;
            }

            public static class WindBean {
                /**
                 * dir : 无持续风向
                 * sc : 微风
                 */

                private String dir;
                private String sc;

                public String getDir() {
                    return dir;
                }

                public void setDir(String dir) {
                    this.dir = dir;
                }

                public String getSc() {
                    return sc;
                }

                public void setSc(String sc) {
                    this.sc = sc;
                }
            }

            public static class AstroBean {
                /**
                 * sr : 05:34
                 * ss : 18:53
                 */

                private String sr;
                private String ss;

                public String getSr() {
                    return sr;
                }

                public void setSr(String sr) {
                    this.sr = sr;
                }

                public String getSs() {
                    return ss;
                }

                public void setSs(String ss) {
                    this.ss = ss;
                }
            }

            public static class TmpBean {
                /**
                 * min : 16
                 */

                private String min;

                public String getMin() {
                    return min;
                }

                public void setMin(String min) {
                    this.min = min;
                }
            }

            public static class CondBean {
                /**
                 * txt_n : 小雨
                 * code_n : 305
                 */

                private String txt_n;
                private String code_n;

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
            }
        }
    }
}
