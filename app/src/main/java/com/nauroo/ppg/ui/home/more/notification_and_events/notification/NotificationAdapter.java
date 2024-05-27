package com.nauroo.ppg.ui.home.more.notification_and_events.notification;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.nauroo.ppg.R;
import com.nauroo.ppg.model.NewsResponseModel;
import com.nauroo.ppg.ui.home.HomeActivity;
import com.nauroo.ppg.utils.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

/**
 * Created by Mohan M on 12/11/2017.
 */

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {
    Context context;
    List<NewsResponseModel> news;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tittleTextView, bodyTextView;
        LinearLayout readMoreLayout;

        public ViewHolder(View v) {
            super(v);
            imageView = (ImageView) v.findViewById(R.id.imageView);
            tittleTextView = (TextView) v.findViewById(R.id.tittleTextView);
            bodyTextView = (TextView) v.findViewById(R.id.bodyTextView);
            readMoreLayout = (LinearLayout) v.findViewById(R.id.readMoreLayout);
        }
    }


    public NotificationAdapter(Context context, List<NewsResponseModel> news) {
        this.context = context;
        this.news = news;

    }


    @Override
    public NotificationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {

        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notification_layout, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(final NotificationAdapter.ViewHolder holder, final int position) {
//        new Handler().post(new Runnable() {
//            @Override
//            public void run() {
//                Picasso.with(context).load(Constants.IMAGE_BASE_URL + news.get(position).getImage_url()).resize(holder.imageView.getWidth(), holder.imageView.getHeight()).into(holder.imageView);
//            }
//        });

       Glide.with(context).load(Constants.IMAGE_BASE_URL + news.get(position).getImage_url()).centerCrop().into(holder.imageView);

       // holder.imageView.setImageURI(Uri.parse(Constants.IMAGE_BASE_URL + news.get(position).getImage_url()));
        holder.tittleTextView.setText(Html.fromHtml(news.get(position).getTranslations().get(0).getTitle()));
        holder.bodyTextView.setText(Html.fromHtml(news.get(position).getTranslations().get(0).getDescription()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationDetailFragment notificationFragment = NotificationDetailFragment.newInstance(news.get(position));
                HomeActivity instance = (HomeActivity) context;
                instance.addFragment(notificationFragment, notificationFragment.getClass().getName(), news.get(position).getTranslations().get(0).getTitle());
            }
        });

    }


    @Override
    public int getItemCount() {
        return news.size();
    }
}