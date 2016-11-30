package com.styx.gta.donorblood.fragments.search;

import java.util.ArrayList;

/**
 * Created by amal.george on 28-11-2016.
 */

class SearchPresenter implements SearchContract.Presenter {
    private SearchContract.View mView;
    private final String TAG = "DonorListPresenter";
    private final String dbFile = "Data/Donor";
    private final String fieldParameter = "bloodGroup";

    SearchPresenter(SearchContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void request() {
    }

    @Override
    public void requestUserList(String mName) {
        ArrayList<String> string = new ArrayList<>();
        string.add("Amal Thomas");
        string.add("Risal K N");
        string.add("Anson Thomas");
        string.add("Saly Thomas");
        string.add("Abhinav Anil Kumar");
        string.add("Tessa Antony Joseph");
        mView.getUserList(string);
    }
}
