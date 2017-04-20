package com.coolweather.android.ui.scenic.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.coolweather.android.R;
import com.coolweather.android.model.ScenicLineBean;
import com.coolweather.android.ui.scenic.ScenicDetailActivity;

import java.util.List;

/**
 * Created by lenovo on 2017/4/15.
 */

public class LineScenicAdapter extends BaseQuickAdapter<ScenicLineBean, BaseViewHolder> {

    private static final String TAG = "LineScenicAdapter";

    public LineScenicAdapter(int layoutResId, List<ScenicLineBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, final ScenicLineBean item) {
        String more = String.format("省份：%s，城市：%s", item.getProvince(), item.getCity());
        holder.setText(R.id.tv_scenic_name, item.getName());
        holder.setText(R.id.tv_scenic_region, more);
        holder.setText(R.id.tv_scenic_more, "更多...");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ScenicDetailActivity.start(mContext, item.getId());
                ScenicDetailActivity.start(mContext, item.getId(), item.getName(), item.getProvince(), item.getCity());
            }
        });
    }
}
