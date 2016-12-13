package com.styx.gta.donorblood.fragments.search;


import android.os.Bundle;

import com.styx.gta.donorblood.constants.Constants;
import com.styx.gta.donorblood.models.Donor;


class SearchPresenter implements SearchContract.Presenter {
    private SearchContract.View mView;

    SearchPresenter(SearchContract.View mView) {
        this.mView = mView;
    }

    public void request() {
        Bundle mBundle = mView.getViewArguments();
        Donor mDonor = (Donor) mBundle.getSerializable(Constants.FragmentParameters.keyObject);
        mView.bindDonorUI(mView.getRootView(),mDonor);
    }
}
