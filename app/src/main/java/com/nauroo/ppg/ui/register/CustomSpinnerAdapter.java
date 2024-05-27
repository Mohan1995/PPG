package com.nauroo.ppg.ui.register;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nauroo.ppg.R;
import com.nauroo.ppg.model.CountryModel;

import java.util.ArrayList;

/**
 * Created by Mohan M on 12/14/2016.
 */

public class CustomSpinnerAdapter extends ArrayAdapter<String> {

    private Context activity;
    private ArrayList data;
    public Resources res;
    CountryModel tempValues = null;
    LayoutInflater inflater;


    public CustomSpinnerAdapter(
            Context activitySpinner,
            int textViewResourceId,
            ArrayList objects,
            Resources resLocal
    ) {
        super(activitySpinner, textViewResourceId, objects);


        activity = activitySpinner;
        data = objects;
        res = resLocal;


        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {
        View row = inflater.inflate(R.layout.custom_spinner_layout, parent, false);

        tempValues = null;
        tempValues = (CountryModel) data.get(position);
        LinearLayout linearLayout=(LinearLayout)row.findViewById(R.id.rootLayout);
        TextView label = (TextView) linearLayout.findViewById(R.id.text1);
        View view=(View) linearLayout.findViewById(R.id.view);
        label.setText(tempValues.getName());
        if (position==0){
            label.setVisibility(View.GONE);
        }else {
            label.setVisibility(View.VISIBLE);
        }
        if (position==4){
            view.setVisibility(View.VISIBLE);
        }else {
            view.setVisibility(View.GONE);
        }


        return row;
    }
}