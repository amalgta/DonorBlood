package com.styx.gta.donorblood.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.styx.gta.donorblood.R;
import com.styx.gta.donorblood.base.BaseActivity;
import com.styx.gta.donorblood.constants.UserAction;

public class HomeActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    Toolbar toolbar;
    ImageView iv_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        iv_search = (ImageView) findViewById(R.id.iv_search);
        iv_search.setOnClickListener(this);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nv_sidebar);
        navigationView.setNavigationItemSelectedListener(this);

        doUserAction(UserAction.HOME_SCREEN, new Bundle());


    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {
            case R.id.nav_camera:
                doUserAction(UserAction.HOME_SCREEN, new Bundle());
                break;
            case R.id.nav_search:
                doUserAction(UserAction.SEARCH_FRAGMENT, new Bundle());
                break;
            case R.id.nav_about:
                doUserAction(UserAction.ABOUT_FRAGMENT, new Bundle());
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_search:
                doUserAction(UserAction.SEARCH_FRAGMENT, new Bundle());
                break;
        }
    }
}
