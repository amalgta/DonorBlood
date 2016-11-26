package com.styx.gta.donorblood.fragments;

import android.os.Bundle;
import android.widget.TextView;

import com.styx.gta.donorblood.R;
import com.styx.gta.donorblood.base.BaseFragment;
import com.styx.gta.donorblood.constants.Constants;
import com.styx.gta.donorblood.models.BloodGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amal.george on 24-11-2016.
 */

public class DonorListFragment extends BaseFragment {
    public static final String TAG = "DonorListFragment";

    @Override
    protected void initUI() {
        setScreenTitle("DonorListFragment");
        setScreenLayout(R.layout.nav_header);

    }

    @Override
    protected void setUI(Bundle savedInstanceState) {
        String b = getArguments().getString(Constants.FragmentParameters.title);
        ((TextView) rootView.findViewById(R.id.textView)).setText(b);
    }
}
