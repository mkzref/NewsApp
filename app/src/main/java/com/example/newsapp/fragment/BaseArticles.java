package com.example.newsapp.fragment;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.example.newsapp.Adapter.NAdapter;
import com.example.newsapp.NewsP;
import com.example.newsapp.model.News;
import com.example.newsapp.NewsLoad;
import com.example.newsapp.R;
import com.example.newsapp.recyclerView;
import com.example.newsapp.utils.Cons;
import java.util.ArrayList;
import java.util.List;


public class BaseArticles extends Fragment implements LoaderManager.LoaderCallbacks<List<News>> {

    TextView mEmpty;
    View mLoadingIndicator;
     static final int newsid = 1;
     NAdapter Adapter;
     SwipeRefreshLayout RefreshLayout;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View RView = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView r = RView.findViewById(R.id.recycler_view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        r.setLayoutManager(layoutManager);
        r.setHasFixedSize(true);

        r.setLayoutManager(layoutManager);
        RefreshLayout = RView.findViewById(R.id.swipe_refresh);




        mLoadingIndicator = RView.findViewById(R.id.loading_indicator);
        mEmpty = RView.findViewById(R.id.empty_view);
        r.setEmpty(mEmpty);
        Adapter = new NAdapter(getActivity(), new ArrayList<News>());
        r.setAdapter(Adapter);
        initLoder(isConnected());

        return RView;
    }

    @NonNull
    @Override
    public Loader<List<News>> onCreateLoader(int i, Bundle bundle) {
        Uri.Builder uriBuilder = NewsP.getPreferredUri(getContext());
        return new NewsLoad(getActivity(), uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<News>> loader, List<News> newsData) {
        mLoadingIndicator.setVisibility(View.GONE);
        mEmpty.setText(R.string.no_news);
        Adapter.clearAll();
        if (newsData != null && !newsData.isEmpty()) {
            Adapter.addAll(newsData);
        }
        RefreshLayout.setRefreshing(false);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<News>> loader) {
        Adapter.clearAll();
    }



    private boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        return (networkInfo != null && networkInfo.isConnected());
    }

    private void initLoder(boolean isConnected) {
        if (isConnected) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(newsid, null, this);
        } else {
            mLoadingIndicator.setVisibility(View.GONE);
            mEmpty.setText(R.string.no_internet_connection);
            mEmpty.setCompoundDrawablesWithIntrinsicBounds(Cons.DEFAULT_NUMBER,
                    R.drawable.ic_action_wifi, Cons.DEFAULT_NUMBER, Cons.DEFAULT_NUMBER);
        }
    }




}
