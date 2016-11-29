package com.styx.gta.donorblood.fragments.donorlist;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.styx.gta.donorblood.constants.Constants;
import com.styx.gta.donorblood.models.BloodGroup;
import com.styx.gta.donorblood.models.Donor;
import com.styx.gta.donorblood.utilities.Utilities;

/**
 * Created by amal.george on 28-11-2016.
 */

class DonorListPresenter implements DonorListContract.Presenter {
    private DonorListContract.View mView;


    private final String TAG = "DonorListPresenter";
    private final String dbFile = "Data/Donor";
    private final String fieldParameter = "bloodGroup";

    DonorListPresenter(DonorListContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void request() {
        DatabaseReference mMessagesRef = Utilities.getDB(dbFile);
        BloodGroup thisGroup = (BloodGroup) mView.getViewArguments().getSerializable(Constants.FragmentParameters.keyObject);
        final Query query = mMessagesRef.orderByChild(fieldParameter).equalTo(thisGroup.getObjectID());
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mView.getAdapter().addItem(dataSnapshot.getValue(Donor.class));
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
