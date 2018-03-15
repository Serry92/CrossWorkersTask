package com.example.greentech_android.crossworkerstask.presenters;

import com.example.greentech_android.crossworkerstask.models.MyItem;

/**
 * Created by Serry on 3/15/2018.
 */

public interface DetailsPresenter {
    void onCreate();

    void getData(MyItem myItem);
}
