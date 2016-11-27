package com.styx.gta.donorblood.fragments.chat.adapters;


import com.styx.gta.donorblood.models.Message;

/**
 * Created by Filip on 25/02/2016.
 */
public interface MessageAdapterView {
    void addItem(Message message);
    void request();
}
