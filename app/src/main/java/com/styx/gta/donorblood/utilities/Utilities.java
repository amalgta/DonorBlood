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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

    public static int findAge(String dob) {
        final String TAG = "findAge";
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

    public static int findBirthYear(int age) {
        Calendar mToday = Calendar.getInstance();
        return (mToday.get(Calendar.YEAR) - age);
    }

    public static String getMD5(String payload) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(payload.getBytes());
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

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Intent getFacebookIntent(Context context, String profile_id) {
        try {
            context.getPackageManager()
                    .getPackageInfo("com.facebook.katana", 0);
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("fb://profile/" + profile_id));
        } catch (Exception e) {
            return getGenericIntent("https://www.facebook.com/" + profile_id);
        }
    }

    public static Intent getGenericIntent(String url) {
        return new Intent(Intent.ACTION_VIEW,
                Uri.parse(url));
    }
}
