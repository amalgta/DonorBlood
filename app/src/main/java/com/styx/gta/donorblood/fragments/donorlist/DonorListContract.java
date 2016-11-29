package com.styx.gta.donorblood.fragments.donorlist;

import android.os.Bundle;

import com.styx.gta.donorblood.adapters.DonorAdapter;
import com.styx.gta.donorblood.base.BasePresenter;

/**
 * Created by amal.george on 29-11-2016.
 */

interface DonorListContract {
    interface View {
        Bundle getViewArguments();

        DonorAdapter getAdapter();
    }

    interface Presenter extends BasePresenter {
    }
}
