package com.styx.gta.donorblood.fragments.donorlist;

import android.os.Bundle;

import com.styx.gta.donorblood.adapters.DonorBaseAdapter;
import com.styx.gta.donorblood.base.BasePresenter;

/**
 * Created by amal.george on 29-11-2016.
 */

interface DonorListContract {
    interface View {
        Bundle getViewArguments();

        DonorBaseAdapter getAdapter();
    }

    interface Presenter extends BasePresenter {
    }
}
