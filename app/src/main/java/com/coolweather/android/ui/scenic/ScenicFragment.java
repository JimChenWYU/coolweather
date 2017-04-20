package com.coolweather.android.ui.scenic;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.coolweather.android.R;
import com.coolweather.android.http.ApiFactory;
import com.coolweather.android.http.BaseScenicWeatherResponse;
import com.coolweather.android.http.api.ApiGlobal;
import com.coolweather.android.ui.MainActivity;
import com.coolweather.android.ui.base.BaseFragment;
import com.coolweather.android.ui.scenic.adapter.LineSearchAdapter;
import com.coolweather.android.utils.ACache;
import com.coolweather.android.utils.Logger;
import com.coolweather.android.utils.WebUtils;
import com.jakewharton.rxbinding.support.v7.widget.RxSearchView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by lenovo on 2017/4/15.
 */

public class ScenicFragment extends BaseFragment implements View.OnKeyListener, View.OnClickListener, View.OnFocusChangeListener, MenuItem.OnMenuItemClickListener, MenuItemCompat.OnActionExpandListener {

    private static final String TAG = "ScenicFragment";

    public static final String CACHE_BACKGROUND = "bing_pic_cache";

    private Toolbar mToolbar;
    private FloatingActionButton mFloatingActionButton;
    private TextView tvCityName;
    private TextView tvNowWeatherString;
    private TextView tvNowTemp;
    private TextView tvUpdateTime;
    private TextView tvAqi;
    private MenuItem search;
    private SearchView searchView;
    private PopupWindow popupWindow;
    private RecyclerView recyclerView;
    private LineSearchAdapter searchAdapter;

    private TextView tvScenicAll;

    private int currentPage = 1;
    private int totalData = 0;
    private Map<String, String> searchOptions = new HashMap<>();

    private ACache mCache;

    private Subscription subscription;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_scenic;
    }

    @Override
    protected void initViews() {
        mToolbar = findView(R.id.toolbar);
        mToolbar.setTitle(R.string.scenic);
        ((MainActivity) getActivity()).initDrawer(mToolbar);

        initTabLayout();
        inflateMenu();
        initSearchView();
    }

    @Override
    protected void lazyFetchData() {
        RxSearchView
            .queryTextChanges(searchView)
            .debounce(600, TimeUnit.MILLISECONDS)
            .subscribeOn(AndroidSchedulers.mainThread())
            .filter(new Func1<CharSequence, Boolean>() {
                @Override
                public Boolean call(CharSequence charSequence) {
                    return charSequence.toString().trim().length() > 0;
                }
            })
            .switchMap(new Func1<CharSequence, Observable<BaseScenicWeatherResponse>>() {
                @Override
                public Observable<BaseScenicWeatherResponse> call(CharSequence charSequence) {
                    searchOptions.clear();
                    searchOptions.put("name", charSequence.toString());
                    currentPage = 1;
                    return loadData();
                }
            })
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new SimpleObserver() {
                @Override
                public void onNext(BaseScenicWeatherResponse response) {
                    super.onNext(response);
                    searchAdapter.getData().clear();
                    searchAdapter.setNewData(response.data);
                    popupWindow.showAsDropDown(searchView);
                }
            });
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
//        Fragment fragment = new NearbyLineFragment();
//        adapter.addFrag(fragment, getString(R.string.bus_nearby_line));
//
//        fragment = new NearbyStationFragment();
//        adapter.addFrag(fragment, getString(R.string.bus_nearby_station));
//
//        fragment = new FavoritesFragment();
//        adapter.addFrag(fragment, getString(R.string.bus_favorites));

        Fragment fragment = new ScenicLineFragment();
        adapter.addFrag(fragment, getString(R.string.city_scenic));

        fragment = new NearbyScenicFragment();
        adapter.addFrag(fragment, getString(R.string.scenic_nearby));

        fragment = new FavoritesFragment();
        adapter.addFrag(fragment, getString(R.string.scenic_favorites));

        viewPager.setAdapter(adapter);

    }

    private void initTabLayout() {
        TabLayout tabLayout = findView(R.id.tabs);
        ViewPager viewPager = findView(R.id.viewPager);
        setupViewPager(viewPager);
        viewPager.setOffscreenPageLimit(viewPager.getAdapter().getCount());
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
    }

    private void inflateMenu() {
        mToolbar.inflateMenu(R.menu.menu_scenic);
        mToolbar.getMenu()
                .findItem(R.id.menu_map)
                .setOnMenuItemClickListener(this);
    }

    private void initSearchView() {
        search = mToolbar.getMenu()
                .findItem(R.id.menu_search);
        searchView = (SearchView) search.getActionView();
        searchView.setOnFocusChangeListener(this);
        searchView.setQueryHint(getString(R.string.scenic_search_query_hint));

        MenuItemCompat.setOnActionExpandListener(search, this);
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.fragment_line_search, null);
        tvScenicAll = (TextView) contentView.findViewById(R.id.tv_scenic_all);
        tvScenicAll.setOnClickListener(this);
        recyclerView = (RecyclerView) contentView.findViewById(R.id.rv_line_search);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        searchAdapter = new LineSearchAdapter(R.layout.item_scenic_line, null);
        searchAdapter.setAutoLoadMoreSize(2);
        searchAdapter.setOnLoadMoreListener(new RequestLoadMoreListener(), recyclerView);
        recyclerView.setAdapter(searchAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState != RecyclerView.SCROLL_STATE_IDLE) {
                    searchView.clearFocus();
                }
            }
        });
        popupWindow = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        WebUtils.openInternet(getContext(), getString(R.string.map_api));
        return false;
    }

    @Override
    public boolean onMenuItemActionExpand(MenuItem item) {
        popupWindow.showAsDropDown(mToolbar);
        return true;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem item) {
        popupWindow.dismiss();
        return true;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getActivity(), "查看更多...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        Logger.i(TAG, hasFocus);
        if (hasFocus) {
            tvScenicAll.setVisibility(View.INVISIBLE);
        } else {
            tvScenicAll.setVisibility(View.VISIBLE);
        }
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    class RequestLoadMoreListener implements BaseQuickAdapter.RequestLoadMoreListener {
        @Override
        public void onLoadMoreRequested() {
            int adapterDataSize = searchAdapter.getData().size();
            if (adapterDataSize < 10 || adapterDataSize == totalData) {
                searchAdapter.loadMoreEnd();
                return;
            }

            recyclerView.post(new Runnable() {
                @Override
                public void run() {
                    subscription = loadData()
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new SimpleObserver() {
                                @Override
                                public void onNext(BaseScenicWeatherResponse response) {
                                    super.onNext(response);
                                    searchAdapter.addData(response.data);
                                }
                            });
                }
            });
        }
    }

    abstract class SimpleObserver implements Observer<BaseScenicWeatherResponse> {

        private static final String TAG = "SimpleObserver";

        @Override
        public void onCompleted() {
            searchAdapter.loadMoreComplete();
        }

        @Override
        public void onError(Throwable e) {
            searchAdapter.loadMoreFail();
            fetchError();
            Logger.e(TAG, e.getMessage());
        }

        @Override
        public void onNext(BaseScenicWeatherResponse response) {
            Logger.i(TAG, response);
            if (response == null || !response.status.equals(ApiGlobal.OK)) {
                fetchError();
                return;
            }

            if (response.data.size() == 0) {
                searchAdapter.loadMoreEnd();
                return;
            }
            totalData = response.total;
            currentPage++;
        }

        private void fetchError() {
            Snackbar.make(getView(), "获取景点失败！", Snackbar.LENGTH_INDEFINITE).setAction("重试", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    lazyFetchData();
                }
            }).setActionTextColor(getActivity().getResources().getColor(R.color.actionColor)).show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getFocus();
    }

    private void getFocus() {
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(this);
    }

    private Observable<BaseScenicWeatherResponse> loadData(Map<String, String> options) {
        return ApiFactory
                .getWeatherController()
                .getScenics(currentPage, options)
                .subscribeOn(Schedulers.io());
    }

    private Observable<BaseScenicWeatherResponse> loadData() {
        return ApiFactory
                .getWeatherController()
                .getScenics(currentPage, searchOptions)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_UP
                && keyCode == KeyEvent.KEYCODE_BACK) {
            if (MenuItemCompat.isActionViewExpanded(search)) {
                MenuItemCompat.collapseActionView(search);
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}
