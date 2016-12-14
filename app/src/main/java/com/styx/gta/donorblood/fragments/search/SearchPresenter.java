package com.styx.gta.donorblood.fragments.search;


import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.styx.gta.donorblood.constants.Constants;
import com.styx.gta.donorblood.models.BloodGroup;
import com.styx.gta.donorblood.models.Donor;
import com.styx.gta.donorblood.utilities.Logger;
import com.styx.gta.donorblood.utilities.Utilities;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;


class SearchPresenter implements SearchContract.Presenter {
    private SearchContract.View view;

    SearchPresenter(SearchContract.View view) {
        this.view = view;
    }

    public void request() {
    }

    //                         0            1            2             3          4
    //presenter.requestMatches(et_address, sp_blood_group, Donor.Sex, et_min_age, et_max_age);
    @Override
    public void requestMatches(final String... parameters) {
        /** Try to optimise as possible. This is the most resource using task in the app**/
        String dbFile = "Data/Donor";
        DatabaseReference mMessagesRef = Utilities.getDB(dbFile);
        final Query query;
        if (!parameters[1].contains("ALL"))
            query = mMessagesRef.orderByChild("bloodGroup").equalTo(parameters[1]);
        else
            query = mMessagesRef;
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                view.onDataReceive();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Donor theDonor = child.getValue(Donor.class);
                    if ((!TextUtils.isEmpty(parameters[0])) && (!theDonor.getAddress().contains(parameters[0])))
                        continue;
                    if (!(parameters[2].contains("both")) && ((!theDonor.getSex().contains(parameters[2]))))
                        continue;
                    Date mDob;
                    try {
                        mDob = Constants.simpleDateFormat.parse(theDonor.getDob());
                    } catch (ParseException e) {
                        Logger.e("SearchPresenter", e.toString());
                        continue;
                    }
                    Calendar min = Calendar.getInstance(), max = Calendar.getInstance();
                    if (!TextUtils.isEmpty(parameters[3])) {
                        min.set(Utilities.findBirthYear(Integer.parseInt(parameters[3])), 1, 1);
                        if (mDob.after(min.getTime()))
                            continue;
                    }
                    if (!TextUtils.isEmpty(parameters[4])) {
                        max.set(Utilities.findBirthYear(Integer.parseInt(parameters[4])), 1, 1);
                        if (mDob.before(max.getTime()))
                            continue;
                    }
                    view.addSearchResult(theDonor);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void requestBloodGroups() {
        String dbFile = "Data/BloodGroup";
        DatabaseReference mMessagesRef = Utilities.getDB(dbFile);
        mMessagesRef.addChildEventListener(new ChildEventListener() {
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
