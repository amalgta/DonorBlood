package com.styx.gta.donorblood.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.styx.gta.donorblood.R;
import com.styx.gta.donorblood.adapters.BloodGroupAdapter;
import com.styx.gta.donorblood.base.BaseFragment;
import com.styx.gta.donorblood.constants.Constants;
import com.styx.gta.donorblood.constants.UserAction;
import com.styx.gta.donorblood.models.BloodGroup;
import com.styx.gta.donorblood.ui.widget.FontTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amal.george on 24-11-2016.
 */

public class SearchFragment extends BaseFragment {
    public static final String TAG = "SearchFragment";


    @Override
    protected void initUI() {
        setScreenTitle("SearchFragment");
        setScreenLayout(R.layout.fragment_search);
    }

    @Override
    protected void setUI(Bundle savedInstanceState) {
    }
}
