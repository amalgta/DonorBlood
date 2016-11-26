package com.styx.gta.donorblood.constants;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by amal.george on 24-11-2016.
 */

public interface Constants {
    boolean enableLog=true;
    int  approximationMargin = 1;
    String datePattern="yyyy-MM-dd";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern);
    interface FragmentParameters{
        String objectID="objectID";
    }
    interface RequestCodes{
        int REQUEST_CODE=0x0000;
    }
}
