package com.styx.gta.donorblood.models;

import com.styx.gta.donorblood.base.BaseModel;
import com.styx.gta.donorblood.constants.Constants;

import java.io.Serializable;

/**
 * Created by amal.george on 25-11-2016.
 */

public class BloodGroup extends BaseModel implements Serializable {
    private String name;
    private int totalCount;
    private String themeColor;

    public BloodGroup() {
    }

    public String getName() {
        return name;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThemeColor() {
        return themeColor;
    }

    public int getApproxTotalCount() {
        int approxCount = totalCount;
        approxCount = approxCount / Constants.approximationMargin;
        approxCount *= Constants.approximationMargin;
        return approxCount;
    }
}