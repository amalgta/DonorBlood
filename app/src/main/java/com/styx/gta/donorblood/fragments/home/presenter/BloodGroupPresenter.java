package com.styx.gta.donorblood.fragments.home.presenter;

import com.styx.gta.donorblood.models.BloodGroup;
import com.styx.gta.donorblood.models.Donor;

/**
 * Created by amal.george on 28-11-2016.
 */

public interface BloodGroupPresenter {
    void sendToAdapter(BloodGroup bloodGroup);

    void request();
}
