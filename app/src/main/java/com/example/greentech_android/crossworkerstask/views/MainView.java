package com.example.greentech_android.crossworkerstask.views;

import com.example.greentech_android.crossworkerstask.models.MyItem;

import java.util.List;

import io.realm.RealmList;

/**
 * Created by Serry on 3/15/2018.
 */

public interface MainView {
    void initViews();

    void hideProgress();

    void setList(RealmList<MyItem> myItems);
}
