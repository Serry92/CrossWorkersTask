package com.example.greentech_android.crossworkerstask.presenters;

/**
 * Created by Serry on 3/15/2018.
 */

public interface MainPresenter {
    void onCreate();

    boolean checkConnection();

    void connectWithServer();


    void getDataFromDatabase();
}
