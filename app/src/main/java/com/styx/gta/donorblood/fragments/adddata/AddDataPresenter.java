package com.styx.gta.donorblood.fragments.adddata;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.styx.gta.donorblood.utilities.Utilities;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


class AddDataPresenter implements AddDataContract.Presenter {
    private AddDataContract.View view;

    AddDataPresenter(AddDataContract.View view) {
        this.view = view;
    }

    public void request() {
    }

    String MD5(String password)  {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte byteData[] = md.digest();
            //convert the byte to hex format method 1
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            //convert the byte to hex format method 2
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                String hex = Integer.toHexString(0xff & byteData[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();

        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void authenticateUser(final String mAccessCode) {
        final String dbFile = "Config/ADMIN_SECRET_HASH";
        DatabaseReference mMessagesRef = Utilities.getDB(dbFile);
        mMessagesRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String data=dataSnapshot.getValue(String.class);
                if(MD5(mAccessCode).equals(data)){
                    view.authenticationResult(true);
                }else {
                    view.authenticationResult(false);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
