package com.example.greentech_android.crossworkerstask.presenters;

import com.example.greentech_android.crossworkerstask.models.MyItem;
import com.example.greentech_android.crossworkerstask.views.DetailsView;

/**
 * Created by Serry on 3/15/2018.
 */

public class DetailsPresenterImplementer implements DetailsPresenter {

    private DetailsView detailsView;

    public DetailsPresenterImplementer(DetailsView detailsView) {
        this.detailsView = detailsView;
    }

    @Override
    public void onCreate() {
        detailsView.initViews();
    }

    @Override
    public void getData(MyItem myItem) {
        detailsView.setData(myItem);
    }
}
