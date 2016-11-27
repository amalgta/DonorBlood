package com.styx.gta.donorblood.base;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.styx.gta.donorblood.R;
import com.styx.gta.donorblood.constants.Constants;
import com.styx.gta.donorblood.constants.UserAction;
import com.styx.gta.donorblood.fragments.AboutFragment;
import com.styx.gta.donorblood.fragments.DonorDetailFragment;
import com.styx.gta.donorblood.fragments.DonorListFragment;
import com.styx.gta.donorblood.fragments.HomeFragment;
import com.styx.gta.donorblood.fragments.SearchFragment;
import com.styx.gta.donorblood.fragments.chat.view.ChatFragment;
import com.styx.gta.donorblood.interfaces.UserActionListener;
import com.styx.gta.donorblood.utilities.Logger;

/**
 * Created by amal.george on 25-11-2016.
 */

public class BaseActivity extends AppCompatActivity implements UserActionListener {
    public static final String TAG = "BaseActivity";

    public void call(String number) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + number));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            String[] mPermission = {Manifest.permission.CALL_PHONE};
            //TODO RequestResponse Implementation
            ActivityCompat.requestPermissions(this, mPermission, Constants.RequestCodes.REQUEST_CODE);
            return;
        }
        startActivity(callIntent);
    }


    protected boolean isFragmentExistsInBackStack(String tag) {
        if (getSupportFragmentManager().findFragmentByTag(tag) != null)
            return true;
        else
            return false;
    }

    public void addFragment(final int mContainer, final Fragment mFragment,
                            final String mTag) {
        final FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        fragmentTransaction.replace(mContainer, mFragment, mTag);
        fragmentTransaction.addToBackStack(mTag);
        fragmentTransaction.commit();
    }

    public void popBackStack(String tag, int flag) {
        this.getSupportFragmentManager().popBackStack(tag, flag);
    }

    public BaseFragment getTopFragment() {
        return ((BaseFragment) this.getSupportFragmentManager().findFragmentById(R.id.fl_content));
    }

    @Override
    public void doUserAction(UserAction mUserAction, Bundle mBundle) {
        int mLayout = R.id.fl_content;
        Fragment mFragment;
        try {
            switch (mUserAction) {
                case HOME:
                    if (isFragmentExistsInBackStack(HomeFragment.TAG)) {
                        if (getTopFragment() instanceof HomeFragment)
                            return;
                        popBackStack(HomeFragment.TAG, 0);
                    } else {
                        mFragment = new HomeFragment();
                        mFragment.setArguments(mBundle);
                        addFragment(mLayout, mFragment, HomeFragment.TAG);
                    }
                    break;
                case DONOR_LIST_FRAGMENT:
                    if (isFragmentExistsInBackStack(DonorListFragment.TAG)) {
                        if (getTopFragment() instanceof DonorListFragment)
                            return;
                        popBackStack(DonorListFragment.TAG, 0);
                    } else {
                        mFragment = new DonorListFragment();
                        mFragment.setArguments(mBundle);
                        addFragment(mLayout, mFragment, DonorListFragment.TAG);
                    }
                    break;
                case DONOR_DETAIL_FRAGMENT:
                    if (isFragmentExistsInBackStack(DonorDetailFragment.TAG)) {
                        if (getTopFragment() instanceof DonorDetailFragment)
                            return;
                        popBackStack(DonorDetailFragment.TAG, 0);
                    } else {
                        mFragment = new DonorDetailFragment();
                        mFragment.setArguments(mBundle);
                        addFragment(mLayout, mFragment, DonorDetailFragment.TAG);
                    }
                    break;
                case SEARCH_FRAGMENT:
                    if (isFragmentExistsInBackStack(SearchFragment.TAG)) {
                        if (getTopFragment() instanceof SearchFragment)
                            return;
                        popBackStack(SearchFragment.TAG, 0);
                    } else {
                        mFragment = new SearchFragment();
                        mFragment.setArguments(mBundle);
                        addFragment(mLayout, mFragment, SearchFragment.TAG);
                    }
                    break;
                case ABOUT_FRAGMENT:
                    if (isFragmentExistsInBackStack(AboutFragment.TAG)) {
                        if (getTopFragment() instanceof AboutFragment)
                            return;
                        popBackStack(AboutFragment.TAG, 0);
                    } else {
                        mFragment = new AboutFragment();
                        mFragment.setArguments(mBundle);
                        addFragment(mLayout, mFragment, AboutFragment.TAG);
                    }
                    break;
                case TEST_FRAGMENT:
                    if (isFragmentExistsInBackStack(ChatFragment.TAG)) {
                        if (getTopFragment() instanceof ChatFragment)
                            return;
                        popBackStack(ChatFragment.TAG, 0);
                    } else {
                        mFragment = new ChatFragment();
                        mFragment.setArguments(mBundle);
                        addFragment(mLayout, mFragment, ChatFragment.TAG);
                    }
                    break;

            }
        } catch (NullPointerException mNullPointerException) {
            Logger.e(TAG, getString(R.string.debug_null));
        }
    }
}