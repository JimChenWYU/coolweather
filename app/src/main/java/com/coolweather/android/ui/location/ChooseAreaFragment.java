package com.coolweather.android.ui.location;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.coolweather.android.R;
import com.coolweather.android.http.ApiFactory;
import com.coolweather.android.http.BaseCityResponse;
import com.coolweather.android.http.BaseCountyResponse;
import com.coolweather.android.http.BaseProvinceResponse;
import com.coolweather.android.http.BaseWeatherResponse;
import com.coolweather.android.http.api.ApiGlobal;
import com.coolweather.android.model.City;
import com.coolweather.android.model.County;
import com.coolweather.android.model.Province;
import com.coolweather.android.ui.MainActivity;
import com.coolweather.android.ui.base.BaseContentFragment;
import com.coolweather.android.ui.weather.WeatherFragment;
import com.coolweather.android.utils.ACache;
import com.coolweather.android.utils.Logger;

import org.litepal.crud.DataSupport;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lenovo on 2017/4/12.
 */

public class ChooseAreaFragment extends BaseContentFragment implements AdapterView.OnItemClickListener, View.OnClickListener{

    private static final int LEVEL_PROVINCE = 1;
    private static final int LEVEL_CITY = 2;
    private static final int LEVEL_COUNTY = 3;

    private TextView mTitleText;
    private Button mBackButton;
    private ListView mListView;
    private ArrayAdapter<String> adapter;

    private List<String> regionList = new ArrayList<>();
    private List<Province> provinceList = new ArrayList<>();
    private List<City> cityList = new ArrayList<>();
    private List<County> countyList = new ArrayList<>();

    private Province selectedProvince;
    private City selectedCity;

    private int currentLevel;

    private Subscription subscription;

    private ACache mCache;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_choose_area;
    }

    @Override
    protected void initViews() {
        super.initViews();
        mCache = ACache.get(getActivity());
        mBackButton = findView(R.id.choose_area_back);
        mTitleText = findView(R.id.choose_area_title);
        mListView = findView(R.id.area_list);
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, regionList);
        mListView.setAdapter(adapter);
    }

    @Override
    protected void lazyFetchData() {
        currentLevel = LEVEL_PROVINCE;
        queryProvinces();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mListView.setOnItemClickListener(this);
        mBackButton.setOnClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (currentLevel) {
            case LEVEL_PROVINCE:
                selectedProvince = provinceList.get(position);
                queryCities();
                break;
            case LEVEL_CITY:
                selectedCity = cityList.get(position);
                queryCounties();
                break;
            case LEVEL_COUNTY:
                queryWeatherFromServer(countyList.get(position).getWeatherId());
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.choose_area_back:
                onBackButton();
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    private void onBackButton() {
        if (currentLevel == LEVEL_COUNTY) {
            queryCities();
        } else if (currentLevel == LEVEL_CITY) {
            queryProvinces();
        } else if (currentLevel == LEVEL_PROVINCE) {
            getActivity().onBackPressed();
        }
    }

    private void queryProvinces() {
        mTitleText.setText(R.string.country);
        provinceList = DataSupport.findAll(Province.class);
        if (provinceList.size() > 0) {
            regionList.clear();
            for (Province province : provinceList) {
                regionList.add(province.getName());
            }
            adapter.notifyDataSetChanged();
            mListView.setSelection(0);
            currentLevel = LEVEL_PROVINCE;
        } else {
            queryFromServer(LEVEL_PROVINCE);
        }
    }

    private void queryCities() {
        mTitleText.setText(selectedProvince.getName());
        cityList = DataSupport.where(
                "provinceid = ?", String.valueOf(selectedProvince.getId()))
                .find(City.class);
        if (cityList.size() > 0) {
            regionList.clear();
            for (City city : cityList) {
                regionList.add(city.getName());
            }
            adapter.notifyDataSetChanged();
            mListView.setSelection(0);
            currentLevel = LEVEL_CITY;
        } else {
            queryFromServer(LEVEL_CITY);
        }
    }

    private void queryCounties() {
        mTitleText.setText(selectedCity.getName());
        countyList = DataSupport.where(
                "cityid = ?", String.valueOf(selectedCity.getId()))
                .find(County.class);
        if (countyList.size() > 0) {
            regionList.clear();
            for (County county : countyList) {
                regionList.add(county.getName());
            }
            adapter.notifyDataSetChanged();
            mListView.setSelection(0);
            currentLevel = LEVEL_COUNTY;
        } else {
            queryFromServer(LEVEL_COUNTY);
        }
    }

    private void queryFromServer(@RegionLevel int regionType) {
        showRefreshing(true);
        switch (regionType) {
            case LEVEL_PROVINCE:
                queryProvincesFromServer();
                break;
            case LEVEL_CITY:
                queryCitiesFromServer();
                break;
            case LEVEL_COUNTY:
                queryCountiesFromServer();
                break;
        }
    }

    private void queryProvincesFromServer() {
        subscription = ApiFactory
                .getRegionController()
                .getProvince()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseProvinceResponse>() {
                    @Override
                    public void onCompleted() {
                        showRefreshing(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e("Fetch Region Error", e.getMessage());
                        fetchError();
                    }

                    @Override
                    public void onNext(BaseProvinceResponse response) {
                        if (response == null || !response.status.equals(ApiGlobal.OK)) {
                            fetchError();
                            return;
                        }
                        Province.saveAll(response.data);
                        queryProvinces();
                    }

                    private void fetchError() {
                        Toast.makeText(getActivity(), "获取地区失败！", Toast.LENGTH_SHORT).show();
                    }
                });


    }

    private void queryCitiesFromServer() {
        int provinceCode = selectedProvince.getProvinceCode();
        subscription = ApiFactory
                .getRegionController()
                .getCity(provinceCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseCityResponse>() {
                    @Override
                    public void onCompleted() {
                        showRefreshing(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        fetchError();
                        Logger.e("Fetch City Error", e.getMessage());
                    }

                    @Override
                    public void onNext(BaseCityResponse response) {
                        if (response == null || !response.status.equals(ApiGlobal.OK)) {
                            fetchError();
                            return;
                        }
                        City.saveAll(response.data, selectedProvince.getId());
                        queryCities();
                    }

                    private void fetchError() {
                        Toast.makeText(getActivity(), "获取地区失败！", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void queryCountiesFromServer() {
        int provinceCode = selectedProvince.getProvinceCode();
        int cityCode = selectedCity.getCityCode();
        subscription = ApiFactory
                .getRegionController()
                .getCounty(provinceCode, cityCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseCountyResponse>() {
                    @Override
                    public void onCompleted() {
                        showRefreshing(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        showRefreshing(false);
                        fetchError();
                        Logger.e("Fetch County Error", e.getMessage());
                    }

                    @Override
                    public void onNext(BaseCountyResponse response) {
                        if (response == null || !response.status.equals(ApiGlobal.OK)) {
                            fetchError();
                            return;
                        }
                        County.saveAll(response.data, selectedCity.getId());
                        queryCounties();
                    }

                    private void fetchError() {
                        Toast.makeText(getActivity(), "获取地区失败！", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void queryWeatherFromServer(String city) {
        showRefreshing(true);
        subscription = ApiFactory
                .getWeatherController()
                .getWeather(city)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseWeatherResponse>() {
                    @Override
                    public void onCompleted() {
                        showRefreshing(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        fetchError();
                        Logger.e("Fetch Weather Error", e.getMessage());
                    }

                    @Override
                    public void onNext(BaseWeatherResponse response) {
                        if (response == null || !response.status.equals(ApiGlobal.OK)) {
                            fetchError();
                            return;
                        }
                        mCache.put(WeatherFragment.CACHE_WEATHER_NAME, response.data, 10 * 60);
                        startActivity(new Intent(getActivity(), MainActivity.class));
                        getActivity().finish();
                    }

                    private void fetchError() {
                        Toast.makeText(getActivity(), "获取天气失败！", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @IntDef({LEVEL_PROVINCE, LEVEL_CITY, LEVEL_COUNTY})
    @Retention(RetentionPolicy.SOURCE)
    private @interface RegionLevel {
    }
}
