package com.coolweather.android.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by lenovo on 2017/4/11.
 */

public class TimeUtils {
    private TimeUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static final SimpleDateFormat DEFAULT_SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

    public static final SimpleDateFormat UTC_SDF = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault());

    public static final SimpleDateFormat DATE_SDF = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    public static final SimpleDateFormat HOUR_SDF = new SimpleDateFormat("HH:mm", Locale.getDefault());

    /**
     * 将Date类型转为时间字符串
     * <p>格式为用户自定义</p>
     *
     * @param time   Date类型时间
     * @param format 时间格式
     * @return 时间字符串
     */
    public static String date2String(Date time, SimpleDateFormat format) {
        return format.format(time);
    }

    /**
     * 将Date类型转为时间字符串
     * <p>格式为yyyy-MM-dd HH:mm:ss</p>
     *
     * @param time Date类型时间
     * @return 时间字符串
     */
    public static String date2String(Date time) {
        return date2String(time, DEFAULT_SDF);
    }

    /**
     * 将时间字符串转为Date类型
     * <p>格式为用户自定义</p>
     *
     * @param time   时间字符串
     * @param format 时间格式
     * @return Date类型
     */
    public static Date string2Date(String time, SimpleDateFormat format) {
        return new Date(string2Milliseconds(time, format));
    }

    /**
     * 将时间字符串转为时间戳
     * <p>格式为用户自定义</p>
     *
     * @param time   时间字符串
     * @param format 时间格式
     * @return 毫秒时间戳
     */
    public static long string2Milliseconds(String time, SimpleDateFormat format) {
        try {
            return format.parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 获取某个日期的前几天的日期
     *
     * @param dateString 某日期
     * @param dayNumber  前面第几天
     * @return
     */
    public static String getPreviousDay(String dateString, int dayNumber) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day - dayNumber);

        return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
    }

    /**
     * 获取当前时间
     * <p>格式为yyyy-MM-dd HH:mm:ss</p>
     *
     * @return 时间字符串
     */
    public static String getCurTimeString() {
        return date2String(new Date());
    }



    /**
     * 获取当前时间
     * <p>格式为用户自定义</p>
     *
     * @param format 时间格式
     * @return 时间字符串
     */
    public static String getCurTimeString(SimpleDateFormat format) {
        return date2String(new Date(), format);
    }

    /**
     * 获取星期
     *
     * @param time   时间字符串
     * @param format 时间格式
     * @return 星期
     */
    public static String getWeek(String time, SimpleDateFormat format) {
        return new SimpleDateFormat("EEEE", Locale.getDefault()).format(string2Date(time, format));
    }

    public static String string2String(String time, SimpleDateFormat fromFormat, SimpleDateFormat toFormat) {
        return TimeUtils.date2String(TimeUtils.string2Date(time, fromFormat), toFormat);
    }

    /**
     *  距离明天凌晨的秒数
     *
     * @return long
     */
    public static long getUntilTomorrowTime() {
        Date today = new Date();
        Date tomorrow = null;
        Calendar todayDate = Calendar.getInstance();
        Calendar tomorrowDate = Calendar.getInstance();
        todayDate.setTime(today);
        tomorrowDate.setTime(today);
        tomorrowDate.add(Calendar.DATE, 1);
        try {
            tomorrow = DATE_SDF.parse(DATE_SDF.format(tomorrowDate.getTime()));
            tomorrowDate.setTime(tomorrow);
            System.out.println(tomorrowDate.getTime());
            System.out.println(todayDate.getTime());
            System.out.println(tomorrowDate.getTimeInMillis());
            System.out.println(todayDate.getTimeInMillis());
            return ((tomorrowDate.getTimeInMillis() - todayDate.getTimeInMillis()) / 1000);
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
