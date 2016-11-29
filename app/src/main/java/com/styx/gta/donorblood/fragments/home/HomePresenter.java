package com.styx.gta.donorblood.fragments.home;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.styx.gta.donorblood.constants.Constants;
import com.styx.gta.donorblood.models.BloodGroup;
import com.styx.gta.donorblood.models.Donor;
import com.styx.gta.donorblood.utilities.Logger;
import com.styx.gta.donorblood.utilities.Utilities;

/**
 * Created by amal.george on 28-11-2016.
 */

class HomePresenter implements HomeContract.Presenter {
    private HomeContract.View mView;

    HomePresenter(HomeContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void request() {
        String dbFile = "Data/BloodGroup";
        DatabaseReference mMessagesRef = Utilities.getDB(dbFile);
        final Query query = mMessagesRef;
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mView.getAdapter().addItem(dataSnapshot.getValue(BloodGroup.class));
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
    public void requestTotalUserCount() {
        String dbFile = "Data";
        final Query query = Utilities.getDB(dbFile).orderByChild("Donor");
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Logger.e("TAG",dataSnapshot.toString());
                mView.setTotalUserCount(dataSnapshot.getChildrenCount());
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
