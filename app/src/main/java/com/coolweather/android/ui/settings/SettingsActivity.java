package com.coolweather.android.ui.settings;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;

import com.afollestad.materialdialogs.color.ColorChooserDialog;
import com.coolweather.android.R;
import com.coolweather.android.even.ThemeChangedEvent;
import com.coolweather.android.ui.base.BaseActivity;
import com.coolweather.android.utils.SettingsUtil;
import com.coolweather.android.utils.ThemeUtil;

import org.greenrobot.eventbus.EventBus;

public class SettingsActivity extends BaseActivity implements ColorChooserDialog.ColorCallback{

    @Override
    protected int getLayoutId() {
        return R.layout.activity_settings;
    }

    @Override
    protected int getMenuId() {
        return 0;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void loadData() {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.contentLayout, new SettingFragment())
                .commit();
    }

    @Override
    protected void ButterKnifeBind() {

    }

    @Override
    public void onColorSelection(@NonNull ColorChooserDialog dialog, @ColorInt int selectedColor) {
        if (selectedColor == ThemeUtil.getThemeColor(this, R.attr.colorPrimary)) {
            return;
        }
        toolbar.setBackgroundColor(selectedColor);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(selectedColor);
        }

        if (selectedColor == getResources().getColor(R.color.colorPrimary)) {
            setTheme(R.style.AppTheme);
            SettingsUtil.setTheme(0);
        } else if (selectedColor == getResources().getColor(R.color.pale_dogwood)) {
            setTheme(R.style.PaleDogwoodTheme);
            SettingsUtil.setTheme(1);
        } else if (selectedColor == getResources().getColor(R.color.greenery)) {
            setTheme(R.style.GreeneryTheme);
            SettingsUtil.setTheme(2);
        } else if (selectedColor == getResources().getColor(R.color.primrose_yellow)) {
            setTheme(R.style.PrimroseYellowTheme);
            SettingsUtil.setTheme(3);
        } else if (selectedColor == getResources().getColor(R.color.flame)) {
            setTheme(R.style.FlameTheme);
            SettingsUtil.setTheme(4);
        } else if (selectedColor == getResources().getColor(R.color.island_paradise)) {
            setTheme(R.style.IslandParadiseTheme);
            SettingsUtil.setTheme(5);
        } else if (selectedColor == getResources().getColor(R.color.kale)) {
            setTheme(R.style.KaleTheme);
            SettingsUtil.setTheme(6);
        } else if (selectedColor == getResources().getColor(R.color.pink_yarrow)) {
            setTheme(R.style.PinkYarrowTheme);
            SettingsUtil.setTheme(7);
        } else if (selectedColor == getResources().getColor(R.color.niagara)) {
            setTheme(R.style.NiagaraTheme);
            SettingsUtil.setTheme(8);
        }

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.contentLayout, new SettingFragment())
                .commit();
        EventBus.getDefault().post(new ThemeChangedEvent(selectedColor));
    }
}
