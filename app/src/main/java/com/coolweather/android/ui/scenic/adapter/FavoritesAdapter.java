package com.coolweather.android.ui.scenic.adapter;

import android.content.ContentValues;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.coolweather.android.R;
import com.coolweather.android.model.FavoritesScenicBean;
import com.coolweather.android.model.ScenicBaseDetail;
import com.coolweather.android.ui.MainActivity;
import com.coolweather.android.ui.scenic.ScenicDetailActivity;
import com.coolweather.android.utils.RxDataBase;

import org.litepal.crud.DataSupport;

import java.util.List;

import rx.functions.Func1;

/**
 * Created by lenovo on 2017/4/19.
 */

public class FavoritesAdapter extends BaseQuickAdapter<FavoritesScenicBean, BaseViewHolder> {

    private int deletedPosition = 0;
    private FavoritesScenicBean deletedItem;

    public FavoritesAdapter(int layoutResId, List<FavoritesScenicBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder, final FavoritesScenicBean item) {
        String more = String.format("省份：%s，城市：%s", item.getProvince(), item.getCity());
        holder.setText(R.id.tv_scenic_name, item.getName());
        holder.setText(R.id.tv_scenic_region, more);
        holder.setText(R.id.tv_scenic_more, "更多...");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ScenicDetailActivity.start(mContext, item.getId(), item.getName(), item.getProvince(), item.getCity());
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                deletedPosition = holder.getAdapterPosition();
                deletedItem = item;
                remove(deletedPosition);
                setFavorite(deletedItem, false);
                Snackbar.make(((MainActivity) mContext)
                    .getWindow().getDecorView()
                    .getRootView().findViewById(R.id.contentLayout), "删除成功！",
                    Snackbar.LENGTH_LONG).setAction("撤销", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addData(deletedPosition, deletedItem);
                        setFavorite(deletedItem, true);
                    }
                }).setActionTextColor(mContext.getResources().getColor(R.color.actionColor)).show();
                return true;
            }
        });
    }

    private void setFavorite(final FavoritesScenicBean item, final boolean isFavorite) {
        RxDataBase.getFirst(ScenicBaseDetail.class, "scenicId = ?", item.getId())
                .map(new Func1<ScenicBaseDetail, Integer>() {
                    @Override
                    public Integer call(ScenicBaseDetail scenicBaseDetail) {
                        ContentValues values = new ContentValues();
                        values.put("isFavorite", isFavorite);
                        return DataSupport.updateAll(ScenicBaseDetail.class, values, "scenicId = ?", item.getId());
                    }
                }).subscribe();
    }
}
