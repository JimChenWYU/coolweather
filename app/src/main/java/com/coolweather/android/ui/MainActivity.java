package com.coolweather.android.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.coolweather.android.AppGlobal;
import com.coolweather.android.R;
import com.coolweather.android.even.ThemeChangedEvent;
import com.coolweather.android.ui.base.BaseActivity;
import com.coolweather.android.ui.scenic.ScenicFragment;
import com.coolweather.android.ui.settings.SettingsActivity;
import com.coolweather.android.ui.weather.WeatherFragment;
import com.coolweather.android.utils.DoubleClickExit;
import com.coolweather.android.utils.RxDrawer;
import com.coolweather.android.utils.SimpleSubscriber;
import com.coolweather.android.utils.TTSManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import rx.android.schedulers.AndroidSchedulers;

public class MainActivity extends BaseActivity {

    public static final String SHOULD_DOUBLE_CLICK_EXIT = "shouldDoubleClickExit";

//    @BindView(R.id.navigation)
    NavigationView navigation;
//    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    private FragmentManager fragmentManager;
    private ActionBarDrawerToggle mDrawerToggle;
    private String currentFragmentTag;

    private static final String FRAGMENT_TAG_BUS = "bus";
    private static final String FRAGMENT_TAG_WEATHER = "weather";
    private static final String FRAGMENT_TAG_SCENIC = "scenic";
    private static final String FRAGMENT_TAG_GANK = "gank";
    private static final String FRAGMENT_TAG_READING = "reading";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected int getMenuId() {
        return R.menu.drawer;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        fragmentManager = getSupportFragmentManager();
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        initNavigationViewHeader();
        initFragment(savedInstanceState);
    }

    @Override
    protected void loadData() {
        // TODO: 2017/4/11  
    }

    @Override
    protected void ButterKnifeBind() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void initDrawer(Toolbar toolbar) {
        if (toolbar != null) {
            mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
            mDrawerToggle.syncState();
            drawerLayout.addDrawerListener(mDrawerToggle);
        }
    }

    private void initNavigationViewHeader() {
        navigation = (NavigationView) findViewById(R.id.navigation);
        navigation.inflateHeaderView(R.layout.drawer_header);
        // TODO: 2017/4/11
        navigation.setNavigationItemSelectedListener(new NavigationItemSelected());
    }

    private void initFragment(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            switchContent(FRAGMENT_TAG_WEATHER);
        } else {
            currentFragmentTag = savedInstanceState.getString(AppGlobal.CURRENT_INDEX);
            switchContent(currentFragmentTag);
        }
    }

    private void switchContent(String name) {
        if (currentFragmentTag != null && currentFragmentTag.equals(name)) {
            return;
        }

        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        Fragment currentFragment = fragmentManager.findFragmentByTag(currentFragmentTag);
        if (currentFragment != null) {
            ft.hide(currentFragment);
        }

        Fragment foundFragment = fragmentManager.findFragmentByTag(name);

        if (foundFragment == null) {
            switch (name) {
                case FRAGMENT_TAG_WEATHER:
                    foundFragment = new WeatherFragment();
                    break;
                case FRAGMENT_TAG_SCENIC:
                    // TODO: 2017/4/15
                    foundFragment = new ScenicFragment();
                    break;
                case FRAGMENT_TAG_BUS:
                    // TODO: 2017/4/11
                    break;
                case FRAGMENT_TAG_READING:
                    // TODO: 2017/4/11
                    break;
                case FRAGMENT_TAG_GANK:
                    // TODO: 2017/4/11
                    break;
            }
        }

        if (foundFragment == null) {
            Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
        } else if (foundFragment.isAdded()) {
            ft.show(foundFragment);
        } else {
            ft.add(R.id.contentLayout, foundFragment, name);
        }
        ft.commit();
        currentFragmentTag = name;
        invalidateOptionsMenu();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(AppGlobal.CURRENT_INDEX, currentFragmentTag);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onThemeChanged(ThemeChangedEvent event) {
        this.recreate();
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onDestroy() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        TTSManager.destroy();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        Intent intent = getIntent();
        boolean showDoubleClickExit = intent.getBooleanExtra(SHOULD_DOUBLE_CLICK_EXIT, true);
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            if (showDoubleClickExit && !DoubleClickExit.check()) {
                Snackbar.make(MainActivity.this.getWindow().getDecorView().findViewById(android.R.id.content),
                        "再按一次退出 App!",
                        Snackbar.LENGTH_SHORT).show();
            } else {
                super.onBackPressed();
            }
        }
    }

    private class NavigationItemSelected implements NavigationView.OnNavigationItemSelectedListener {

        @Override
        public boolean onNavigationItemSelected(@NonNull final MenuItem item) {
            RxDrawer.close(drawerLayout)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SimpleSubscriber<Void>() {
                        @Override
                        public void onNext(Void aVoid) {
                            switch (item.getItemId()) {
                                case R.id.navigation_item_weather:
                                    item.setChecked(true);
                                    switchContent(FRAGMENT_TAG_WEATHER);
                                    break;
                                case R.id.navigation_item_scenic:
                                    item.setChecked(true);
                                    switchContent(FRAGMENT_TAG_SCENIC);
                                    break;
                                case R.id.navigation_item_settings:
                                    startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                                    break;
                            }
                        }
                    });
            return false;
        }
    }
}