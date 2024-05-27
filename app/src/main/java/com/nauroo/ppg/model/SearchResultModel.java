package com.nauroo.ppg.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohan M on 12/26/2017.
 */

public class SearchResultModel implements Parcelable{
    String id,code;
    List<SearchResultTranslationModel> translations=new ArrayList<>();

    public List<SubProductsModel> getSubproducts() {
        return subproducts;
    }

    public void setSubproducts(List<SubProductsModel> subproducts) {
        this.subproducts = subproducts;
    }

    List<SubProductsModel> subproducts=new ArrayList<>();

    protected SearchResultModel(Parcel in) {
        id = in.readString();
        code = in.readString();
    }

    public static final Creator<SearchResultModel> CREATOR = new Creator<SearchResultModel>() {
        @Override
        public SearchResultModel createFromParcel(Parcel in) {
            return new SearchResultModel(in);
        }

        @Override
        public SearchResultModel[] newArray(int size) {
            return new SearchResultModel[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<SearchResultTranslationModel> getTranslations() {
        return translations;
    }

    public void setTranslations(List<SearchResultTranslationModel> translations) {
        this.translations = translations;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(code);
    }
}
