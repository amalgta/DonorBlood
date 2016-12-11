package com.styx.gta.donorblood.fragments.adddata;


import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Query;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;
import com.styx.gta.donorblood.base.BaseActivity;
import com.styx.gta.donorblood.constants.Constants;
import com.styx.gta.donorblood.models.BloodGroup;
import com.styx.gta.donorblood.models.Donor;
import com.styx.gta.donorblood.utilities.Utilities;


class AddDataPresenter implements AddDataContract.Presenter {
    private AddDataContract.View view;
    private final String TAG = "DonorListPresenter";

    AddDataPresenter(AddDataContract.View view) {
        this.view = view;
    }

    public void request() {

    }

    @Override
    public void requestBloodGroups() {
        String dbFile = "Data/BloodGroup";
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

    @Override
    public void saveDonor(final Donor donor) {
        String dbFile = "Data/Donor";
        DatabaseReference mMessagesRef = Utilities.getDB(dbFile);
        DatabaseReference newDonor = mMessagesRef.push();
        donor.setObjectID(newDonor.getKey());
        mMessagesRef.child(donor.getObjectID()).setValue(donor, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError == null) {
                    incrementBloodGroupDonorCount(donor.getBloodGroup());
                } else {
                    view.onAddUserSuccess(false);
                }
            }
        });
    }

    private void incrementBloodGroupDonorCount(String groupName) {
        final String dbFile = "Data/BloodGroup";
        final DatabaseReference mMessagesRef = Utilities.getDB(dbFile);
        mMessagesRef.orderByChild("name").equalTo(groupName).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mMessagesRef.child(dataSnapshot.getKey()).runTransaction(new Transaction.Handler() {
                    @Override
                    public Transaction.Result doTransaction(MutableData mutableData) {
                        BloodGroup bloodGroup = mutableData.getValue(BloodGroup.class);
                        if (bloodGroup == null) {
                            return Transaction.success(mutableData);
                        }
                        bloodGroup.setCount(bloodGroup.getCount() + 1);
                        mutableData.setValue(bloodGroup);
                        return Transaction.success(mutableData);
                    }

                    @Override
                    public void onComplete(DatabaseError databaseError, boolean b, DataSnapshot dataSnapshot) {
                        view.onAddUserSuccess(true);
                    }
                });
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
