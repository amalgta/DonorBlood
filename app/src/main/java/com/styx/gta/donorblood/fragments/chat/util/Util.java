package com.styx.gta.donorblood.fragments.chat.util;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by amalg on 27-11-2016.
 */

public class Util {
    public static DatabaseReference getDB(String child) {
        return FirebaseDatabase.getInstance().getReference().child(child);
    }
}
