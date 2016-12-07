package com.styx.gta.donorblood.fragments.about;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.styx.gta.donorblood.utilities.Utilities;



class AboutPreferencePresenter implements AboutPreferenceContract.Presenter {
    private AboutPreferenceContract.View view;

    public AboutPreferencePresenter(AboutPreferenceContract.View view) {
        this.view = view;
    }

    public void request() {
    }


    @Override
    public void doValidateUser(final String adminLaunchCode) {
        final String dbFile = "Config/ADMIN_SECRET_HASH";
        DatabaseReference mMessagesRef = Utilities.getDB(dbFile);
        mMessagesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String adminCodeHash = dataSnapshot.getValue(String.class);
                view.validationResult(Utilities.getMD5(adminLaunchCode).equals(adminCodeHash));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
