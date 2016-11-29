package com.styx.gta.donorblood.fragments.home;

import android.content.Context;
import android.os.Bundle;

import com.styx.gta.donorblood.base.BasePresenter;
import com.styx.gta.donorblood.models.BloodGroup;
import com.styx.gta.donorblood.models.Donor;

/**
 * Created by amal.george on 29-11-2016.
 */

interface HomeContract {
    interface BloodGroupAdapterView {
        void addItem(BloodGroup bloodGroup);
        void setContext(Context context);
    }


    interface View {
        BloodGroupAdapterView getAdapter();
    }

    interface Presenter extends BasePresenter {
    }
}
