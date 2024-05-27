package com.nauroo.ppg.model;

/**
 * Created by Mohan M on 2/14/2018.
 */

public class NotationsModel {
    String id,description;
    PivotModel pivot;

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

    public PivotModel getPivot() {
        return pivot;
    }

    public void setPivot(PivotModel pivot) {
        this.pivot = pivot;
    }
}
