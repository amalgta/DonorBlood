package com.styx.gta.donorblood.fragments.home.controller;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.styx.gta.donorblood.fragments.donorlist.presenter.DonorPresenter;
import com.styx.gta.donorblood.fragments.home.presenter.BloodGroupPresenter;
import com.styx.gta.donorblood.models.BloodGroup;
import com.styx.gta.donorblood.models.Donor;

import static com.styx.gta.donorblood.utilities.Utilities.getDB;

/**
 * Created by amal.george on 28-11-2016.
 */

public class BloodGroupController {

    private final BloodGroupPresenter presenter;
    private final Query query;

    public BloodGroupController(BloodGroupPresenter presenter) {
        this.presenter = presenter;
        DatabaseReference mMessagesRef = getDB("Data/BloodGroup");
        this.query = mMessagesRef;
    }

    public void request() {

        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                presenter.sendToAdapter(dataSnapshot.getValue(BloodGroup.class));
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
