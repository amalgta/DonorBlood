package com.styx.gta.donorblood.fragments.donorlist.presenter;

import android.os.Bundle;

import com.styx.gta.donorblood.fragments.donorlist.controller.DonorController;
import com.styx.gta.donorblood.fragments.donorlist.view.DonorAdapter;
import com.styx.gta.donorblood.models.BloodGroup;
import com.styx.gta.donorblood.models.Donor;

/**
 * Created by amal.george on 28-11-2016.
 */

public class DonorPresenterImpl implements DonorPresenter {
    private final DonorAdapter adapterView;
    private final DonorController controller;

    public DonorPresenterImpl(DonorAdapter adapterView) {
        this.adapterView = adapterView;
        this.controller = new DonorController(this);
    }
    public DonorPresenterImpl(DonorAdapter adapterView,BloodGroup bloodGroup) {
        this.adapterView = adapterView;
        this.controller = new DonorController(this,bloodGroup);
    }

    @Override
    public void sendToAdapter(Donor donor) {
        adapterView.addItem(donor);
    }

    @Override
    public void request() {
        controller.request();
    }
}
