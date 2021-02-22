package com.example.newsapp;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.example.newsapp.Adapter.PagerAdapter;
import com.example.newsapp.utils.Cons;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
/*
Author @ Marwa Hatamleh
 */
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ViewPager Pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//////////////////////******************************************************************************
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        Pager = findViewById(R.id.viewpager);
        TabLayout tabLayout = findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(Pager);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        NavigationView navigationView = findViewById(R.id.nav_view);
        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(this);
        onNavigationItemSelected(navigationView.getMenu().getItem(0).setChecked(true));
        PagerAdapter pagerAdapter =
                new PagerAdapter(this, getSupportFragmentManager());
        Pager.setAdapter(pagerAdapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Pager.setCurrentItem(Cons.HOME);
        } else if (id == R.id.nav_world) {
            Pager.setCurrentItem(Cons.WORLD);
        } else if (id == R.id.nav_science) {
            Pager.setCurrentItem(Cons.SCIENCE);
        } else if (id == R.id.nav_sport) {
            Pager.setCurrentItem(Cons.SPORT);
        } else if (id == R.id.nav_environment) {
            Pager.setCurrentItem(Cons.ENVIRONMENT);
        } else if (id == R.id.nav_society) {
            Pager.setCurrentItem(Cons.SOCIETY);
        } else if (id == R.id.nav_fashion) {
            Pager.setCurrentItem(Cons.FASHION);
        } else if (id == R.id.nav_business) {
            Pager.setCurrentItem(Cons.BUSINESS);
        } else if (id == R.id.nav_culture) {
            Pager.setCurrentItem(Cons.CULTURE);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



}
