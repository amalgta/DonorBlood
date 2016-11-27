package com.styx.gta.donorblood.fragments.chat.presenter;

/**
 * Created by Filip on 25/02/2016.
 */
public interface FirebaseChatMessagePresenter {
    void sendMessage(String author, String message, String emoji);
}
