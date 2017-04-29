package com.coolweather.android.ui.settings;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.coolweather.android.BuildConfig;
import com.coolweather.android.R;
import com.coolweather.android.http.ApiFactory;
import com.coolweather.android.http.BaseGirlsGankResponse;
import com.coolweather.android.http.api.ApiGlobal;
import com.coolweather.android.model.GirlsGank;
import com.coolweather.android.ui.base.BaseActivity;
import com.coolweather.android.utils.ACache;
import com.coolweather.android.utils.FileUtil;
import com.coolweather.android.utils.Logger;
import com.coolweather.android.utils.SimpleSubscriber;
import com.coolweather.android.utils.TimeUtils;
import com.coolweather.android.utils.ToastUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static com.coolweather.android.utils.FileUtil.getFileDir;


/**
 * Created by liyu on 2016/11/28.
 */

public class AboutActivity extends BaseActivity {

    private static final String CACHE_MEIZI_PIC_NAME = "meizitu_cache";
    private TextView tvVersion;
    private ImageSwitcher imageSwitcher;
    private List<String> imageUrls = new ArrayList<>();

    private Subscription subscription;
    private ACache mCache;

    private Gson gson = new Gson();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    protected int getMenuId() {
        return 0;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setDisplayHomeAsUpEnabled(true);
        setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(R.string.about);
        mCache = ACache.get(this);
        tvVersion = (TextView) findViewById(R.id.tv_app_version);
        tvVersion.setText(String.format("v%s", BuildConfig.VERSION_NAME));
        imageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher);
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(AboutActivity.this);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                return imageView;
            }
        });
        imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this,
                R.anim.zoom_in));
        imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this,
                R.anim.zoom_out));
    }

    private void loadImage() {
        String url = imageUrls.get(new Random().nextInt(imageUrls.size()));
        Glide.with(this).load(url).into(new SimpleTarget<GlideDrawable>(imageSwitcher.getWidth(), imageSwitcher.getHeight()) {
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                imageSwitcher.setImageDrawable(resource);
            }
        });
    }

    @Override
    protected void loadData() {
        String tmp = mCache.getAsString(CACHE_MEIZI_PIC_NAME);
        Logger.i("GSON", tmp);
        if (tmp != null) {
            imageUrls = gson.fromJson(tmp, ArrayList.class);
            imageSwitcher.post(new Runnable() {
                @Override
                public void run() {
                    loadImage();
                }
            });
            Observable.interval(5, TimeUnit.SECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SimpleSubscriber<Long>() {
                        @Override
                        public void onNext(Long aLong) {
                            loadImage();
                        }
                    });
            return;
        }
        subscription = getGirls()
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<BaseGirlsGankResponse, Observable<Long>>() {
                    @Override
                    public Observable<Long> call(BaseGirlsGankResponse response) {
                        if (response == null || !response.status.equals(ApiGlobal.OK)) {
                            ToastUtil.showShort("获取背景图失败！");
                            return null;
                        }
                        if (response.data.size() == 0) {
                            return null;
                        }

                        imageUrls = new ArrayList<>();
                        for (GirlsGank gank : response.data) {
                            imageUrls.add(gank.getUrl());
                        }

                        mCache.put(CACHE_MEIZI_PIC_NAME, gson.toJson(imageUrls), (int) TimeUtils.getUntilTomorrowTime());
                        return Observable.interval(5, TimeUnit.SECONDS);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SimpleSubscriber<Long>() {
                    @Override
                    public void onNext(Long longObservable) {
                        loadImage();
                    }
                });
    }

    @Override
    protected void ButterKnifeBind() {

    }

    private Observable<BaseGirlsGankResponse> getGirls() {
        return ApiFactory
                .getGirlsController()
                .getGirlsGank()
                .subscribeOn(Schedulers.io());
    }

    public void onClick(View v) {
    }

    private void feedBack() {
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", "1047004324@qq.com", null));
        intent.putExtra(Intent.EXTRA_EMAIL, "1047004324@qq.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, "反馈");
        intent.putExtra(Intent.EXTRA_TEXT, FileUtil.readFile(getFileDir("Log/crash.log")));
        startActivity(Intent.createChooser(intent, "反馈"));
    }

}
