package com.styx.gta.donorblood.base;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by amalg on 26-11-2016.
 */

public class BaseModel {
    protected String objectID;

    public String getObjectID() {
        return objectID;
    }

    public void setObjectID(String objectID) {
        this.objectID = objectID;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("objectID", objectID);
        return result;
    }
}
