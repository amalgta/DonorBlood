package com.styx.gta.donorblood.fragments.search;

import android.widget.ArrayAdapter;

import com.styx.gta.donorblood.base.BasePresenter;

/**
 * Created by amal.george on 29-11-2016.
 */

interface SearchContract {
    interface View {
        ArrayAdapter<String> getBloodGroupAdapter();
        void showResult(String result);
    }

    interface Presenter extends BasePresenter {
        void searchDonor(String bloodGroupObjectID,int minAge,int maxAge,String address);
    }
}
