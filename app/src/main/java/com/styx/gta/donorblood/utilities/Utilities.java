package com.styx.gta.donorblood.utilities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.styx.gta.donorblood.activities.HomeActivity;
import com.styx.gta.donorblood.constants.Constants;

import java.text.ParseException;
import java.util.Calendar;

/**
 * Created by amalg on 26-11-2016.
 */

public class Utilities {
    public static HomeActivity getApp(Context context) {
        return (HomeActivity) context;
    }

    public static void doCall(Context context, String number) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + number));
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            String[] mPermission = {Manifest.permission.CALL_PHONE};
            //TODO RequestResponse Implementation
            ActivityCompat.requestPermissions(getApp(context), mPermission, Constants.RequestCodes.REQUEST_CODE);
            return;
        }
        context.startActivity(callIntent);
    }

    public static DatabaseReference getDB(String child) {
        return FirebaseDatabase.getInstance().getReference().child(child);
    }

    public static int getAge(String dob) {
        final String TAG = "getAge";
        Calendar mDob = Calendar.getInstance();
        try {
            mDob.setTime(Constants.simpleDateFormat.parse(dob));
        } catch (ParseException e) {
            Logger.e(TAG, e.toString());
            e.printStackTrace();
        }
        Calendar mToday = Calendar.getInstance();
        return (mToday.get(Calendar.YEAR) - mDob.get(Calendar.YEAR));
    }
}
