package com.styx.gta.donorblood.models;

import com.google.firebase.database.Exclude;
import com.styx.gta.donorblood.base.BaseModel;
import com.styx.gta.donorblood.constants.Constants;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by amal.george on 25-11-2016.
 */

public class BloodGroup extends BaseModel implements Serializable {
    private String name;
    private String themeColor;
    private int count = 0;

    public BloodGroup() {
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getThemeColor() {
        return themeColor;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setThemeColor(String themeColor) {
        this.themeColor = themeColor;
    }

}