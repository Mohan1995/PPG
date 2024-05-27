package com.nauroo.ppg.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohan M on 12/18/2017.
 */

public class NewsResponseModel implements Parcelable{
    String id, date,image_url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public List<TranslationsModel> getTranslations() {
        return translations;
    }

    public void setTranslations(List<TranslationsModel> translations) {
        this.translations = translations;
    }

    List<TranslationsModel> translations=new ArrayList<>();

    protected NewsResponseModel(Parcel in) {
        id = in.readString();
        date = in.readString();
        image_url = in.readString();
    }

    public static final Creator<NewsResponseModel> CREATOR = new Creator<NewsResponseModel>() {
        @Override
        public NewsResponseModel createFromParcel(Parcel in) {
            return new NewsResponseModel(in);
        }

        @Override
        public NewsResponseModel[] newArray(int size) {
            return new NewsResponseModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(date);
        parcel.writeString(image_url);
    }
}
