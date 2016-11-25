package com.styx.gta.donorblood.fragments;

import android.os.Bundle;
import android.widget.TextView;

import com.styx.gta.donorblood.R;
import com.styx.gta.donorblood.base.BaseFragment;
import com.styx.gta.donorblood.models.BloodGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amal.george on 24-11-2016.
 */

public class HomeFragment2 extends BaseFragment {
    public static final String TAG = "HomeFragment2";

    @Override
    protected void initUI() {
        setScreenTitle("HomeFragment2");
        setScreenLayout(R.layout.nav_header);

    }

    @Override
    protected void setUI(Bundle savedInstanceState) {
        Bundle b=getArguments();
        String s=(String)b.get("A");
        ((TextView)rootView.findViewById(R.id.textView)).setText(s);
    }
}
