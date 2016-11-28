package com.styx.gta.donorblood.fragments.donorlist.view;

import com.styx.gta.donorblood.models.BloodGroup;
import com.styx.gta.donorblood.models.Donor;

/**
 * Created by amal.george on 28-11-2016.
 */

public interface DonorAdapter {
    void addItem(Donor donor);
    void request();
}
