package com.example.newsapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.preference.PreferenceManager;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Html;
import android.text.format.DateUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.newsapp.model.News;
import com.example.newsapp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class NAdapter extends RecyclerView.Adapter<NAdapter.ViewHolder> {
    private Context mContext;
    private List<News> mNewsList;
    private SharedPreferences shp;

    public NAdapter(Context context, List<News> newsList) {
        mContext = context;
        mNewsList = newsList;
    }

    private static long getDateInMillis(String formattedDate) {
        SimpleDateFormat Format =
                new SimpleDateFormat("MMM d, yyyy  h:mm a");
        long datemili;
        Date dateObject;
        try {
            dateObject = Format.parse(formattedDate);
            datemili = dateObject.getTime();
            return datemili;
        } catch (ParseException e) {

        }
        return 0;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newsitems, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        shp = PreferenceManager.getDefaultSharedPreferences(mContext);
        final News CNews = mNewsList.get(position);

        holder.title.setText(CNews.getTitle());
        holder.section.setText(CNews.getSection());
        if (CNews.getAuthor() == null) {
            holder.author.setVisibility(View.GONE);
        } else {
            holder.author.setVisibility(View.VISIBLE);
            holder.author.setText(CNews.getAuthor());
        }

        holder.date.setText(getTimeDifference(formatDate(CNews.getDate())));
        String trailTextHTML = CNews.getTrailTextHtml();
        holder.trail.setText(Html.fromHtml(Html.fromHtml(trailTextHTML).toString()));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Uri nUri = Uri.parse(CNews.getUrl());
                Intent intent = new Intent(Intent.ACTION_VIEW, nUri);
                mContext.startActivity(intent);
            }
        });

        if (CNews.getThumbnail() == null) {
            holder.thumbnailImage.setVisibility(View.GONE);
        } else {
            holder.thumbnailImage.setVisibility(View.VISIBLE);

            Glide.with(mContext.getApplicationContext())
                    .load(CNews.getThumbnail())
                    .into(holder.thumbnailImage);
        }

        holder.shareImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareData(CNews);
            }
        });
    }


    private void shareData(News news) {
        Intent sIntent = new Intent(Intent.ACTION_SEND);
        sIntent.setType("text/plain");
        sIntent.putExtra(Intent.EXTRA_TEXT,
                news.getTitle() + " : " + news.getUrl());
        mContext.startActivity(Intent.createChooser(sIntent,
               "Share Article"));
    }
    public void clearAll() {
        mNewsList.clear();
        notifyDataSetChanged();
    }
    public void addAll(List<News> newsList) {
        mNewsList.clear();
        mNewsList.addAll(newsList);
        notifyDataSetChanged();
    }

    private String formatDate(String dateStringUTC) {

        SimpleDateFormat Format2 =
                new SimpleDateFormat("yyyy-MM-dd'T'kk:mm:ss'Z'");
        Date dateObject = null;
        try {
            dateObject = Format2.parse(dateStringUTC);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat D = new SimpleDateFormat("MMM d, yyyy  h:mm a", Locale.ENGLISH);
        String DateUTC = D.format(dateObject);
        D.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = null;
        try {
            date = D.parse(DateUTC);
            D.setTimeZone(TimeZone.getDefault());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return D.format(date);
    }

    private CharSequence getTimeDifference(String formattedDate) {
        long ctime = System.currentTimeMillis();
        long publicTime = getDateInMillis(formattedDate);
        return DateUtils.getRelativeTimeSpanString(publicTime, ctime,
                DateUtils.SECOND_IN_MILLIS);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView section;
        private TextView author;
        private TextView date;
        private ImageView thumbnailImage;
        private ImageView shareImage;
        private TextView trail;
        private CardView cardView;

        ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.card);
            section = itemView.findViewById(R.id.section);
            author = itemView.findViewById(R.id.author);
            date = itemView.findViewById(R.id.date);
            thumbnailImage = itemView.findViewById(R.id.thumbnail);
            shareImage = itemView.findViewById(R.id.share);
            trail = itemView.findViewById(R.id.trail);
            cardView = itemView.findViewById(R.id.card_view);
        }
    }
}
