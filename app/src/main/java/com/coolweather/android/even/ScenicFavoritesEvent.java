package com.coolweather.android.even;

import com.coolweather.android.model.FavoritesScenicBean;

/**
 * Created by lenovo on 2017/4/19.
 */

public class ScenicFavoritesEvent {
    private boolean isFavorite;

    private FavoritesScenicBean data;

    public ScenicFavoritesEvent(FavoritesScenicBean data) {
        this.data = data;
    }

    public FavoritesScenicBean getScenicFavorite() {
        return data;
    }

    public void setScenicFavorite(FavoritesScenicBean data) {
        this.data = data;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
