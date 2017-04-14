package com.coolweather.android.ui.base;

import android.support.v4.widget.SwipeRefreshLayout;

import com.coolweather.android.R;
import com.coolweather.android.utils.Logger;
import com.coolweather.android.utils.ThemeUtil;

/**
 * Created by lenovo on 2017/4/11.
 */

public abstract class BaseContentFragment extends BaseFragment {

    protected SwipeRefreshLayout refreshLayout;

    @Override
    protected void initViews() {
        initRefreshLayout();
    }

    private void initRefreshLayout() {
        refreshLayout = findView(R.id.swipe_container);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                lazyFetchData();
            }
        });

        refreshLayout.setColorSchemeColors(ThemeUtil.getCurrentColorPrimary(getActivity()));
    }

    protected void showRefreshing(final boolean refresh) {
//        Logger.d("refreshing", "loading");
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(refresh);
            }
        });
    }
}
