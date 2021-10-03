package com.example.veeez.feature.message;

public class MessageObject {
    private String id;
    private String body;

    public MessageObject(String id, String body) {
        this.id = id;
        this.body = body;
    }

    public String getId() {
        return id;
    }

    public String getBody() {
        return body;
    }
}
