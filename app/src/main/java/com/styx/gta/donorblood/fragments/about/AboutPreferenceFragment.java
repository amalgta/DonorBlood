package com.styx.gta.donorblood.fragments.about;

import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceGroup;
import android.preference.PreferenceScreen;
import android.util.Log;
import android.widget.Toast;

import com.styx.gta.donorblood.R;
import com.styx.gta.donorblood.base.BaseActivity;
import com.styx.gta.donorblood.constants.UserAction;

/**
 * Created by amal.george on 06-12-2016.
 */

public class AboutPreferenceFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener{
    private static final int MAX_CLICKS_TO_UNLOCK_REG = 7;

    private static final String TAG = "AboutPreferenceFragment";

    private Preference prefVersion;
    private int numTimesVersionClicked;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_about);
        prefVersion = findPreference("version");

        PackageManager pm = getActivity().getPackageManager();
        try {
            prefVersion.setSummary(pm.getPackageInfo(getActivity().getPackageName(), 0).versionName);
        } catch (PackageManager.NameNotFoundException e) {
            Log.wtf(TAG, "Error getting our own package name");
        }

        // Set an OnPreferenceClickListener on all Preferences.
        // Just set the OnPreferenceClickListener for the prefVersion in a normal project.
        PreferenceScreen screen = getPreferenceScreen();
        for (int i = 0; i < screen.getPreferenceCount(); i++) {
            Preference preference = screen.getPreference(i);
            if (preference instanceof PreferenceGroup) {
                PreferenceGroup preferenceGroup = (PreferenceGroup) preference;
                for (int j = 0; j < preferenceGroup.getPreferenceCount(); j++) {
                    preferenceGroup.getPreference(j).setOnPreferenceClickListener(this);
                }
            } else {
                preference.setOnPreferenceClickListener(this);
            }
        }
    }

    @Override public boolean onPreferenceClick(Preference preference) {
        if (preference == findPreference("copyright")) {
            if (++numTimesVersionClicked == MAX_CLICKS_TO_UNLOCK_REG) {
                numTimesVersionClicked = 0;
                Toast.makeText(getActivity(), "Taking you to admin panel", Toast.LENGTH_SHORT).show();
                (((BaseActivity)getActivity())).doUserAction(UserAction.ADD_DATA_FRAGMENT,new Bundle());
            }
        } else if( (preference==findPreference("legal")) || (preference==findPreference("open_source_licenses")) || (preference==findPreference("terms_of_service")) || (preference==findPreference("privacy_policy")) ) {
            Toast.makeText(getActivity().getApplicationContext(), preference.getTitle().toString()+" Being written.", Toast.LENGTH_LONG).show();
        }
        return true;
    }
}
