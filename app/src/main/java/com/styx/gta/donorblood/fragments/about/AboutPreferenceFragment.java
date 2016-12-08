package com.styx.gta.donorblood.fragments.about;

import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceGroup;
import android.preference.PreferenceScreen;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.styx.gta.donorblood.R;
import com.styx.gta.donorblood.base.BaseActivity;
import com.styx.gta.donorblood.constants.UserAction;


/**
 * Created by amal.george on 06-12-2016.
 */

public class AboutPreferenceFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener, AboutPreferenceContract.View {
    private static final int MAX_CLICKS_TO_UNLOCK_REG = 1;

    private static final String TAG = "AboutPreferenceFragment";

    private int numTimesVersionClicked;
    private AboutPreferenceContract.Presenter presenter;
    boolean isLoading = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new AboutPreferencePresenter(this);
        addPreferencesFromResource(R.xml.pref_about);
        Preference prefVersion = findPreference("version");

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

    @Override
    public boolean onPreferenceClick(Preference preference) {
        if (preference == findPreference("copyright")) {
            if (++numTimesVersionClicked == MAX_CLICKS_TO_UNLOCK_REG && !isLoading) {
                numTimesVersionClicked = 0;
                showDialogue(getActivity());
            }
        } else if ((preference == findPreference("legal")) || (preference == findPreference("open_source_licenses")) || (preference == findPreference("terms_of_service")) || (preference == findPreference("privacy_policy"))) {
            Toast.makeText(getActivity().getApplicationContext(), preference.getTitle().toString() + " Being written.", Toast.LENGTH_LONG).show();
        }
        return true;
    }

    void showDialogue(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Enter Access Code");

        final View dialogueView = LayoutInflater.from(context).inflate(R.layout.dialog_single_input, null);
        builder.setView(dialogueView);
        builder.setPositiveButton("ACCESS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                isLoading = true;
                presenter.doValidateUser(((EditText) (dialogueView.findViewById(R.id.et_text))).getText().toString());
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                getActivity().onBackPressed();
            }
        });
        builder.setCancelable(false);
        builder.show();
    }

    @Override
    public void validationResult(boolean authenticated) {
        isLoading = false;
        Toast.makeText(getActivity(), ((authenticated) ? "Tr8e" : "False"), Toast.LENGTH_SHORT).show();
        if (authenticated)
            (((BaseActivity) getActivity())).doUserAction(UserAction.ADD_DATA_FRAGMENT, new Bundle());
        //Toast.makeText(getActivity(), "Taking you to admin panel", Toast.LENGTH_SHORT).show();

    }
}
