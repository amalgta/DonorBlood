package com.styx.gta.donorblood.fragments.donorlist;

import android.content.Context;
import android.os.Bundle;

import com.styx.gta.donorblood.base.BasePresenter;
import com.styx.gta.donorblood.models.Donor;

/**
 * Created by amal.george on 29-11-2016.
 */

interface DonorListContract {
    interface DonorAdapterView {
        void addItem(Donor donor);

        void setContext(Context context);
    }


    interface View {
        Bundle getViewArguments();

        DonorAdapterView getAdapter();
    }

    interface Presenter extends BasePresenter {
    }
}
