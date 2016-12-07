package com.styx.gta.donorblood.fragments.about;

import com.styx.gta.donorblood.base.BasePresenter;

/**
 * Created by amal.george on 07-12-2016.
 */

interface AboutPreferenceContract {
    interface View {
        void validationResult(boolean authenticated);
    }

    interface Presenter extends BasePresenter {
        void doValidateUser(String adminLaunchCode);
    }
}
