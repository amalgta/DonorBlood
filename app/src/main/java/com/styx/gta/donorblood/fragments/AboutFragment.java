package com.styx.gta.donorblood.fragments;

import android.os.Bundle;

import com.styx.gta.donorblood.R;
import com.styx.gta.donorblood.base.BaseFragment;

/**
 * Created by amal.george on 24-11-2016.
 */

public class AboutFragment extends BaseFragment {
    public static final String TAG = "AboutFragment";


    @Override
    protected void initUI() {
        setScreenTitle("AboutFragment");
        setScreenLayout(R.layout.fragment_about);
    }

    @Override
    protected void setUI(Bundle savedInstanceState) {
    }
}
