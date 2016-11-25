package com.styx.gta.donorblood;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by amal.george on 24-11-2016.
 */

public class DonorBloodApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
