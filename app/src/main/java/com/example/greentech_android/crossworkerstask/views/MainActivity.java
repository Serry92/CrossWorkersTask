package com.example.greentech_android.crossworkerstask.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.greentech_android.crossworkerstask.R;
import com.example.greentech_android.crossworkerstask.adapters.MyRecyclerViewAdapter;
import com.example.greentech_android.crossworkerstask.interfaces.OnMyItemClickListener;
import com.example.greentech_android.crossworkerstask.models.MyItem;
import com.example.greentech_android.crossworkerstask.presenters.MainPresenterImplementer;
import com.example.greentech_android.crossworkerstask.utilities.ConnectionDetector;
import com.example.greentech_android.crossworkerstask.utilities.Constants;

import java.util.List;

import io.realm.RealmList;

/**
 * Created by Serry on 3/15/2018.
 */

public class MainActivity extends AppCompatActivity implements MainView, OnMyItemClickListener {

    RecyclerView rvList;
    ProgressBar progressBar;
    LinearLayoutManager linearLayoutManager;
    MyRecyclerViewAdapter myRecyclerViewAdapter;
    MainPresenterImplementer mainPresenterImplementer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainPresenterImplementer = new MainPresenterImplementer(this, this);
        mainPresenterImplementer.onCreate();

    }

    @Override
    public void initViews() {
        rvList = findViewById(R.id.rv_list);
        progressBar = findViewById(R.id.pb_list);
        linearLayoutManager = new LinearLayoutManager(this);
        rvList.setLayoutManager(linearLayoutManager);
        if (mainPresenterImplementer.checkConnection())
            mainPresenterImplementer.connectWithServer();
        else
            mainPresenterImplementer.getDataFromDatabase();
    }


    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setList(RealmList<MyItem> myItems) {
        progressBar.setVisibility(View.GONE);
        myRecyclerViewAdapter = new MyRecyclerViewAdapter(myItems, this, this);
        rvList.setAdapter(myRecyclerViewAdapter);
    }

    @Override
    public void onMyItemClick(MyItem myItem) {
        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
        intent.putExtra(Constants.INTENT_KEY, myItem);
        startActivity(intent);
    }
}
