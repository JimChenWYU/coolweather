package com.coolweather.android.ui.scenic;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.coolweather.android.R;
import com.coolweather.android.http.ApiFactory;
import com.coolweather.android.http.BaseScenicWeatherResponse;
import com.coolweather.android.http.api.ApiGlobal;
import com.coolweather.android.layout.CustomLinearLayoutManager;
import com.coolweather.android.ui.base.BaseContentFragment;
import com.coolweather.android.ui.scenic.adapter.LineScenicAdapter;
import com.coolweather.android.utils.ACache;
import com.coolweather.android.utils.Logger;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lenovo on 2017/4/15.
 */

public class ScenicLineFragment extends BaseContentFragment implements View.OnClickListener{

    protected CustomLinearLayoutManager layoutManager;
    protected RecyclerView recyclerView;
    protected FloatingActionButton backUpButton;
    protected LineScenicAdapter adapter;
    protected Subscription subscription;

    protected ACache mCache;

    protected int currentPage = 1;
    private int totalData = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_line_scenic;
    }

    @Override
    protected void initViews() {
        super.initViews();
        // TODO: 2017/4/16 缓存
        mCache = ACache.get(getActivity());
        recyclerView = findView(R.id.rv_line_scenic);
        layoutManager = new CustomLinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        backUpButton = (FloatingActionButton) getActivity().findViewById(R.id.back_up);
        backUpButton.setVisibility(View.INVISIBLE);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new LineScenicAdapter(R.layout.item_scenic_line, null);
        adapter.setAutoLoadMoreSize(2);
        adapter.setOnLoadMoreListener(new RequestLoadMoreListener(), recyclerView);
        adapter.openLoadAnimation();
        recyclerView.setAdapter(adapter);
        backUpButton.setOnClickListener(this);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private static final String TAG = "ScenicLineFragment";
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                int firstCompleteVisiblesItems = ((LinearLayoutManager) layoutManager).findFirstCompletelyVisibleItemPosition();
                //向下滚动
                if (dy >= 0 && firstCompleteVisiblesItems > 1) {
                    backUpButton.setVisibility(View.VISIBLE);
                } else if (dy >= 0) {
                    backUpButton.setVisibility(View.INVISIBLE);
                } else if (dy <= 0 && firstCompleteVisiblesItems<=1) {
                    backUpButton.setVisibility(View.INVISIBLE);
                } else if (dy <= 0) {
                    backUpButton.setVisibility(View.VISIBLE);
                }
            }
        });
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
        super.onDestroy();
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    private Observable<BaseScenicWeatherResponse> loadData() {
        return ApiFactory
                .getWeatherController()
                .getScenics(currentPage)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_up:
                // TODO: 2017/4/16 无法滚动到顶部
                Logger.i("Scroll To Position", "-----------------");
                recyclerView.smoothScrollToPosition(0);
//                layoutManager.scrollToPositionWithOffset(0, 0);
                break;
        }
    }

    class RequestLoadMoreListener implements BaseQuickAdapter.RequestLoadMoreListener {
        @Override
        public void onLoadMoreRequested() {
            int adapterDataSize = adapter.getData().size();
            if (adapterDataSize < 10 || adapterDataSize == totalData) {
                adapter.loadMoreEnd();
                return;
            }
            recyclerView.post(new Runnable() {
                @Override
                public void run() {
                    subscription = loadData()
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new ScenicWeatherObserver() {
                                @Override
                                public void onNext(BaseScenicWeatherResponse response) {
                                    super.onNext(response);
                                    adapter.addData(response.data);
                                }
                            });
                }
            });
        }
    }

    abstract class ScenicWeatherObserver implements Observer<BaseScenicWeatherResponse> {

        private static final String TAG = "ScenicWeatherObserver";

        @Override
        public void onCompleted() {
            adapter.loadMoreComplete();
            showRefreshing(false);
        }

        @Override
        public void onError(Throwable e) {
            adapter.loadMoreFail();
            showRefreshing(false);
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
                adapter.loadMoreEnd();
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
}
