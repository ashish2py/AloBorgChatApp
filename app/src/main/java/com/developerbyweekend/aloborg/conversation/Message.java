package com.developerbyweekend.aloborg.conversation;

/**
 * Created by Ashish on 13/01/17.
 */

public class Message {
    private String fromName;
    private String message;
    private boolean isSelf;

    public Message() {
    }

    public Message(String fromName, String message) {
        this.fromName = fromName;
        this.message = message;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSelf() {
        return isSelf;
    }

    public void setSelf(boolean isSelf) {
        this.isSelf = isSelf;
    }

}
