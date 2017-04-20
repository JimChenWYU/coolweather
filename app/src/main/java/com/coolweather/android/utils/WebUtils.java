package com.coolweather.android.utils;

import android.content.Context;

import com.coolweather.android.R;
import com.thefinestartist.finestwebview.FinestWebView;

/**
 * Created by lenovo on 2017/4/20.
 */

public class WebUtils {
    public static void openInternet(Context context, String url) {
        new FinestWebView.Builder(context)
                .stringResCopiedToClipboard(R.string.copied_to_clipboard)
                .stringResRefresh(R.string.menu_action_refresh)
                .stringResShareVia(R.string.menu_action_share)
                .stringResCopyLink(R.string.menu_action_copy)
                .stringResOpenWith(R.string.menu_action_openwith)
                .titleColor(context.getResources().getColor(R.color.white))
                .toolbarColor(context.getResources().getColor(ThemeUtil.getCurrentColorPrimary(context)))
                .statusBarColor(context.getResources().getColor(ThemeUtil.getCurrentColorPrimary(context)))
                .swipeRefreshColor(context.getResources().getColor(ThemeUtil.getCurrentColorPrimary(context)))
                .showUrl(false)
                .webViewDisplayZoomControls(true)
                .webViewSupportZoom(true)
                .webViewBuiltInZoomControls(true)
                .iconDefaultColor(context.getResources().getColor(R.color.Color_White))
                .show(url);
    }
}
