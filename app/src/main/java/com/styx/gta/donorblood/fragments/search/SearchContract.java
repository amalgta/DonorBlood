package com.styx.gta.donorblood.fragments.search;

import com.styx.gta.donorblood.adapters.BloodGroupAdapter;
import com.styx.gta.donorblood.base.BasePresenter;

import java.util.ArrayList;

/**
 * Created by amal.george on 29-11-2016.
 */

interface SearchContract {
    interface View {
        void getUserList(ArrayList<String> list);
    }

    interface Presenter extends BasePresenter {
        void requestUserList(String mName);
    }
}
