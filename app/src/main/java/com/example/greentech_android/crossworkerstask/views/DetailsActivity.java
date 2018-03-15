package com.example.greentech_android.crossworkerstask.views;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.greentech_android.crossworkerstask.R;
import com.example.greentech_android.crossworkerstask.models.MyItem;
import com.example.greentech_android.crossworkerstask.presenters.DetailsPresenterImplementer;
import com.example.greentech_android.crossworkerstask.utilities.Constants;

/**
 * Created by Serry on 3/15/2018.
 */

public class DetailsActivity extends AppCompatActivity implements DetailsView {
    ImageView ivPhoto;
    TextView tvDescription;
    ProgressBar progressBar;
    DetailsPresenterImplementer detailsPresenterImplementer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        detailsPresenterImplementer = new DetailsPresenterImplementer(this);
        detailsPresenterImplementer.onCreate();
        MyItem myItem = getIntent().getParcelableExtra(Constants.INTENT_KEY);
        detailsPresenterImplementer.getData(myItem);

    }

    @Override
    public void initViews() {
        ivPhoto = findViewById(R.id.iv_item_detail);
        progressBar = findViewById(R.id.pb_detail);
        tvDescription = findViewById(R.id.tv_description);
    }

    @Override
    public void setData(MyItem myItem) {
        Glide.with(this).load(myItem.getPhotoUrl()).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                progressBar.setVisibility(View.GONE);
                ivPhoto.setImageResource(R.drawable.ic_launcher_background);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                progressBar.setVisibility(View.GONE);
                return false;
            }
        }).into(ivPhoto);
        tvDescription.setText(myItem.getDescription());

    }
}
