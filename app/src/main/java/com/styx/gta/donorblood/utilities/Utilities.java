package com.styx.gta.donorblood.utilities;

import android.content.Context;

import com.styx.gta.donorblood.activities.HomeActivity;

/**
 * Created by amalg on 26-11-2016.
 */

public class Utilities {
    public static HomeActivity getApp(Context context) {
        return (HomeActivity) context;
    }
}
