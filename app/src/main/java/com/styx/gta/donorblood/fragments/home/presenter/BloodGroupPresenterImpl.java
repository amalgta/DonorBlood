package com.styx.gta.donorblood.fragments.home.presenter;

import com.styx.gta.donorblood.fragments.donorlist.controller.DonorController;
import com.styx.gta.donorblood.fragments.donorlist.presenter.DonorPresenter;
import com.styx.gta.donorblood.fragments.donorlist.view.DonorAdapter;
import com.styx.gta.donorblood.fragments.home.controller.BloodGroupController;
import com.styx.gta.donorblood.fragments.home.view.BloodGroupAdapter;
import com.styx.gta.donorblood.models.BloodGroup;
import com.styx.gta.donorblood.models.Donor;

/**
 * Created by amal.george on 28-11-2016.
 */

public class BloodGroupPresenterImpl implements BloodGroupPresenter {
    private final BloodGroupAdapter adapterView;
    private final BloodGroupController controller;

    public BloodGroupPresenterImpl(BloodGroupAdapter adapterView) {
        this.adapterView = adapterView;
        this.controller = new BloodGroupController(this);
    }

    @Override
    public void sendToAdapter(BloodGroup bloodGroup) {
        adapterView.addItem(bloodGroup);
    }

    @Override
    public void request() {
        controller.request();
    }
}
