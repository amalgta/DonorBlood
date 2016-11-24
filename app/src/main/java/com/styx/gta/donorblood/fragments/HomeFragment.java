package com.styx.gta.donorblood.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.styx.gta.donorblood.R;
import com.styx.gta.donorblood.base.BaseFragment;

/**
 * Created by amal.george on 24-11-2016.
 */

public class HomeFragment extends BaseFragment {
    public static final String TAG = "HomeFragment";

    @Override
    protected void setUI() {
        setRoot(true);
        setScreenTitle("HomeFragment");
        setScreenLayout(R.layout.layout_splash_screen);
    }
}
