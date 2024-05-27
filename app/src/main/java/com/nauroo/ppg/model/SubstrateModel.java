package com.nauroo.ppg.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohan M on 12/20/2017.
 */

public class SubstrateModel {
    String id;
    List<SubstrateTranslationModel> translations=new ArrayList<>();


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<SubstrateTranslationModel> getTranslations() {
        return translations;
    }

    public void setTranslations(List<SubstrateTranslationModel> translations) {
        this.translations = translations;
    }


}
