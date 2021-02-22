package com.example.newsapp;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
/*
Author @ Marwa Hatamleh
 */
public class recyclerView extends RecyclerView {

    private View Empty;
    final private AdapterDataObserver adapterDataObserver = new AdapterDataObserver() {
        @Override
        public void onChanged() {
            checkIfEmpty();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            checkIfEmpty();
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            checkIfEmpty();
        }
    };

    public recyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public recyclerView(Context context, AttributeSet attrs,
                        int defStyle) {
        super(context, attrs, defStyle);
    }

    public recyclerView(@NonNull Context context) {
        super(context);
    }

    private void checkIfEmpty() {
        if (Empty != null && getAdapter() != null) {
            final boolean emptyViewVisible =
                    getAdapter().getItemCount() == 0;
            Empty.setVisibility(emptyViewVisible ? VISIBLE : GONE);
            setVisibility(emptyViewVisible ? GONE : VISIBLE);
        }
    }

    @Override
    public void setAdapter(Adapter adapter) {
        final Adapter adapter1 = getAdapter();
        if (adapter1 != null) {
            adapter1.unregisterAdapterDataObserver(adapterDataObserver);
        }
        super.setAdapter(adapter);
        if (adapter != null) {
            adapter.registerAdapterDataObserver(adapterDataObserver);
        }

        checkIfEmpty();
    }

    public void setEmpty(View emptyView) {
        Empty = emptyView;
        checkIfEmpty();
    }
}

