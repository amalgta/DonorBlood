package com.styx.gta.donorblood.fragments.donorlist.controller;

import android.os.Bundle;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.styx.gta.donorblood.constants.Constants;
import com.styx.gta.donorblood.fragments.donorlist.presenter.DonorPresenter;
import com.styx.gta.donorblood.models.BloodGroup;
import com.styx.gta.donorblood.models.Donor;
import com.styx.gta.donorblood.utilities.Logger;

import static com.styx.gta.donorblood.utilities.Utilities.getDB;

/**
 * Created by amal.george on 28-11-2016.
 */

public class DonorController {

    private final String TAG = "DonorController";
    private final String dbFile = "Data/Donor";
    private final String fieldParameter = "bloodGroup";
    private final DonorPresenter presenter;
    private BloodGroup bloodGroup;

    public DonorController(DonorPresenter presenter) {
        this.presenter = presenter;
    }

    public DonorController(DonorPresenter presenter, BloodGroup bloodGroup) {
        this.presenter = presenter;
        this.bloodGroup = bloodGroup;
    }

    public void request() {
        try {
            DatabaseReference mMessagesRef = getDB(dbFile);
            final Query query = mMessagesRef.orderByChild(fieldParameter).equalTo(bloodGroup.getObjectID());
            query.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    presenter.sendToAdapter(dataSnapshot.getValue(Donor.class));
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
        } catch (Exception e) {
            Logger.e(TAG, e.toString());
        }
    }
}
