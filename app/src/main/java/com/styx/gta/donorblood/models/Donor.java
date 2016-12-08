package com.styx.gta.donorblood.models;

import com.styx.gta.donorblood.base.BaseModel;
import com.styx.gta.donorblood.constants.Constants;

import java.io.Serializable;

/**
 * Created by amal.george on 25-11-2016.
 */

public class Donor extends BaseModel implements Serializable {
    private String address, bloodGroupCanonicalName, contact, dob, name, sex, bloodGroup;

    public Donor() {
    }

    public Donor(String address, String bloodGroupCanonicalName, String contact, String dob, String name, String sex, String bloodGroup) {
        this.address = address;
        this.bloodGroupCanonicalName = bloodGroupCanonicalName;
        this.contact = contact;
        this.dob = dob;
        this.name = name;
        this.sex = sex;
        this.bloodGroup = bloodGroup;
    }

    public interface Sex {
        String male = "Male";
        String female = "Female";
    }

    public String getAddress() {
        return address;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public String getBloodGroupCanonicalName() {
        return bloodGroupCanonicalName;
    }

    public String getContact() {
        return contact;
    }

    public String getDob() {
        return dob;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }
}
