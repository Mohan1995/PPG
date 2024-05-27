package com.nauroo.ppg.ui.home.interactiveguide.maritimo;

import android.content.Context;
import android.graphics.Color;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nauroo.ppg.R;
import com.nauroo.ppg.model.InteractiveGuideModel;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Mohan M on 3/8/2018.
 */

public class MarinoAdapter extends RecyclerView.Adapter<MarinoAdapter.ViewHolder> {
    Context context;
    int firstTime = 0;
    List<InteractiveGuideModel> interactiveGuideModelList;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView headerTextView;
        WebView bodyTextView;
        RelativeLayout headerLayout;
        ImageView arrowImageView;

        public ViewHolder(View v) {
            super(v);
            headerTextView = (TextView) v.findViewById(R.id.headerTextView);
            bodyTextView = (WebView) v.findViewById(R.id.bodyTextView);
            headerLayout = (RelativeLayout) v.findViewById(R.id.headerLayout);
            arrowImageView = (ImageView) v.findViewById(R.id.arrowImageView);
        }
    }

    public MarinoAdapter(Context context, List<InteractiveGuideModel> interactiveGuideModelList) {
        this.context = context;
        this.interactiveGuideModelList = interactiveGuideModelList;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MarinoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
        // create a new view
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.interactive_guide_detail_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final MarinoAdapter.ViewHolder holder, final int position) {
        if (firstTime == 0) {
            holder.headerLayout.setTag("0");
            if (position == interactiveGuideModelList.size() - 1) {
                firstTime = 1;
            }
        }

        holder.bodyTextView.getSettings();
        holder.bodyTextView.setBackgroundColor(Color.TRANSPARENT);
        holder.headerTextView.setText(Html.fromHtml(interactiveGuideModelList.get(position).getHeader()));
        holder.bodyTextView.loadDataWithBaseURL(null,interactiveGuideModelList.get(position).getBody(), "text/html", "utf-8",null);

        if (interactiveGuideModelList.get(position).getBody() == null) {
            holder.arrowImageView.setVisibility(View.GONE);
        } else {
            holder.arrowImageView.setVisibility(View.VISIBLE);
        }
        holder.bodyTextView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });
        holder.bodyTextView.setLongClickable(false);
        holder.bodyTextView.setHapticFeedbackEnabled(false);
        holder.headerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.headerLayout.getTag().toString().equals("0")) {
                    holder.bodyTextView.setVisibility(View.VISIBLE);
                    holder.headerLayout.setTag("1");
                    Picasso.with(context).load(R.drawable.ic_keyboard_arrow_down).into(holder.arrowImageView);
                } else {
                    holder.bodyTextView.setVisibility(View.GONE);
                    holder.headerLayout.setTag("0");
                    Picasso.with(context).load(R.drawable.ic_keyboard_arrow_up).into(holder.arrowImageView);
                }
            }
        });

    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return interactiveGuideModelList.size();
    }
}