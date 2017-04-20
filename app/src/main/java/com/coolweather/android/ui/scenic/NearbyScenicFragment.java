package com.coolweather.android.ui.scenic;

import android.text.TextUtils;

import com.baidu.location.BDLocation;
import com.coolweather.android.http.ApiFactory;
import com.coolweather.android.http.BaseScenicWeatherResponse;
import com.coolweather.android.location.RxLocation;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by lenovo on 2017/4/17.
 */

public class NearbyScenicFragment extends ScenicLineFragment {

    private static final String CACHE_SCENIC_PROVINCE = "CACHE_SCENIC_PROVINCE";
    private static final String CACHE_SCENIC_CITY = "CACHE_SCENIC_CITY";

    @Override
    protected int getLayoutId() {
        return super.getLayoutId();
    }

    @Override
    protected void initViews() {
        super.initViews();
        adapter.setOnLoadMoreListener(new RequestLoadMoreListener(), recyclerView);
    }

    @Override
    protected void lazyFetchData() {
        showRefreshing(true);
        currentPage = 1;
        subscription = loadData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ScenicWeatherObserver() {
                    @Override
                    public void onNext(BaseScenicWeatherResponse response) {
                        super.onNext(response);
                        adapter.setNewData(response.data);
                    }
                });
    }

    @Override
    public void onDestroy() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        super.onDestroy();
    }

    private Observable<BaseScenicWeatherResponse> loadData() {
        String province = mCache.getAsString(CACHE_SCENIC_PROVINCE);
        String city = mCache.getAsString(CACHE_SCENIC_CITY);

//        Logger.i("Province", province);
//        Logger.i("City", city);
        if (province != null && city != null
                && !province.equals("") && !city.equals("")) {
            Map<String, String> options = new HashMap<>();
            options.put("province", province);
            options.put("city", city);
            return ApiFactory
                    .getWeatherController()
                    .getScenics(currentPage, options)
                    .subscribeOn(Schedulers.io());
        }
        return RxLocation.get().locate(getActivity())
                .flatMap(new Func1<BDLocation, Observable<BaseScenicWeatherResponse>>() {
                    @Override
                    public Observable<BaseScenicWeatherResponse> call(BDLocation bdLocation) {
                        String province = TextUtils.isEmpty(bdLocation.getProvince()) ? "" : bdLocation.getProvince().replace("省", "");
                        String city = TextUtils.isEmpty(bdLocation.getCity()) ? "" : bdLocation.getCity().replace("市", "");
                        Map<String, String> options = new HashMap<>();
                        options.put("province", province);
                        options.put("city", city);

                        mCache.put(CACHE_SCENIC_PROVINCE, province, 60 * 5);
                        mCache.put(CACHE_SCENIC_CITY, city, 60 * 5);

                        return ApiFactory
                                .getWeatherController()
                                .getScenics(currentPage, options)
                                .subscribeOn(Schedulers.io());
                    }
                });
    }
}
