package com.styx.gta.donorblood.fragments.adddata;


import com.styx.gta.donorblood.base.BasePresenter;
import com.styx.gta.donorblood.models.Donor;

/**
 * This specifies the contract between the view and the presenter.
 */
interface AddDataContract {

    interface View {
        void addGroupItem(String value, String name);
        void bindDonorUI(android.view.View view, Donor donor);
    }

    interface Presenter extends BasePresenter {
        void requestBloodGroups();
        void saveDonor(Donor donor);
    }
}