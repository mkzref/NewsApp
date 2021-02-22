package com.example.newsapp.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.loader.content.Loader;

import com.example.newsapp.NewsP;
import com.example.newsapp.model.News;
import com.example.newsapp.NewsLoad;
import com.example.newsapp.R;

import java.util.List;


public class Business extends BaseArticles {

    @NonNull
    @Override
    public Loader<List<News>> onCreateLoader(int i, Bundle bundle) {
        String url = NewsP.getPreferredUrl(getContext(), getString(R.string.business));

        return new NewsLoad(getActivity(),url);
    }
}
