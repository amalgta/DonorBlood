package com.styx.gta.donorblood.interfaces;

import android.os.Bundle;

import com.styx.gta.donorblood.constants.Constants;
import com.styx.gta.donorblood.constants.UserAction;

/**
 * Created by amal.george on 24-11-2016.
 */

public interface UserActionListener {
    void doUserAction(UserAction mUserAction,Bundle mBundle);
}
