package com.nauroo.ppg.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohan M on 1/4/2018.
 */

public class ComparatorResultModel implements Parcelable {
    String id,competitor_id,code,category_id,subcategory_id,ppg_primary_offset,also_consider,notes;
    List<NotationsModel> notations=new ArrayList<>();
    String ppg_primary_offset_spanish;

    public String getNotes_spanish() {
        return notes_spanish;
    }

    public void setNotes_spanish(String notes_spanish) {
        this.notes_spanish = notes_spanish;
    }

    public String getPpg_primary_offset_spanish() {
        return ppg_primary_offset_spanish;
    }

    public void setPpg_primary_offset_spanish(String ppg_primary_offset_spanish) {
        this.ppg_primary_offset_spanish = ppg_primary_offset_spanish;
    }

    String notes_spanish;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompetitor_id() {
        return competitor_id;
    }

    public void setCompetitor_id(String competitor_id) {
        this.competitor_id = competitor_id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getSubcategory_id() {
        return subcategory_id;
    }

    public void setSubcategory_id(String subcategory_id) {
        this.subcategory_id = subcategory_id;
    }

    public String getPpg_primary_offset() {
        return ppg_primary_offset;
    }

    public void setPpg_primary_offset(String ppg_primary_offset) {
        this.ppg_primary_offset = ppg_primary_offset;
    }

    public String getAlso_consider() {
        return also_consider;
    }

    public void setAlso_consider(String also_consider) {
        this.also_consider = also_consider;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<NotationsModel> getNotations() {
        return notations;
    }

    public void setNotations(List<NotationsModel> notations) {
        this.notations = notations;
    }

    protected ComparatorResultModel(Parcel in) {
        id = in.readString();
        competitor_id = in.readString();
        code = in.readString();
        category_id = in.readString();
        subcategory_id = in.readString();
        ppg_primary_offset = in.readString();
        also_consider = in.readString();
        notes = in.readString();
        ppg_primary_offset_spanish=in.readString();
        notes_spanish=in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(competitor_id);
        dest.writeString(code);
        dest.writeString(category_id);
        dest.writeString(subcategory_id);
        dest.writeString(ppg_primary_offset);
        dest.writeString(also_consider);
        dest.writeString(notes);
        dest.writeString(ppg_primary_offset_spanish);
        dest.writeString(notes_spanish);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ComparatorResultModel> CREATOR = new Creator<ComparatorResultModel>() {
        @Override
        public ComparatorResultModel createFromParcel(Parcel in) {
            return new ComparatorResultModel(in);
        }

        @Override
        public ComparatorResultModel[] newArray(int size) {
            return new ComparatorResultModel[size];
        }
    };
}
