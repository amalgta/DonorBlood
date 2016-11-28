package com.styx.gta.donorblood.fragments.donorlist.view;

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
import com.styx.gta.donorblood.base.BaseFragment;
import com.styx.gta.donorblood.constants.Constants;
import com.styx.gta.donorblood.constants.UserAction;
import com.styx.gta.donorblood.fragments.chat.view.MessageAdapterImpl;
import com.styx.gta.donorblood.fragments.donorlist.presenter.DonorPresenterImpl;
import com.styx.gta.donorblood.models.Donor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amal.george on 24-11-2016.
 */

public class DonorListFragment extends BaseFragment {
    public static final String TAG = "DonorListFragment";
    DonorAdapterImpl adapter;

    @Override
    protected void initUI() {
        setScreenTitle("DonorListFragment");
        setScreenLayout(R.layout.fragment_donor_list);
    }

    @Override
    protected void setUI(Bundle savedInstanceState) {
        RecyclerView mRecyclerView = ((RecyclerView) rootView.findViewById(R.id.rv_donor_list));
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        adapter = new DonorAdapterImpl(getContext());
        adapter.request();
        mRecyclerView.setAdapter(adapter);

    }
}
