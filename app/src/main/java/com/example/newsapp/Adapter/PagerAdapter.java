package com.example.newsapp.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;

import androidx.fragment.app.FragmentPagerAdapter;

import com.example.newsapp.R;
import com.example.newsapp.fragment.Business;
import com.example.newsapp.fragment.Culture;
import com.example.newsapp.fragment.Environment;
import com.example.newsapp.fragment.Fashion;
import com.example.newsapp.fragment.HomeFragment;
import com.example.newsapp.fragment.Science;
import com.example.newsapp.fragment.Society;
import com.example.newsapp.fragment.Sport;
import com.example.newsapp.fragment.World;
import com.example.newsapp.utils.Cons;



public class PagerAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public PagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {

            case Cons.HOME:
                return new HomeFragment();

            case Cons.WORLD:
                return new World();
            case Cons.SCIENCE:
                return new Science();

            case Cons.SPORT:
                return new Sport();

            case Cons.ENVIRONMENT:
                return new Environment();

            case Cons.SOCIETY:
                return new Society();

            case Cons.FASHION:
                return new Fashion();

            case Cons.BUSINESS:
                return new Business();

            case Cons.CULTURE:
                return new Culture();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 10;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        int id;
        switch (position) {
            case Cons.HOME:
                id = R.string.ic_title_home;
                break;
            case Cons.WORLD:
                id = R.string.ic_title_world;
                break;
            case Cons.SCIENCE:
                id = R.string.ic_title_science;
                break;
            case Cons.SPORT:
                id = R.string.ic_title_sport;
                break;
            case Cons.ENVIRONMENT:
                id = R.string.ic_title_environment;
                break;
            case Cons.SOCIETY:
                id = R.string.ic_title_society;
                break;
            case Cons.FASHION:
                id = R.string.ic_title_fashion;
                break;
            case Cons.BUSINESS:
                id = R.string.ic_title_business;
                break;
            default:
                id = R.string.ic_title_culture;
                break;
        }
        return mContext.getString(id);
    }
}