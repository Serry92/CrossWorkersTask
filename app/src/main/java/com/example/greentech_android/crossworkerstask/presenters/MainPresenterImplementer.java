package com.example.greentech_android.crossworkerstask.presenters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.greentech_android.crossworkerstask.apis.Apis;
import com.example.greentech_android.crossworkerstask.models.MyItem;
import com.example.greentech_android.crossworkerstask.utilities.ConnectionDetector;
import com.example.greentech_android.crossworkerstask.utilities.Constants;
import com.example.greentech_android.crossworkerstask.views.MainView;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Serry on 3/15/2018.
 */

public class MainPresenterImplementer implements MainPresenter {
    private MainView mainView;
    private Context context;

    public MainPresenterImplementer(MainView mainView, Context context) {
        this.mainView = mainView;
        this.context = context;
    }

    @Override
    public void onCreate() {
        mainView.initViews();
    }

    @Override
    public boolean checkConnection() {
        ConnectionDetector cd = new ConnectionDetector(context);
        return cd.isConnectingToInternet();
    }

    @Override
    public void connectWithServer() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.GET_ITEM_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Apis apis = retrofit.create(Apis.class);
        Call<MyItem> listCall = apis.getItems();
        listCall.enqueue(new Callback<MyItem>() {
            @Override
            public void onResponse(@NonNull Call<MyItem> call, @NonNull Response<MyItem> response) {
                RealmList<MyItem> myItems = response.body().getItems();
                mainView.setList(myItems);
                mainView.hideProgress();
                Realm.init(context);
                RealmConfiguration config = new RealmConfiguration.Builder()
                        .name(Constants.FILE_NAME)
                        .schemaVersion(1)
                        .deleteRealmIfMigrationNeeded()
                        .build();
                Realm.setDefaultConfiguration(config);
                Realm realm = Realm.getInstance(config);
                realm.beginTransaction();
                realm.delete(MyItem.class);
                realm.copyToRealmOrUpdate(myItems);
                realm.commitTransaction();
                int notesCount = realm.where(MyItem.class).findAll().size();
                Log.d("count", String.valueOf(notesCount));
                realm.close();
            }

            @Override
            public void onFailure(@NonNull Call<MyItem> call, @NonNull Throwable t) {
                mainView.hideProgress();

            }
        });

    }

    @Override
    public void getDataFromDatabase() {
        Realm.init(context);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name(Constants.FILE_NAME)
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
        Realm realm = Realm.getInstance(config);
        RealmResults<MyItem> realmResults = realm.where(MyItem.class).findAllAsync();
        realmResults.load();
        RealmList<MyItem> myItems = new RealmList<>();
        myItems.addAll(realmResults);
        mainView.setList(myItems);

    }
}
