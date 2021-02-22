package com.example.newsapp;

import androidx.loader.content.AsyncTaskLoader;

import android.content.Context;

import com.example.newsapp.model.News;
import com.example.newsapp.utils.Query;


import java.util.List;


public class NewsLoad extends AsyncTaskLoader<List<News>> {
    private String Url;

    public NewsLoad(Context context, String url) {
        super(context);
        Url = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<News> loadInBackground() {
        if (Url == null) {
            return null;
        }
        List<News> newsData = Query.NewsData(Url);
        return newsData;
    }
}

