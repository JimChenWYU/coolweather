package com.coolweather.android.ui.weather;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;

import com.coolweather.android.R;
import com.coolweather.android.ui.base.BaseActivity;
import com.coolweather.android.utils.RxImage;
import com.coolweather.android.utils.ShareUtils;
import com.coolweather.android.utils.SimpleSubscriber;
import com.coolweather.android.utils.ToastUtil;

import rx.android.schedulers.AndroidSchedulers;

public class ShareActivity extends BaseActivity {

    private static final String EXTRA_SHARE_MESSAGE = "share_message";

    private String shareMessage = "";

    private ScrollView scrollView;
    private TextView tv;

    public static void actionStart(Context context, String shareMessage) {
        Intent starter = new Intent(context, ShareActivity.class);
        starter.putExtra(EXTRA_SHARE_MESSAGE, shareMessage);
        context.startActivity(starter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_share;
    }

    @Override
    protected int getMenuId() {
        return 0;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        scrollView = (ScrollView) findViewById(R.id.share_container);
        tv = (TextView) findViewById(R.id.tv_share_content);
    }

    @Override
    protected void loadData() {
        shareMessage = getIntent().getStringExtra(EXTRA_SHARE_MESSAGE);
        tv.setText(shareMessage);
        scrollView.post(new Runnable() {
            @Override
            public void run() {
                RxImage.saveText2ImageObservable(ShareActivity.this, scrollView)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new SimpleSubscriber<Uri>() {

                            @Override
                            public void onError(Throwable e) {
                                ToastUtil.showShort("天气分享失败：" + e.toString());
                            }

                            @Override
                            public void onNext(Uri uri) {
                                ShareUtils.shareImage(ShareActivity.this, uri, "分享到");
                            }
                        });
                ShareActivity.this.finish();
            }
        });
    }

    @Override
    protected void ButterKnifeBind() {

    }
}
