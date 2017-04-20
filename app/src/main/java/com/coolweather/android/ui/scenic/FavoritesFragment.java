package com.coolweather.android.ui.scenic;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.coolweather.android.R;
import com.coolweather.android.even.ScenicFavoritesEvent;
import com.coolweather.android.model.FavoritesScenicBean;
import com.coolweather.android.model.ScenicBaseDetail;
import com.coolweather.android.ui.base.BaseContentFragment;
import com.coolweather.android.ui.scenic.adapter.FavoritesAdapter;
import com.coolweather.android.utils.Logger;
import com.coolweather.android.utils.RxDataBase;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;

/**
 * Created by lenovo on 2017/4/19.
 */

public class FavoritesFragment extends BaseContentFragment {

    private static final String TAG = "FavoritesFragment";

    private RecyclerView recyclerView;
    private FavoritesAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_scenic_favorites;
    }

    @Override
    protected void initViews() {
        super.initViews();
        recyclerView = findView(R.id.rv_favorites);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new FavoritesAdapter(R.layout.item_scenic_line, null);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void lazyFetchData() {
        showRefreshing(true);
        RxDataBase.getAll(ScenicBaseDetail.class, "isFavorite = ?", "1")
                .map(new Func1<List<ScenicBaseDetail>, List<FavoritesScenicBean>>() {
                    @Override
                    public List<FavoritesScenicBean> call(List<ScenicBaseDetail> lines) {
                        if (lines == null || lines.size() == 0) {
                            return null;
                        }
                        List<FavoritesScenicBean> favList = new ArrayList<>();
                        for (ScenicBaseDetail line : lines) {
                            favList.add(new FavoritesScenicBean(line));
                        }
                        return favList;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<FavoritesScenicBean>>() {
                    @Override
                    public void onCompleted() {
                        showRefreshing(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        showRefreshing(false);
                    }

                    @Override
                    public void onNext(List<FavoritesScenicBean> favList) {
                        adapter.setNewData(favList);
                    }
                });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFavoritesChanged(ScenicFavoritesEvent event) {
        int position = adapter.getData().indexOf(event.getScenicFavorite());
        Logger.i(TAG, event.getScenicFavorite());
        if (position >= 0) {
            if (!event.isFavorite()) {
                adapter.remove(position);
            }
        } else {
            if (event.isFavorite()) {
                adapter.addData(event.getScenicFavorite());
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onDestroy() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        super.onDestroy();
    }
}
