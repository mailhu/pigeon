package com.example.demo;

public class MessageEvent {

    private String sender;
    private String content;
    private String date;

    public MessageEvent(String sender, String content, String date) {
        this.sender = sender;
        this.content = content;
        this.date = date;
    }

    public String getSender() {
        return sender;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }
}
