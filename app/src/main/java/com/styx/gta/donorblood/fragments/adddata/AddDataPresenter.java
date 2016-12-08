package com.styx.gta.donorblood.fragments.adddata;


import android.os.Bundle;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.styx.gta.donorblood.base.BaseActivity;
import com.styx.gta.donorblood.constants.Constants;
import com.styx.gta.donorblood.models.BloodGroup;
import com.styx.gta.donorblood.models.Donor;
import com.styx.gta.donorblood.utilities.Utilities;


class AddDataPresenter implements AddDataContract.Presenter {
    private AddDataContract.View view;
    private final String TAG = "DonorListPresenter";
    private final String dbFile = "Data/BloodGroup";

    AddDataPresenter(AddDataContract.View view) {
        this.view = view;
    }

    public void request() {

    }

    @Override
    public void requestBloodGroups() {
        DatabaseReference mMessagesRef = Utilities.getDB(dbFile);
        final Query query = mMessagesRef;
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                BloodGroup bloodGroup = dataSnapshot.getValue(BloodGroup.class);
                view.addGroupItem(bloodGroup.getObjectID(), bloodGroup.getName());
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
}
