package com.example.newsapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;

import com.example.newsapp.utils.Cons;


import static com.example.newsapp.utils.Cons.API_KEY;
import static com.example.newsapp.utils.Cons.API_KEY_PARAM;
import static com.example.newsapp.utils.Cons.FORMAT;
import static com.example.newsapp.utils.Cons.FORMAT_PARAM;
import static com.example.newsapp.utils.Cons.FROM_DATE_PARAM;
import static com.example.newsapp.utils.Cons.ORDER;
import static com.example.newsapp.utils.Cons.ORDER_DATE;

import static com.example.newsapp.utils.Cons.PAGE_SIZE;

import static com.example.newsapp.utils.Cons.QUERY_PARAM;
import static com.example.newsapp.utils.Cons.SECTION;
import static com.example.newsapp.utils.Cons.SHOW;
import static com.example.newsapp.utils.Cons.SHOWPARAM;
import static com.example.newsapp.utils.Cons.SHOW_FIELDS;
import static com.example.newsapp.utils.Cons.SHOW_FIELDS_PAR;


/*
Author @ Marwa Hatamleh
 */
public class NewsP {
    public static Uri.Builder getPreferredUri(Context context) {
        SharedPreferences Shared = PreferenceManager.getDefaultSharedPreferences(context);
        String order = Shared.getString(
                context.getString(R.string.settings_order_by_key),
                context.getString(R.string.settings_order_by_default));
        String numitem = Shared.getString(
                context.getString(R.string.settings_number_of_items_key),
                context.getString(R.string.settings_number_of_items_default));

        String fdate = Shared.getString(
                context.getString(R.string.settings_from_date_key),
                context.getString(R.string.settings_from_date_default));

        String ODate = Shared.getString(
                context.getString(R.string.settings_order_date_key),
                context.getString(R.string.settings_order_date_default));
        Uri baseUri = Uri.parse(Cons.NEWS_REQUEST);

        Uri.Builder builder = baseUri.buildUpon();
        builder.appendQueryParameter(QUERY_PARAM, "");
        builder.appendQueryParameter(ORDER, order);
        builder.appendQueryParameter(PAGE_SIZE, numitem);
        builder.appendQueryParameter(ORDER_DATE, ODate);
        builder.appendQueryParameter(FROM_DATE_PARAM, fdate);
        builder.appendQueryParameter(SHOW_FIELDS_PAR, SHOW_FIELDS);
        builder.appendQueryParameter(FORMAT_PARAM, FORMAT);
        builder.appendQueryParameter(SHOWPARAM, SHOW);
        builder.appendQueryParameter(API_KEY_PARAM, API_KEY);

        return builder;
    }
    public static String getPreferredUrl(Context context, String section) {
        Uri.Builder preferredUri = getPreferredUri(context);
        return preferredUri.appendQueryParameter(SECTION, section).toString();
    }
}

