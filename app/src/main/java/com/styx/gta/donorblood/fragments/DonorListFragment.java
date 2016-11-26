package com.styx.gta.donorblood.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.AbsListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.styx.gta.donorblood.R;
import com.styx.gta.donorblood.adapters.BloodGroupAdapter;
import com.styx.gta.donorblood.adapters.DonorAdapter;
import com.styx.gta.donorblood.base.BaseFragment;
import com.styx.gta.donorblood.constants.Constants;
import com.styx.gta.donorblood.constants.UserAction;
import com.styx.gta.donorblood.models.BloodGroup;
import com.styx.gta.donorblood.models.Donor;

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
        setScreenLayout(R.layout.fragment_donor_list);
    }

    @Override
    protected void setUI(Bundle savedInstanceState) {
        final List<Donor> donorList = new ArrayList<>();

        final DonorAdapter mAdapter = new DonorAdapter(donorList, getContext(), new DonorAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Donor thisDonor) {
                Bundle mBundle = new Bundle();
                mBundle.putString(Constants.FragmentParameters.objectID, thisDonor.getObjectID());
                getBase().doUserAction(UserAction.DONOR_DETAIL_FRAGMENT, mBundle);
            }
        });
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        RecyclerView mRecyclerView = ((RecyclerView) rootView.findViewById(R.id.rv_donor_list));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(mLayoutManager);
        Query myTopPostsQuery = databaseReference.child("Data/Donor");
        myTopPostsQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Donor thisDonor = postSnapshot.getValue(Donor.class);
                    donorList.add(thisDonor);
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
