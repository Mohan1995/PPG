package com.nauroo.ppg.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Mohan M on 12/20/2017.
 */

public class TemperatureModel{
    String id;
    String description;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
