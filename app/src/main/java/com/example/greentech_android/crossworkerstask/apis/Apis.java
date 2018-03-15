package com.example.greentech_android.crossworkerstask.apis;

import com.example.greentech_android.crossworkerstask.models.MyItem;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Serry on 3/15/2018.
 */

public interface Apis {
    @GET("kvdzh")
    Call<MyItem> getItems();
}
