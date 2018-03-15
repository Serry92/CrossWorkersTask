package com.example.greentech_android.crossworkerstask.models;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Serry on 3/15/2018.
 */

public class MyItem extends RealmObject implements Parcelable {
    private RealmList<MyItem> items;
    private int id;
    private String name;
    private String photoUrl;
    private String description;

    public MyItem(Parcel in) {
        id = in.readInt();
        name = in.readString();
        photoUrl = in.readString();
        description = in.readString();
    }

    public MyItem() {

    }

    public RealmList<MyItem> getItems() {
        return items;
    }

    public void setItems(RealmList<MyItem> items) {
        this.items = items;
    }

    public static final Creator<MyItem> CREATOR = new Creator<MyItem>() {
        @Override
        public MyItem createFromParcel(Parcel in) {
            return new MyItem(in);
        }

        @Override
        public MyItem[] newArray(int size) {
            return new MyItem[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(photoUrl);
        parcel.writeString(description);
    }
}
