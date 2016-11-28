package com.styx.gta.donorblood.fragments.donorlist.presenter;

import com.styx.gta.donorblood.models.Donor;
import com.styx.gta.donorblood.models.Message;

/**
 * Created by amal.george on 28-11-2016.
 */

public interface DonorPresenter {
    void sendToAdapter(Donor donor);

    void request();
}
