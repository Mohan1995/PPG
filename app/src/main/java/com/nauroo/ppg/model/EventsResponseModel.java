package com.nauroo.ppg.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohan M on 12/22/2017.
 */

public class EventsResponseModel implements Parcelable {
    String id;
    String start_date;
    String end_date;

    protected EventsResponseModel(Parcel in) {
        id = in.readString();
        start_date = in.readString();
        end_date = in.readString();
        image_url = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(start_date);
        dest.writeString(end_date);
        dest.writeString(image_url);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<EventsResponseModel> CREATOR = new Creator<EventsResponseModel>() {
        @Override
        public EventsResponseModel createFromParcel(Parcel in) {
            return new EventsResponseModel(in);
        }

        @Override
        public EventsResponseModel[] newArray(int size) {
            return new EventsResponseModel[size];
        }
    };

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    String image_url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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


}
