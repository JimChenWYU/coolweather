package com.coolweather.android;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.coolweather.android.gson.Forecast;
import com.coolweather.android.gson.Weather;
import com.coolweather.android.service.AutoUpdateService;
import com.coolweather.android.util.HttpUtil;
import com.coolweather.android.util.LogUtil;
import com.coolweather.android.util.Utility;
import com.coolweather.android.util.Utils;

import java.io.IOException;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WeatherActivity extends BaseActivity {

    private static final String TAG = "WeatherActivity";

    private String mWeatherId;

    private ProgressDialog progressDialog;

    @BindView(R.id.title_city)
    TextView titleCity;
    @BindView(R.id.title_update_time)
    TextView titleUpdateTime;
    @BindView(R.id.degree_text)
    TextView degreeText;
    @BindView(R.id.weather_info_text)
    TextView weatherInfoText;
    @BindView(R.id.forecast_layout)
    LinearLayout forecastLayout;
    @BindView(R.id.aqi_text)
    TextView aqiText;
    @BindView(R.id.pm25_text)
    TextView pm25Text;
    @BindView(R.id.comfort_text)
    TextView comfortText;
    @BindView(R.id.car_wash_text)
    TextView carWashText;
    @BindView(R.id.sport_text)
    TextView sportText;
    @BindView(R.id.weather_layout)
    ScrollView weatherLayout;
    @BindView(R.id.bing_pc_img)
    ImageView bingPcImg;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.nav_button)
    Button navButton;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        ButterKnife.bind(this);

        /**
         * 加载背景图
         */
        loadBackgroundImage();

        /**
         * 状态栏融合背景图
         */
        hideStatusBar();

        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String weatherString = preferences.getString("weather", null);

        if (weatherString != null) {
            Weather weather = Utility.handleWeatherResponse(weatherString);
            mWeatherId = weather.getBasic().getId();
            showWeatherInfo(weather);
        } else {
            mWeatherId = getIntent().getStringExtra("weather_id");
            weatherLayout.setVisibility(View.INVISIBLE);
            requestWeather(mWeatherId);
        }

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestWeather(mWeatherId);
            }
        });
        weatherLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return drawerLayout.isDrawerOpen(Gravity.LEFT);
            }
        });
    }

    @OnClick(R.id.nav_button)
    void onClick() {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    private void hideStatusBar() {
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    public void loadBackgroundImage() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String bingPc = prefs.getString("bing_pic", null);
        if (bingPc != null) {
            Glide.with(this).load(bingPc).into(bingPcImg);
        } else {
            loadBingPic();
        }
    }

    private void loadBingPic() {
        String requestBingPic = "http://guolin.tech/api/bing_pic";
        HttpUtil.sendOkHttpRequest(requestBingPic, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String bingPic = response.body().string();
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(WeatherActivity.this).edit();
                editor.putString("bing_pic", bingPic);
                editor.apply();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        loadBackgroundImage();
                    }
                });
            }
        });
    }

    public void requestWeather(String weatherId) {
        String weatherUrl = "http://guolin.tech/api/weather?cityid=" + weatherId + "&key=" + Utils.getEnvConfig(this).getValue("key");
        mWeatherId = weatherId;
        HttpUtil.sendOkHttpRequest(weatherUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        LogUtil.d(TAG, "onFailure");
                        Toast.makeText(WeatherActivity.this, "获取天气信息失败", Toast.LENGTH_SHORT).show();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                final Weather weather = Utility.handleWeatherResponse(responseText);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (weather != null && weather.getStatus().equals("ok")) {
                            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(WeatherActivity.this).edit();
                            editor.putString("weather", responseText);
                            editor.apply();
                            showWeatherInfo(weather);
                        } else {
//                            LogUtil.d(TAG, "onResponse: " + weather.getStatus());
                            Toast.makeText(WeatherActivity.this, "获取天气信息失败", Toast.LENGTH_SHORT).show();
                        }
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        });
        loadBingPic();
    }

    private void showWeatherInfo(Weather weather) {
        if (weather != null && weather.getStatus().equals("ok")) {
            String cityName = weather.getBasic().getCity();
            String updateName = weather.getBasic().getUpdate().getLoc().split(" ")[1];
            String degree = weather.getNow().getTmp() + "℃";
            String weatherInfo = weather.getNow().getCond().getTxt();
            titleCity.setText(cityName);
            titleUpdateTime.setText(updateName);
            degreeText.setText(degree);
            weatherInfoText.setText(weatherInfo);

            forecastLayout.removeAllViews();
            for (Forecast forecast : weather.getForecastList()) {
                View view = LayoutInflater.from(this).inflate(R.layout.forecast_item, forecastLayout, false);
                TextView dateText = ButterKnife.findById(view, R.id.date_text);
                TextView infoText = ButterKnife.findById(view, R.id.info_text);
                TextView maxText = ButterKnife.findById(view, R.id.max_text);
                TextView minText = ButterKnife.findById(view, R.id.min_text);

                dateText.setText(forecast.getDate());
                infoText.setText(forecast.getCond().getTxt_d());
                maxText.setText(forecast.getTmp().getMax());
                minText.setText(forecast.getTmp().getMin());
                forecastLayout.addView(view);
            }

            if (weather.getAqi() != null) {
                aqiText.setText(weather.getAqi().getCity().getAqi());
                pm25Text.setText(weather.getAqi().getCity().getPm25());
            }

            String comfort = "舒适度：" + weather.getSuggestion().getComf().getTxt();
            String carWash = "洗车指数：" + weather.getSuggestion().getCw().getTxt();
            String sport = "运动建议：" + weather.getSuggestion().getSport().getTxt();

            comfortText.setText(comfort);
            carWashText.setText(carWash);
            sportText.setText(sport);

            weatherLayout.setVisibility(View.VISIBLE);

            Intent intent = new Intent(this, AutoUpdateService.class);
            startService(intent);
        } else {
            Toast.makeText(this, "获取天气信息失败", Toast.LENGTH_SHORT).show();
        }
    }
}
