package com.example.exam;

import java.util.Date;

public class Message {
    public String userName;
    public String textMessage;
    private long messageTime;
    public Message(){}
    public Message(String userName, String textMessage){
        this.userName = userName;
        this.textMessage = textMessage;
        this.messageTime = new Date().getTime();
    }

    public String getUserName() {
        return userName;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public long getMessageTime() {
        return messageTime;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    public void setMessageTime(long messageTime) {
        this.messageTime = messageTime;
    }
}
