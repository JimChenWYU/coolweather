package com.coolweather.android.ui.weather;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.coolweather.android.R;
import com.coolweather.android.http.ApiFactory;
import com.coolweather.android.http.BaseBingPicResponse;
import com.coolweather.android.http.BaseWeatherResponse;
import com.coolweather.android.http.api.ApiGlobal;
import com.coolweather.android.location.RxLocation;
import com.coolweather.android.model.HeWeather5;
import com.coolweather.android.ui.MainActivity;
import com.coolweather.android.ui.base.BaseContentFragment;
import com.coolweather.android.ui.location.ChooseAreaActivity;
import com.coolweather.android.ui.weather.adapter.WeatherAdapter;
import com.coolweather.android.utils.ACache;
import com.coolweather.android.utils.Logger;
import com.coolweather.android.utils.SettingsUtil;
import com.coolweather.android.utils.ShareUtils;
import com.coolweather.android.utils.TTSManager;
import com.coolweather.android.utils.TimeUtils;
import com.coolweather.android.utils.WeatherUtil;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by lenovo on 2017/4/11.
 */

public class WeatherFragment extends BaseContentFragment implements Toolbar.OnMenuItemClickListener, View.OnClickListener{

    public static final String CACHE_WEATHER_NAME = "weather_cache";
    public static final String CACHE_BACKGROUND = "bing_pic_cache";

    private Toolbar mToolbar;
    private FloatingActionButton mFloatingActionButton;
    private TextView tvCityName;
    private TextView tvNowWeatherString;
    private TextView tvNowTemp;
    private TextView tvUpdateTime;
    private TextView tvAqi;

    private RecyclerView recyclerView;
    private WeatherAdapter adapter;

    private HeWeather5.DataBean currentWeather;
    private ACache mCache;

    private Subscription subscription;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_weather;
    }

    @Override
    protected void initViews() {
        super.initViews();
        mCache = ACache.get(getActivity());
        mToolbar = findView(R.id.toolbar);
        mToolbar.setTitle(R.string.weather);
        ((MainActivity)getActivity()).initDrawer(mToolbar);
        mToolbar.inflateMenu(R.menu.menu_weather);
        // TODO: 2017/4/11
        mFloatingActionButton = findView(R.id.locate);

        tvCityName = findView(R.id.tv_city_name);
        tvNowWeatherString = findView(R.id.tv_weather_string);
        tvNowTemp = findView(R.id.tv_temp);
        tvUpdateTime = findView(R.id.tv_update_time);
        tvAqi = findView(R.id.tv_weather_aqi);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView = findView(R.id.rv_weather);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new WeatherAdapter(null);
        adapter.openLoadAnimation();
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mToolbar.setOnMenuItemClickListener(this);
        mFloatingActionButton.setOnClickListener(this);
    }

    @Override
    protected void lazyFetchData() {
        getBingPic();
        if (!locateWeather()) {
            getWeatherData();
        }
    }

    private boolean locateWeather() {
        Intent intent = getActivity().getIntent();
        HeWeather5.DataBean weather = (HeWeather5.DataBean) intent.getSerializableExtra(CACHE_WEATHER_NAME);
        if (weather != null) {
            showWeather(weather);
            showRefreshing(false);
            return true;
        }
        return false;
    }

    private void showWeather(HeWeather5.DataBean weather) {
        currentWeather = weather;
        tvCityName.setText(weather.getBasic().getCity());
        mToolbar.setTitle(String.format("%s天气", weather.getBasic().getCity()));
        tvNowWeatherString.setText(weather.getNow().getCond().getTxt());
        tvAqi.setText(weather.getAqi() == null ? "" : weather.getAqi().getCity().getQlty());
        tvNowTemp.setText(String.format("%s ℃", weather.getNow().getTmp()));
        String updateTime = TimeUtils.string2String(
                weather.getBasic().getUpdate().getLoc(),
                new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()), new SimpleDateFormat("HH:mm", Locale.getDefault()));
        tvUpdateTime.setText(String.format("截止 %s", updateTime));

        List<MultiItemEntity> weathers = new ArrayList<>();

        HeWeather5.DataBean nowWeather = (HeWeather5.DataBean) weather.clone();
        nowWeather.setItemType(HeWeather5.DataBean.TYPE_NOW);
        weathers.add(nowWeather);

        weathers.add(weather.getSuggestion());

        HeWeather5.DataBean dailyWeather = (HeWeather5.DataBean) weather.clone();
        dailyWeather.setItemType(HeWeather5.DataBean.TYPE_DAILYFORECAST);
        weathers.add(dailyWeather);

        adapter.setNewData(weathers);
    }

    private void shareWeather() {
        // TODO: 2017/4/11
        if (currentWeather == null) {
            return;
        }

        String shareType = SettingsUtil.getWeatherShareType();
        if (shareType.equals("纯文本")) {
            ShareUtils.shareText(getActivity(), WeatherUtil.getShareMessage(currentWeather));
        } else if (shareType.equals("仿锤子便签")){
            ShareActivity.actionStart(getActivity(), WeatherUtil.getShareMessage(currentWeather));
        }
    }

    private void showBingPic(String url) {
        Glide.with(getActivity()).load(url).into((ImageView) findView(R.id.bannner));
    }

    private void getBingPic() {
        String cacheBingPic = mCache.getAsString(CACHE_BACKGROUND);
        if (cacheBingPic != null) {
            showBingPic(cacheBingPic);
            return;
        }
        subscription = ApiFactory
                .getWeatherController()
                .getBingPic()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseBingPicResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e("BingPic onError", e.getMessage());
                        fetchError();
                    }

                    @Override
                    public void onNext(BaseBingPicResponse response) {
                        if (response == null || !response.status.equals(ApiGlobal.OK)) {
                            fetchError();
                            return;
                        }
                        showBingPic(response.data);
                        mCache.put(CACHE_BACKGROUND, response.data, (int) TimeUtils.getUntilTomorrowTime());
                    }

                    private void fetchError() {
                        Toast.makeText(getActivity(), "获取背景图失败！", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void getWeatherData() {
        showRefreshing(true);
        HeWeather5.DataBean cacheWeather = (HeWeather5.DataBean) mCache.getAsObject(CACHE_WEATHER_NAME);
        if (cacheWeather != null) {
            Logger.d("cacheWeather", cacheWeather.toString());
            showWeather(cacheWeather);
            showRefreshing(false);
            return;
        }

        Logger.d("reloadData", ".......................");
        subscription = RxLocation.get().locate(getActivity())
                .flatMap(new Func1<BDLocation, Observable<BaseWeatherResponse>>() {
                    @Override
                    public Observable<BaseWeatherResponse> call(BDLocation bdLocation) {
                        String city = "";
                        if (!TextUtils.isEmpty(bdLocation.getCity())) {
                            city = bdLocation.getCity().replace("市", "");
                        }

                        tvCityName.setText(city);
                        return ApiFactory
                                .getWeatherController()
                                .getWeather(city)
                                .subscribeOn(Schedulers.io());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseWeatherResponse>() {
                    @Override
                    public void onCompleted() {
                        showRefreshing(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        showRefreshing(false);
                        fetchError();
                    }

                    @Override
                    public void onNext(BaseWeatherResponse response) {
                        if (response == null || !response.status.equals(ApiGlobal.OK)) {
                            fetchError();
                            return;
                        }
                        Logger.e("debug", response.toString());

                        showWeather(response.data);
                        mCache.put(CACHE_WEATHER_NAME, response.data, 10 * 60);
                        WeatherUtil.saveDailyHistory(response.data);
                    }

                    private void fetchError() {
                        Snackbar.make(getView(), "获取天气失败！", Snackbar.LENGTH_INDEFINITE)
                                .setAction("重试", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        lazyFetchData();
                                    }
                                })
                                .setActionTextColor(getActivity().getResources().getColor(R.color.actionColor))
                                .show();
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_share:
                new RxPermissions(getActivity())
                        .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .subscribe(new Action1<Boolean>() {
                            @Override
                            public void call(Boolean result) {
                                if (result) {
                                    shareWeather();
                                }
                            }
                        });
                return true;
            case R.id.menu_tts:
                TTSManager.getInstance(getActivity()).speak(WeatherUtil.getShareMessage(currentWeather), null);
                return true;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.locate:
                startActivity(new Intent(getActivity(), ChooseAreaActivity.class));
                break;
        }
    }
}