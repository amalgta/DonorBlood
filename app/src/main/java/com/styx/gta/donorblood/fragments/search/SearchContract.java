package com.styx.gta.donorblood.fragments.search;

import android.widget.ArrayAdapter;

import com.styx.gta.donorblood.base.BasePresenter;

/**
 * Created by amal.george on 29-11-2016.
 */

interface SearchContract {
    interface View {
        ArrayAdapter<String> getBloodGroupAdapter();
    }

    interface Presenter extends BasePresenter {
    }
}
