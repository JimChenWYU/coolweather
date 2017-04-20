package com.coolweather.android.utils;

import com.coolweather.android.App;
import com.coolweather.android.R;

/**
 * Created by lenovo on 2017/4/15.
 */

public class SettingsUtil {
    public static final String WEATHER_SHARE_TYPE = "weather_share_type";//天气分享形式
    public static final String THEME = "theme_color";//主题
    public static final String CLEAR_CACHE = "clean_cache";//清空缓存
    //public static final String BUS_REFRESH_FREQ = "bus_refresh_freq";//公交自动刷新频率
    public static final String TTS_VOICE_TYPE = "tts_voice_type";//讯飞语音人声

    public static void setTtsVoiceType(String ttsVoiceType) {
        SPUtil.put(App.getContext(), TTS_VOICE_TYPE, ttsVoiceType);
    }

    public static String getTtsVoiceType() {
        String defaultVoice = App.getContext().getResources().getStringArray(R.array.tts_voice_value)[0];
        return (String) SPUtil.get(App.getContext(), TTS_VOICE_TYPE, defaultVoice);
    }

    public static void setWeatherShareType(String weatherShareType) {
        SPUtil.put(App.getContext(), WEATHER_SHARE_TYPE, weatherShareType);
    }

    public static String getWeatherShareType() {
        String defaultWeather = App.getContext().getResources().getStringArray(R.array.share_type)[0];
        return (String) SPUtil.get(App.getContext(), WEATHER_SHARE_TYPE, defaultWeather);
    }
    
    public static void setTheme(int themeIndex) {
        SPUtil.put(App.getContext(), THEME, themeIndex);
    }

    public static int getTheme() {
        return (int) SPUtil.get(App.getContext(), THEME, 0);
    }

    // TODO: 2017/4/15 Bus
}
