package com.styx.gta.donorblood.fragments.chat.interactor;

import com.google.firebase.database.DatabaseReference;
import com.styx.gta.donorblood.fragments.chat.util.Util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Filip on 25/02/2016.
 */
//pushes a message to Firebase on every onClick method usage
public class ChatMessageInteractor implements CMessageInteractor {

    @Override
    public void pushMessageToFirebase(String author, String message, String emoji) {
        DatabaseReference messageRef = Util.getDB("Data/Messages");
        messageRef.push().setValue(createMessage(message, author, emoji));
    }

    @Override
    public Map<String, Object> createMessage(String message, String author, String emoji) {
        Map<String, Object> values = new HashMap<>();
        values.put("message", message);
        values.put("author", author);
        values.put("emoji", emoji);
        return values;
    }
}
