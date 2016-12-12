package com.styx.gta.donorblood.constants;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by amal.george on 24-11-2016.
 */

public interface Constants {
    boolean enableLog = true;
    String datePattern = "yyyy-MM-dd";
    int MAX_CLICKS_TO_UNLOCK_REG = 8;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern);

    interface FragmentParameters {
        String keyObject = "keyObject";
    }

    interface RequestCodes {
        int REQUEST_CODE = 0x0000;
    }
}
