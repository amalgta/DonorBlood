package com.styx.gta.donorblood.fragments.donordetail;


import android.os.Bundle;

import com.styx.gta.donorblood.constants.Constants;
import com.styx.gta.donorblood.models.Donor;


public class DonorDetailPresenter implements DonorDetailContract.Presenter {
    private DonorDetailContract.View mView;

    DonorDetailPresenter(DonorDetailContract.View mView) {
        this.mView = mView;
        update();
    }

    public void update() {
        Bundle mBundle = mView.getViewArguments();
        Donor mDonor = (Donor) mBundle.getSerializable(Constants.FragmentParameters.keyObject);
        mView.bindDonorUI(mDonor);
    }
}
