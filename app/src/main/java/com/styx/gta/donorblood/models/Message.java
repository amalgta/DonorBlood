package com.styx.gta.donorblood.models;

import com.styx.gta.donorblood.base.BaseModel;
import com.styx.gta.donorblood.constants.Constants;

import java.io.Serializable;

/**
 * Created by amal.george on 25-11-2016.
 */

public class Message extends BaseModel implements Serializable {
    /**
     * values.put("message", message);
     * values.put("author", author);
     * values.put("emoji", emoji);
     */
    private String message, author, emoji;

    public Message() {
    }

    public String getAuthor() {
        return author;
    }

    public String getEmoji() {
        return emoji;
    }

    public String getMessage() {
        return message;
    }
}
