package com.styx.gta.donorblood.fragments.home;

import com.styx.gta.donorblood.adapters.BloodGroupAdapter;
import com.styx.gta.donorblood.base.BasePresenter;

/**
 * Created by amal.george on 29-11-2016.
 */

interface HomeContract {
    interface View {
        BloodGroupAdapter getAdapter();
        void setTotalUserCount(long totalUserCount);
    }

    interface Presenter extends BasePresenter {
        void requestTotalUserCount();
    }
}
