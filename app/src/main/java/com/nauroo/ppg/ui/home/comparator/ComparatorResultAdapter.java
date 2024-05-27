package com.nauroo.ppg.ui.home.comparator;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nauroo.ppg.R;
import com.nauroo.ppg.model.ComparatorResultModel;
import com.nauroo.ppg.utils.NotationsDialog;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Mohan M on 1/5/2018.
 */

public class ComparatorResultAdapter extends RecyclerView.Adapter<ComparatorResultAdapter.ViewHolder> {
    Activity context;
    List<ComparatorResultModel> comparatorResultModelList;
    int firstTime = 0;
    //    String notation1;
//    String notation2;
//    String notation1Final,notation2Final;
    String alsoConsider;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tittleTextView, countTextView, specificationTextView, alsoConsiderTextView, notesTextView;
        RelativeLayout topLayout;

        LinearLayout bottomLayout;
        TextView specificationTextView1;
        View line;
        RecyclerView notationRecyclerView;


        public ViewHolder(View v) {
            super(v);
            imageView = (ImageView) v.findViewById(R.id.imageView);
            tittleTextView = (TextView) v.findViewById(R.id.tittleTextView);
            topLayout = (RelativeLayout) v.findViewById(R.id.topLayout);
            bottomLayout = (LinearLayout) v.findViewById(R.id.bottomLayout);

            countTextView = (TextView) v.findViewById(R.id.countTextView);
            specificationTextView = (TextView) v.findViewById(R.id.specificationTextView);
            alsoConsiderTextView = (TextView) v.findViewById(R.id.alsoConsiderTextView);
            notesTextView = (TextView) v.findViewById(R.id.notesTextView);
            line = (View) v.findViewById(R.id.line);
            specificationTextView1 = (TextView) v.findViewById(R.id.specificationTextView1);
            notationRecyclerView = (RecyclerView) v.findViewById(R.id.notationRecyclerView);

        }
    }


    public ComparatorResultAdapter(Activity context, List<ComparatorResultModel> comparatorResultModelList) {
        this.context = context;
        this.comparatorResultModelList = comparatorResultModelList;

    }


    @Override
    public ComparatorResultAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                 int viewType) {

        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(final ComparatorResultAdapter.ViewHolder holder, final int position) {
        if (firstTime == 0) {
            holder.topLayout.setTag("0");
            holder.bottomLayout.setTag("0");
            if (position == comparatorResultModelList.size() - 1) {
                firstTime = 1;
            }
        }

        holder.countTextView.setText(position + 1 + "");
        holder.tittleTextView.setText(comparatorResultModelList.get(position).getCode());
        holder.specificationTextView.setText(context.getString(R.string.product_specification));
        holder.specificationTextView1.setText(comparatorResultModelList.get(position).getPpg_primary_offset_spanish());
        alsoConsider = "<font color='#ff7a09'> <b>" + context.getString(R.string.also_consider) + "</b> </font>";

        holder.notationRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        holder.notationRecyclerView.setNestedScrollingEnabled(false);
        if (comparatorResultModelList.get(position).getNotations() == null || comparatorResultModelList.get(position).getNotations().size() == 0) {
            holder.notationRecyclerView.setVisibility(View.GONE);
        } else {
            holder.notationRecyclerView.setAdapter(new NotationAdapter(context, comparatorResultModelList.get(position).getNotations()));
        }
        if (comparatorResultModelList.get(position).getAlso_consider() != null) {
            holder.alsoConsiderTextView.setText(Html.fromHtml(alsoConsider + " " + comparatorResultModelList.get(position).getAlso_consider()));
        } else {
            holder.alsoConsiderTextView.setVisibility(View.GONE);
        }
        if (comparatorResultModelList.get(position).getNotes() == null
                || comparatorResultModelList.get(position).getNotes().length() == 0
                || TextUtils.isEmpty(comparatorResultModelList.get(position).getNotes())) {
            holder.notesTextView.setVisibility(View.GONE);
        } else {
            holder.notesTextView.setVisibility(View.VISIBLE);
            holder.notesTextView.setText(Html.fromHtml(context.getString(R.string.notes) + ": " + comparatorResultModelList.get(position).getNotes_spanish()));

        }

        holder.topLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.topLayout.getTag().toString().equals("0")) {
                    holder.bottomLayout.setVisibility(View.VISIBLE);
                    holder.topLayout.setTag("1");
                    Picasso.with(context).load(R.drawable.minus_icon).into(holder.imageView);
                    holder.line.setVisibility(View.VISIBLE);
                    updateData(position, holder);
                } else {
                    holder.bottomLayout.setVisibility(View.GONE);
                    holder.bottomLayout.setTag("0");
                    holder.topLayout.setTag("0");
                    Picasso.with(context).load(R.drawable.plus_icon).into(holder.imageView);
                    holder.line.setVisibility(View.GONE);
                }
            }
        });

//        holder.bottomLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (holder.bottomLayout.getTag().toString().equals("0")) {
//                    holder.bottomLayout.setVisibility(View.VISIBLE);
//                    holder.bottomLayout.setTag("1");
//                    holder.line.setVisibility(View.VISIBLE);
//                } else {
//                    holder.bottomLayout.setVisibility(View.GONE);
//                    holder.bottomLayout.setTag("0");
//                    holder.line.setVisibility(View.GONE);
//                }
//            }
//        });


    }

    private void updateData(final int position, final ComparatorResultAdapter.ViewHolder holder) {

//        notation1 =context.getString(R.string.notations1);
//        notation2 =context.getString(R.string.notations2);
        // notation1Final = "<font color='#D08047'> <b>" + notation1 + "</b></font>";
        // notation2Final="<font color='#D08047'> <b>" + notation2 + "</b> </font>";


    }

    public void showDialog(int position) {
        NotationsDialog cdd = new NotationsDialog(context, context.getString(R.string.notes).toUpperCase(), comparatorResultModelList.get(position).getNotes());
        cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        cdd.show();
    }


    @Override
    public int getItemCount() {
        return comparatorResultModelList.size();
    }
}
