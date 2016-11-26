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

public class HomeFragment extends BaseFragment {
    public static final String TAG = "HomeFragment";


    @Override
    protected void initUI() {
        setRoot(true);
        setScreenTitle("HomeFragment");
        setScreenLayout(R.layout.fragment_home);
    }

    @Override
    protected void setUI(Bundle savedInstanceState) {
        ((FontTextView) rootView.findViewById(R.id.tv_helloworld)).setText("0 Users Online");
        final List<BloodGroup> bloodGroupList = new ArrayList<>();
        final BloodGroupAdapter  mAdapter = new BloodGroupAdapter(bloodGroupList, getContext(), new BloodGroupAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BloodGroup thisBloodGroup) {
                Bundle mBundle = new Bundle();
                mBundle.putString(Constants.FragmentParameters.objectID, thisBloodGroup.getObjectID());
                getBase().doUserAction(UserAction.DONOR_LIST_FRAGMENT, mBundle);
            }
        });
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        RecyclerView mRecyclerView = ((RecyclerView) rootView.findViewById(R.id.rv_bloodgroup));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(mLayoutManager);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        Query myTopPostsQuery = databaseReference.child("Data/BloodGroup");
        myTopPostsQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    BloodGroup thisGroup = postSnapshot.getValue(BloodGroup.class);
                    bloodGroupList.add(thisGroup);
                    mAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
