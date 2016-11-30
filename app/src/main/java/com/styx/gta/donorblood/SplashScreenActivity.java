package com.styx.gta.donorblood;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.styx.gta.donorblood.activities.HomeActivity;

/**
 * Created by amal.george on 24-11-2016.
 */

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}