package com.styx.gta.donorblood.fragments.donorlist;

import android.os.Bundle;

import com.styx.gta.donorblood.adapters.DonorAdapter;
import com.styx.gta.donorblood.base.BasePresenter;
import com.styx.gta.donorblood.models.BloodGroup;

/**
 * Created by amal.george on 29-11-2016.
 */

interface DonorListContract {
    interface View {
        BloodGroup getThisGroup();

        DonorAdapter getAdapter();
    }

    interface Presenter extends BasePresenter {
    }
}
