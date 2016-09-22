package com.weddingsingers.wsapp.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Tacademy on 2016-09-13.
 */

public class ChatMessage {

    private User sender;

    private String message;

    private String date;

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
