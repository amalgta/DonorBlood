package com.styx.gta.donorblood;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.styx.gta.donorblood.base.BaseFragment;
import com.styx.gta.donorblood.constants.Constants;
import com.styx.gta.donorblood.constants.UserAction;
import com.styx.gta.donorblood.fragments.HomeFragment;
import com.styx.gta.donorblood.fragments.HomeFragment2;
import com.styx.gta.donorblood.fragments.HomeFragment3;
import com.styx.gta.donorblood.interfaces.UserActionListener;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, UserActionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Check if first run
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean isFirstRun = prefs.getBoolean(getString(R.string.pref_is_firstrun), true);
        if (isFirstRun) {
            SharedPreferences.Editor edit = prefs.edit();
            edit.putBoolean(getString(R.string.pref_is_firstrun), Boolean.FALSE);
            edit.commit();
            showIntro();
        }
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nv_sidebar);
        navigationView.setNavigationItemSelectedListener(this);
        doUserAction(UserAction.FIRST);
    }

    private void showIntro() {
        Intent mHome = new Intent(HomeActivity.this, SplashScreenActivity.class);
        startActivity(mHome);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (getTopFragment().isRoot()) {
            finish();
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
        } else if (id == R.id.nav_slideshow) {
            doUserAction(UserAction.SECOND);
        } else if (id == R.id.nav_manage) {
            doUserAction(UserAction.THIRD);
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void doUserAction(UserAction mUserAction) {
        Fragment mFragment;
        switch (mUserAction) {
            case FIRST:
                if (isFragmentExistsInBackStack(HomeFragment.TAG)) {
                    if (getTopFragment() instanceof HomeFragment)
                        return;
                    popBackStack(HomeFragment.TAG, 0);
                } else {
                    mFragment = new HomeFragment();
                    addFragment(R.id.fl_content, mFragment, HomeFragment.TAG);
                }
                break;
            case SECOND:
                if (isFragmentExistsInBackStack(HomeFragment2.TAG)) {
                    if (getTopFragment() instanceof HomeFragment2)
                        return;
                    popBackStack(HomeFragment2.TAG, 0);
                } else {
                    mFragment = new HomeFragment2();
                    addFragment(R.id.fl_content, mFragment, HomeFragment2.TAG);
                }
                break;
            case THIRD:
                if (isFragmentExistsInBackStack(HomeFragment3.TAG)) {
                    if (getTopFragment() instanceof HomeFragment3)
                        return;
                    popBackStack(HomeFragment3.TAG, 0);
                } else {
                    mFragment = new HomeFragment3();
                    addFragment(R.id.fl_content, mFragment, HomeFragment3.TAG);
                }
                break;
        }
    }

    protected boolean isFragmentExistsInBackStack(String tag) {
        if (getSupportFragmentManager().findFragmentByTag(tag) != null)
            return true;
        else
            return false;
    }

    public void addFragment(final int mContainer, final Fragment mFragment,
                            final String mTag) {
        final FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        fragmentTransaction.replace(mContainer, mFragment, mTag);
        fragmentTransaction.addToBackStack(mTag);
        fragmentTransaction.commit();
    }

    public BaseFragment getTopFragment() {
        return ((BaseFragment) this.getSupportFragmentManager().findFragmentById(R.id.fl_content));
    }

    public void popBackStack(String tag, int flag) {
        this.getSupportFragmentManager().popBackStack(tag, flag);
    }
}
