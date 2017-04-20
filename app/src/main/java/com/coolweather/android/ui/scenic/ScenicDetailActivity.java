package com.coolweather.android.ui.scenic;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.coolweather.android.R;
import com.coolweather.android.even.ScenicFavoritesEvent;
import com.coolweather.android.http.ApiFactory;
import com.coolweather.android.http.BaseScenicDetailResponse;
import com.coolweather.android.http.api.ApiGlobal;
import com.coolweather.android.model.FavoritesScenicBean;
import com.coolweather.android.model.HeWeather5;
import com.coolweather.android.model.ScenicBaseDetail;
import com.coolweather.android.ui.base.BaseActivity;
import com.coolweather.android.utils.ACache;
import com.coolweather.android.utils.Logger;
import com.coolweather.android.utils.RxDataBase;
import com.coolweather.android.utils.SizeUtils;
import com.coolweather.android.utils.TimeUtils;
import com.coolweather.android.widgets.WeatherChartView;
import com.coolweather.android.widgets.WeatherDetailsView;

import org.greenrobot.eventbus.EventBus;
import org.litepal.crud.DataSupport;

import java.text.SimpleDateFormat;
import java.util.Locale;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static com.coolweather.android.R.string.scenic;

/**
 * Created by lenovo on 2017/4/16.
 */

public class ScenicDetailActivity extends BaseActivity implements View.OnClickListener{

    private static final String TAG = "ScenicDetailActivity";

    public static final String KEY_EXTRE_SCENIC = "extreScenicId";
    public static final String NAME_EXTRE_SCENIC = "extreScenicName";
    public static final String PROVINCE_EXTRE_SCENIC = "extreScenicProvince";
    public static final String CITY_EXTRE_SCENIC = "extreScenicCity";

    private TextView tvScenicName;
    private TextView tvScenicCountry;
    private TextView tvLon;
    private TextView tvLat;
    private TextView tvUpdateTime;
    private FloatingActionButton fabLike;
    private SwipeRefreshLayout refreshLayout;

    private LinearLayout container;

    private String scenicId;
    private String scenicName;
    private String scenicProvince;
    private String scenicCity;
    private boolean showWeatherChart = true;
    private boolean isFavorite = false;

    private Subscription subscription;

    private ACache mCache;

    public static void start(Context context, String scenicId, String scenicName, String scenicProvince, String scenicCity) {
        Intent intent = new Intent(context, ScenicDetailActivity.class);
        intent.putExtra(KEY_EXTRE_SCENIC, scenicId);
        intent.putExtra(NAME_EXTRE_SCENIC, scenicName);
        intent.putExtra(PROVINCE_EXTRE_SCENIC, scenicProvince);
        intent.putExtra(CITY_EXTRE_SCENIC, scenicCity);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_scenic_detail;
    }

    @Override
    protected int getMenuId() {
        return 0;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setDisplayHomeAsUpEnabled(true);
        mCache = ACache.get(this);
        scenicId = getIntent().getStringExtra(KEY_EXTRE_SCENIC);
        scenicName = getIntent().getStringExtra(NAME_EXTRE_SCENIC);
        scenicProvince = getIntent().getStringExtra(PROVINCE_EXTRE_SCENIC);
        scenicCity = getIntent().getStringExtra(CITY_EXTRE_SCENIC);
        initHeader();
        initContainer();
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.refeshLayout);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });
        fabLike.setOnClickListener(this);
    }

    private void initHeader() {
        getSupportActionBar().setTitle(getString(scenic));
        tvScenicName = (TextView) findViewById(R.id.tv_scenic_name);
        tvScenicCountry = (TextView) findViewById(R.id.tv_scenic_country);
        tvLon = (TextView) findViewById(R.id.tv_lon);
        tvLat = (TextView) findViewById(R.id.tv_lat);
        tvUpdateTime = (TextView) findViewById(R.id.tv_update_time);
        fabLike = (FloatingActionButton) findViewById(R.id.fab_like);
    }

    private void initContainer() {
        container = (LinearLayout) findViewById(R.id.contentLayout);
        container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showWeatherChart = !showWeatherChart;
                loadData();
            }
        });
    }

    private void showScenicWeather(HeWeather5.DataBean weather) {
        toolbar.setTitle(weather.getBasic().getCity());
        tvScenicName.setText(weather.getBasic().getCity());
        tvScenicCountry.setText(weather.getBasic().getCnty());
        tvLon.setText(String.format("经度：%s", weather.getBasic().getLon()));
        tvLat.setText(String.format("纬度：%s", weather.getBasic().getLat()));
        String updateTime = TimeUtils.string2String(
                weather.getBasic().getUpdate().getLoc(),
                new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()), new SimpleDateFormat("HH:mm", Locale.getDefault()));
        tvUpdateTime.setText(String.format("截止 %s", updateTime));
        if (showWeatherChart) {
            container.setPadding(0, SizeUtils.dp2px(this, 16), 0, SizeUtils.dp2px(this, 16));
            container.removeAllViews();
            container.addView(getChartView(weather));
        } else {
            container.removeAllViews();
            container.addView(getDetailsView(weather));
        }
    }

    private WeatherChartView getChartView(HeWeather5.DataBean weather) {
        WeatherChartView chartView = new WeatherChartView(this);
        chartView.setWeather(weather);
        return chartView;
    }

    private WeatherDetailsView getDetailsView(HeWeather5.DataBean weather) {
        WeatherDetailsView detailsView = new WeatherDetailsView(this);
        detailsView.setWeather(weather);
        return detailsView;
    }

    private void showRefreshing(final boolean refresh) {
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(refresh);
            }
        });
    }

    private void showFabStatus(boolean isFavorite) {
        this.isFavorite = isFavorite;
        if (isFavorite) {
            fabLike.setImageResource(R.drawable.ic_favorite);
        } else {
            fabLike.setImageResource(R.drawable.ic_favorite_not);
        }
    }

    @Override
    protected void loadData() {
        showRefreshing(true);

        RxDataBase.getFirst(ScenicBaseDetail.class, "scenicId = ?", scenicId)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Action1<ScenicBaseDetail>() {
                    @Override
                    public void call(ScenicBaseDetail scenicBaseDetail) {
                        Logger.i(TAG, scenicBaseDetail);
                        if (scenicBaseDetail != null) {
                            showFabStatus(scenicBaseDetail.isFavorite());
                        }
                    }
                })
                .flatMap(new Func1<ScenicBaseDetail, Observable<BaseScenicDetailResponse>>() {
                    @Override
                    public Observable<BaseScenicDetailResponse> call(ScenicBaseDetail scenicBaseDetail) {

                        final HeWeather5.DataBean cacheScenic = (HeWeather5.DataBean) mCache.getAsObject(scenicId);
                        if (cacheScenic != null) {
                            showScenicWeather(cacheScenic);
                            showRefreshing(false);
                            return null;
                        }
                        Logger.i(TAG, scenicId);
                        return ApiFactory
                                .getWeatherController()
                                .getScenicDetailWeather(scenicId)
                                .subscribeOn(Schedulers.io());

                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseScenicDetailResponse>() {
                    @Override
                    public void onCompleted() {
                        showRefreshing(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        fetchError();
                        showRefreshing(false);
                        Logger.e("Fetch Scenic Detail Error", e.getMessage());
                    }

                    @Override
                    public void onNext(BaseScenicDetailResponse response) {
//                        Toast.makeText(ScenicDetailActivity.this, response.status, Toast.LENGTH_SHORT).show();
                        if (response == null || !response.status.equals(ApiGlobal.OK)) {
                            fetchError();
                            return;
                        }

                        showScenicWeather(response.data);
                        mCache.put(scenicId, response.data, (int) TimeUtils.getUntilTomorrowTime());
                        saveScenicDetail();
                    }

                    private void fetchError() {
                        Toast.makeText(ScenicDetailActivity.this, "获取景点天气失败！", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void saveScenicDetail() {
        ScenicBaseDetail detail = new ScenicBaseDetail();
        detail.setScenicId(scenicId);
        detail.setName(scenicName);
        detail.setProvince(scenicProvince);
        detail.setCity(scenicCity);
        detail.setFavorite(isFavorite);
        RxDataBase.deleteThenSave(detail, "scenicId = ?", scenicId);
    }

    @Override
    protected void ButterKnifeBind() {

    }

    @Override
    protected void onDestroy() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_like:
                isFavorite = !isFavorite;
                ContentValues values = new ContentValues();
                values.put("isFavorite", isFavorite);
                DataSupport.updateAll(ScenicBaseDetail.class, values, "scenicId = ?", scenicId);
                showFabStatus(isFavorite);
                // TODO: 2017/4/19
                FavoritesScenicBean detail = new FavoritesScenicBean();
                detail.setId(scenicId);
                detail.setName(scenicName);
                detail.setProvince(scenicProvince);
                detail.setCity(scenicCity);
                ScenicFavoritesEvent event = new ScenicFavoritesEvent(detail);
                event.setFavorite(isFavorite);
                EventBus.getDefault().post(event);
                break;
        }
    }
}
