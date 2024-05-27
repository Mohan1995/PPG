package com.nauroo.ppg.ui.home.comparator;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nauroo.ppg.R;
import com.nauroo.ppg.model.NotationsModel;
import com.uncopt.android.widget.text.justify.JustifiedTextView;

import java.util.List;

/**
 * Created by Mohan M on 3/15/2018.
 */

public class NotationAdapter extends RecyclerView.Adapter<NotationAdapter.ViewHolder> {
    private List<NotationsModel> notationList;
    Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView notationLabelTextView;
        com.codesgood.views.JustifiedTextView notationTextView;
        public ViewHolder(View v) {
            super(v);
            notationLabelTextView=(TextView)v.findViewById(R.id.notationLabel);
            notationTextView=(com.codesgood.views.JustifiedTextView)v.findViewById(R.id.notationTextView);
        }
    }

    public NotationAdapter(Context context,List<NotationsModel> notationList) {
        this.context=context;
        this.notationList=notationList;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public NotationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {
        // create a new view
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notation_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(NotationAdapter.ViewHolder holder, int position) {
        holder.notationLabelTextView.setText(context.getString(R.string.notations1)+" "+(position+1));
        holder.notationTextView.setText(Html.fromHtml(notationList.get(position).getDescription()));

    }



    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return notationList.size();
    }
}