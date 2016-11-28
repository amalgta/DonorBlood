package com.styx.gta.donorblood.fragments.chat.view;


import com.styx.gta.donorblood.models.Message;

/**
 * Created by Filip on 25/02/2016.
 */
public interface MessageAdapter {
    void addItem(Message message);
    void request();
}
