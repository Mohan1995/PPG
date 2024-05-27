package com.nauroo.ppg.utils;

import android.app.Activity;
import android.content.Context;

import androidx.core.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nauroo.ppg.R;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Mohan M on 12/28/2017.
 */

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Activity context;
    private List<String> expandableListTitle;
    private HashMap<String, List<String>> expandableListDetail;

    public ExpandableListAdapter(Activity context, List<String> expandableListTitle,
                                 HashMap<String, List<String>> expandableListDetail) {
        this.context = context;
        this.expandableListTitle = expandableListTitle;
        this.expandableListDetail = expandableListDetail;
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return expandableListDetail.get(expandableListTitle.get(listPosition))
                .get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final String expandedListText = (String) getChild(listPosition, expandedListPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.sub_category_layout, null);
        }
        TextView expandedListTextView = (TextView) convertView
                .findViewById(R.id.subCategoryTextView);
        expandedListTextView.setText(expandedListText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return expandableListDetail.get(expandableListTitle.get(listPosition))
                .size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return expandableListTitle.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return expandableListTitle.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String listTitle = (String) getGroup(listPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater)context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.category_layout, null);
        }
        TextView listTitleTextView = (TextView) convertView
                .findViewById(R.id.categoryTextView);
        TextView countTextView=(TextView)convertView.findViewById(R.id.countTextView);
        RelativeLayout rootLayout = (RelativeLayout) convertView
                .findViewById(R.id.rootLayout);
        ImageView groupIndicator=(ImageView) convertView.findViewById(R.id.groupIndicator);
        listTitleTextView.setText(listTitle);
        countTextView.setText(listPosition+1+"");
        if (isExpanded){
            rootLayout.setBackgroundColor(ContextCompat.getColor(context,R.color.cell_expanded_color));
            Picasso.with(context).load(R.drawable.minus_icon).into(groupIndicator);
        }else {
            rootLayout.setBackgroundColor(ContextCompat.getColor(context,R.color.window_background_color));
            Picasso.with(context).load(R.drawable.plus_icon).into(groupIndicator);
        }
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }
}