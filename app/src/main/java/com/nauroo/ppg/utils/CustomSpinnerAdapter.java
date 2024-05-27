package com.nauroo.ppg.utils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import static java.security.AccessController.getContext;

/**
 * Created by Mohan M on 2/28/2018.
 */

public class CustomSpinnerAdapter extends ArrayAdapter<String> {

    private int hidingItemIndex;

    public CustomSpinnerAdapter(Context context, int textViewResourceId, List<String> objects, int hidingItemIndex) {
        super(context, textViewResourceId, objects);
        this.hidingItemIndex = hidingItemIndex;
    }

    @Override
    public boolean isEnabled(int position) {
//        if (position==5){
//            return false;
//        }else {
//            return true;
//        }
        return true;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View v = null;
        TextView tv = new TextView(getContext());
        if (position == hidingItemIndex) {
            tv.setVisibility(View.GONE);
            v = tv;
        } else {
            v = super.getDropDownView(position, null, parent);
        }
        return v;
    }
}