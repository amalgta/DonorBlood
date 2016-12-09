package com.styx.gta.donorblood.models;

import com.styx.gta.donorblood.base.BaseModel;
import com.styx.gta.donorblood.constants.Constants;

import java.io.Serializable;

/**
 * Created by amal.george on 25-11-2016.
 */

public class Donor extends BaseModel implements Serializable {
    private String address, contact, dob, name, sex, bloodGroup;

    public Donor() {
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

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
