package com.styx.gta.donorblood.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.styx.gta.donorblood.R;
import com.styx.gta.donorblood.base.BaseFragment;
import com.styx.gta.donorblood.ui.widget.FontTextView;

/**
 * Created by amal.george on 24-11-2016.
 */

public class HomeFragment extends BaseFragment {
    public static final String TAG = "HomeFragment";

    @Override
    protected void initUI() {
        setRoot(true);
        setScreenTitle("HomeFragment");
        setScreenLayout(R.layout.layout_main);
    }

    @Override
    protected void setUI(Bundle savedInstanceState) {
        ((FontTextView) rootView.findViewById(R.id.ftv_helloworld)).setText("0 Users Online");
    }
}
