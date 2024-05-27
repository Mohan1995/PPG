package com.nauroo.ppg.network;

import android.content.Context;
import android.text.TextUtils;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class ResponseCallback<T> implements Callback<T> {

    private RestClient mRestClient = null;
    private Context mContext;

    public ResponseCallback(Context context) {
        mContext = context;
        mRestClient = new RestClient(mContext);
    }

    public abstract void onResponse(T response);

    public abstract void onFailure();


    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.errorBody() != null && !response.isSuccessful()) {
        } else {
            onResponse(response.body());
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
            onFailure();
    }


}
