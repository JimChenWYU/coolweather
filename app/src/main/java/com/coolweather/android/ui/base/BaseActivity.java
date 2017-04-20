package com.coolweather.android.ui.base;

import android.support.annotation.LayoutRes;
import android.support.annotation.MenuRes;
import android.support.annotation.StyleRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.coolweather.android.R;
import com.coolweather.android.utils.SettingsUtil;

public abstract class BaseActivity extends AppCompatActivity {

    protected Toolbar toolbar;

    @StyleRes
    public int currentTheme;

    @LayoutRes
    protected abstract int getLayoutId();

    @MenuRes
    protected abstract int getMenuId();

    protected abstract void initViews(Bundle savedInstanceState);

    protected abstract void loadData();

    protected abstract void ButterKnifeBind();

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnifeBind();
        initTheme();
        setContentView(getLayoutId());
        initToolBar();
        initViews(savedInstanceState);
        loadData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (getMenuId() != 0) {
            getMenuInflater().inflate(getMenuId(), menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initTheme() {
        // TODO: 2017/4/11
        int themeIndex = SettingsUtil.getTheme();
        switch (themeIndex) {
            case 0:
                currentTheme = R.style.AppTheme;
                setTheme(R.style.AppTheme);
                break;
            case 1:
                currentTheme = R.style.PaleDogwoodTheme;
                setTheme(R.style.PaleDogwoodTheme);
                break;
            case 2:
                currentTheme = R.style.GreeneryTheme;
                setTheme(R.style.GreeneryTheme);
                break;
            case 3:
                currentTheme = R.style.PrimroseYellowTheme;
                setTheme(R.style.PrimroseYellowTheme);
                break;
            case 4:
                currentTheme = R.style.FlameTheme;
                setTheme(R.style.FlameTheme);
                break;
            case 5:
                currentTheme = R.style.IslandParadiseTheme;
                setTheme(R.style.IslandParadiseTheme);
                break;
            case 6:
                currentTheme = R.style.KaleTheme;
                setTheme(R.style.KaleTheme);
                break;
            case 7:
                currentTheme = R.style.PinkYarrowTheme;
                setTheme(R.style.PinkYarrowTheme);
                break;
            case 8:
                currentTheme = R.style.NiagaraTheme;
                setTheme(R.style.NiagaraTheme);
                break;
        }
    }

    private void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    protected void setDisplayHomeAsUpEnabled(boolean enabled) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(enabled);
        }
    }

    protected void setDisplayShowTitleEnabled(boolean enabled) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(enabled);
        }
    }
}
