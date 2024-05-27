package com.nauroo.ppg.ui.home.more.notification_and_events.events;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nauroo.ppg.R;
import com.nauroo.ppg.model.EventsResponseModel;
import com.nauroo.ppg.ui.home.HomeActivity;

import java.util.List;

/**
 * Created by Mohan M on 12/12/2017.
 */

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ViewHolder> {
    Context context;
    List<EventsResponseModel> events;
    String[] startDate;
    String[] endDate;
    String[] finalStartTime;
    String[] finalEndTime;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tittleTextView, descriptionTextView, dateTextView, timeTextView;

        public ViewHolder(View v) {
            super(v);
            tittleTextView = (TextView) v.findViewById(R.id.tittleTextView);
            descriptionTextView = (TextView) v.findViewById(R.id.descriptionTextView);
            dateTextView = (TextView) v.findViewById(R.id.dateTextVew);
            timeTextView = (TextView) v.findViewById(R.id.timeTextView);

        }
    }


    public EventsAdapter(Context context, List<EventsResponseModel> events) {
        this.context = context;
        this.events = events;
    }


    @Override
    public EventsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {

        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.events_layout, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(EventsAdapter.ViewHolder holder, final int position) {

        holder.tittleTextView.setText(events.get(position).getTranslations().get(0).getTitle());
        holder.descriptionTextView.setText(events.get(position).getTranslations().get(0).getBrief());

        startDate = events.get(position).getStart_date().split(" ");
        endDate=events.get(position).getEnd_date().split(" ");
        if(startDate[0].replace("-", "/").equals(endDate[0].replace("-", "/"))){
            holder.dateTextView.setText(startDate[0].replace("-", "/"));
        }else {
            holder.dateTextView.setText(startDate[0].replace("-", "/") + " - " + endDate[0].replace("-", "/"));
        }
       finalStartTime=startDate[1].split(":");
        finalEndTime=endDate[1].split(":");

        holder.timeTextView.setText(finalStartTime[0]+":"+finalStartTime[1]+" - "+finalEndTime[0]+":"+finalEndTime[1]);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventDetailFragment eventDetailFragment = EventDetailFragment.newInstance(events.get(position));
                HomeActivity instance = (HomeActivity) context;
                instance.addFragment(eventDetailFragment, eventDetailFragment.getClass().getName(), context.getString(R.string.event_detail));
            }
        });
    }


    @Override
    public int getItemCount() {
        return events.size();
    }
}