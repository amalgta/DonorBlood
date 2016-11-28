package com.styx.gta.donorblood.fragments.chat.presenter;

import com.styx.gta.donorblood.fragments.chat.view.MessageAdapter;
import com.styx.gta.donorblood.fragments.chat.controller.MessageController;
import com.styx.gta.donorblood.models.Message;

/**
 * Created by Filip on 25/02/2016.
 */
public class MessagePresenterImpl implements MessagePresenter {
    private final MessageAdapter adapterView;
    private final MessageController controller;

    public MessagePresenterImpl(MessageAdapter view) {
        this.adapterView = view;
        this.controller = new MessageController(this);
    }

    @Override
    public void sendMessageToAdapter(Message message) {
        adapterView.addItem(message);
    }

    @Override
    public void requestMessages() {
        controller.request();
    }
}
