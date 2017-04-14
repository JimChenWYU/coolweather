package com.coolweather.android.ui.location;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.coolweather.android.R;
import com.coolweather.android.ui.base.BaseActivity;

/**
 * Created by lenovo on 2017/4/12.
 */

public class ChooseAreaActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_choose_area;
    }

    @Override
    protected int getMenuId() {
        return 0;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setDisplayHomeAsUpEnabled(true);
        setDisplayShowTitleEnabled(false);
    }

    @Override
    protected void loadData() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment currentFragment = new ChooseAreaFragment();

        if (currentFragment.isAdded()) {
            ft.show(currentFragment);
        } else {
            ft.add(R.id.contentLayout, currentFragment);
        }
        ft.commit();
    }

    @Override
    protected void ButterKnifeBind() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}
