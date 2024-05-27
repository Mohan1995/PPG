package com.nauroo.ppg.ui.home.interactiveguide.certificaciones;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nauroo.ppg.R;

import java.util.ArrayList;

/**
 * Created by Mohan M on 6/5/2018.
 */

public class PrincipleAndCertifierAdapter extends RecyclerView.Adapter<PrincipleAndCertifierAdapter.ViewHolder> {
    ArrayList<String> principleArray;
    ArrayList<String> certifierArray;
    Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView principleTextView,certifierTextView;
        public ViewHolder(View v) {
            super(v);
            principleTextView=(TextView)v.findViewById(R.id.principleTextView);
            certifierTextView=(TextView)v.findViewById(R.id.certifierTextView);
        }
    }

    public PrincipleAndCertifierAdapter(Context context,ArrayList<String> principleArray,ArrayList<String> certifierArray) {
        this.context=context;
        this.principleArray=principleArray;
        this.certifierArray=certifierArray;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public PrincipleAndCertifierAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {
        // create a new view
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.principle_and_certifier_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(PrincipleAndCertifierAdapter.ViewHolder holder, int position) {
        holder.principleTextView.setText(principleArray.get(position));
        holder.certifierTextView.setText(certifierArray.get(position));
    }



    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return principleArray.size();
    }
}