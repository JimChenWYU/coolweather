package com.coolweather.android.ui.weather.adapter;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.coolweather.android.R;
import com.coolweather.android.model.HeWeather5;
import com.coolweather.android.utils.Logger;
import com.coolweather.android.utils.SizeUtils;
import com.coolweather.android.utils.TimeUtils;
import com.coolweather.android.widgets.WeatherChartView;
import com.coolweather.android.widgets.WeatherDetailsView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by lenovo on 2017/4/11.
 */

public class WeatherAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder>{

    private static final String TAG = "WeatherAdapter";

    private SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
    private SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm", Locale.getDefault());

    private boolean showWeatherChart = true;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public WeatherAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(HeWeather5.DataBean.TYPE_NOW, R.layout.item_weather_container);
        addItemType(HeWeather5.DataBean.TYPE_SUGGESTION, R.layout.item_suggestion_weather);
        addItemType(HeWeather5.DataBean.TYPE_DAILYFORECAST, R.layout.item_weather_container);
    }

    @Override
    protected void convert(final BaseViewHolder holder, MultiItemEntity multiItemEntity) {
        Logger.d(TAG, holder.getItemViewType());
        switch (holder.getItemViewType()) {
            case HeWeather5.DataBean.TYPE_NOW:
                HeWeather5.DataBean now = (HeWeather5.DataBean) multiItemEntity;
                LinearLayout nowContainer = holder.getView(R.id.contentLayout);
                nowContainer.removeAllViews();
                for (int i = 0; i < now.getHourly_forecast().size(); i++) {
                    View view = View.inflate(mContext, R.layout.item_now_weather, null);
                    TextView tvTime = (TextView) view.findViewById(R.id.tv_now_time);
                    TextView tvTemp = (TextView) view.findViewById(R.id.tv_now_temp);
                    TextView tvPop = (TextView) view.findViewById(R.id.tv_now_pop);
                    TextView tvWind = (TextView) view.findViewById(R.id.tv_now_wind);
                    tvTime.setText(
                            TimeUtils.date2String(
                                    TimeUtils.string2Date(
                                            now.getHourly_forecast()
                                                    .get(i).getDate(), sdf1), sdf2));
                    tvTemp.setText(String.format("%s ℃", now.getHourly_forecast().get(i).getTmp()));
                    tvPop.setText(now.getHourly_forecast().get(i).getPop() + " %");
                    tvWind.setText(String.format("%s 级", now.getHourly_forecast().get(i).getWind().getSc()));
                    nowContainer.addView(view);
                }
                break;
            case HeWeather5.DataBean.TYPE_SUGGESTION:
                HeWeather5.DataBean.SuggestionBean suggestion = (HeWeather5.DataBean.SuggestionBean) multiItemEntity;
                holder.setText(R.id.tv_suggestion_air, String.format("舒适指数 -- %s", suggestion.getComf().getBrf()));
                holder.setText(R.id.tv_suggestion_air_info, suggestion.getComf().getTxt());
                holder.setText(R.id.tv_suggestion_out, String.format("运动指数 -- %s", suggestion.getSport().getBrf()));
                holder.setText(R.id.tv_suggestion_out_info, suggestion.getSport().getTxt());
                holder.setText(R.id.tv_suggestion_car, String.format("洗车指数 -- %s", suggestion.getCw().getBrf()));
                holder.setText(R.id.tv_suggestion_car_info, suggestion.getCw().getTxt());
                break;
            case HeWeather5.DataBean.TYPE_DAILYFORECAST:
                HeWeather5.DataBean weather = (HeWeather5.DataBean) ((HeWeather5.DataBean) multiItemEntity).clone();
                LinearLayout container = holder.getView(R.id.contentLayout);
                if (showWeatherChart) {
                    container.setPadding(0, SizeUtils.dp2px(mContext, 16), 0, SizeUtils.dp2px(mContext, 16));
                    container.removeAllViews();
                    container.addView(getChartView(weather));
                } else {
                    container.removeAllViews();
                    container.addView(getDetailsView(weather));
                }

                container.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showWeatherChart = !showWeatherChart;
                        notifyItemChanged(holder.getAdapterPosition());
                    }
                });
                break;
        }
    }

    private WeatherChartView getChartView(HeWeather5.DataBean weather) {
        WeatherChartView chartView = new WeatherChartView(mContext);
        chartView.setWeather(weather);
        return chartView;
    }

    private WeatherDetailsView getDetailsView(HeWeather5.DataBean weather) {
        WeatherDetailsView detailsView = new WeatherDetailsView(mContext);
        detailsView.setWeather(weather);
        return detailsView;
    }
}