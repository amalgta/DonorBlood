package com.styx.gta.donorblood.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.styx.gta.donorblood.R;
import com.styx.gta.donorblood.adapters.DonorAdapter;
import com.styx.gta.donorblood.base.BaseFragment;
import com.styx.gta.donorblood.constants.Constants;
import com.styx.gta.donorblood.models.BloodGroup;
import com.styx.gta.donorblood.models.Donor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amal.george on 24-11-2016.
 */

public class DonorDetailFragment extends BaseFragment {
    public static final String TAG = "DonorDetailFragment";

    @Override
    protected void initUI() {
        setScreenTitle("DonorDetailFragment");
        setScreenLayout(R.layout.fragment_donor_detail);
    }

    @Override
    protected void setUI(Bundle savedInstanceState) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        Query myTopPostsQuery2 = databaseReference.child("Data/Donor/" + getArguments().getString(Constants.FragmentParameters.objectID));
        myTopPostsQuery2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final Donor thisDonor = dataSnapshot.getValue(Donor.class);
                ((TextView) rootView.findViewById(R.id.tv_name)).setText(thisDonor.getName());
                rootView.findViewById(R.id.tv_share_to_whatsapp).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent sendIntent = new Intent();
                        sendIntent.setAction(Intent.ACTION_SEND);
                        sendIntent.setPackage("com.whatsapp");
                        sendIntent.putExtra(Intent.EXTRA_TEXT, thisDonor.getName() + " " + thisDonor.getContact());
                        sendIntent.setType("text/plain");
                        startActivity(sendIntent);
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
