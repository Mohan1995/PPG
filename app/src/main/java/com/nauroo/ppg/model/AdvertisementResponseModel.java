package com.nauroo.ppg.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohan M on 1/8/2018.
 */

public class AdvertisementResponseModel {
    String id,url_image,color;

    public List<AdvertisementTranslationModel> getTranslations() {
        return translations;
    }

    public void setTranslations(List<AdvertisementTranslationModel> translations) {
        this.translations = translations;
    }

    List<AdvertisementTranslationModel> translations=new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl_image() {
        return url_image;
    }

    public void setUrl_image(String url_image) {
        this.url_image = url_image;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
