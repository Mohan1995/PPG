package com.nauroo.ppg.model;

/**
 * Created by Mohan M on 7/28/2017.
 */

public class GenericResponse<T> {
    private T data;
    private int code;

    public int getStatus() {
        return code;
    }

    public T getData() {
        return data;
    }
}
