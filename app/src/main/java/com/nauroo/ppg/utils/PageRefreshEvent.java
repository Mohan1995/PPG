package com.nauroo.ppg.utils;

/**
 * Created by Mohan M on 2/23/2018.
 */

public class PageRefreshEvent {


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    int type;
    public PageRefreshEvent(int type, String id) {
        this.id=id;
        this.type=type;
    }

}
