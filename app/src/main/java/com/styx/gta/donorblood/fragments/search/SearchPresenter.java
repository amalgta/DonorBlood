package com.styx.gta.donorblood.fragments.search;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.styx.gta.donorblood.models.BloodGroup;
import com.styx.gta.donorblood.models.Donor;
import com.styx.gta.donorblood.utilities.Utilities;

import java.util.Calendar;

/**
 * Created by amal.george on 28-11-2016.
 */

class SearchPresenter implements SearchContract.Presenter {
    private SearchContract.View mView;

    SearchPresenter(SearchContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void request() {
        Utilities.getDB("Data/BloodGroup").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mView.getBloodGroupAdapter().add(dataSnapshot.getValue(BloodGroup.class).getName());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void searchDonor(final String bloodGroupObjectID, int minAge, final int maxAge, final String address) {
        int minAgeYear = Calendar.getInstance().get(Calendar.YEAR) - minAge;
        int maxAgeYear = Calendar.getInstance().get(Calendar.YEAR) - maxAge;
        Utilities.getDB("Data/Donor").orderByChild("dob").startAt(String.valueOf(maxAgeYear) + "-01-01").endAt(String.valueOf(minAgeYear) + "-01-01").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Donor mDonor = dataSnapshot.getValue(Donor.class);
                if ((mDonor.getBloodGroupCanonicalName().contains(bloodGroupObjectID))) {
                    if (mDonor.getAddress().contains(address)) {
                        mView.showResult(mDonor.getName());
                    }
                }}

                @Override
                public void onChildChanged (DataSnapshot dataSnapshot, String s){

                }

                @Override
                public void onChildRemoved (DataSnapshot dataSnapshot){

                }

                @Override
                public void onChildMoved (DataSnapshot dataSnapshot, String s){

                }

                @Override
                public void onCancelled (DatabaseError databaseError){

                }
            }

            );
        }
    }
