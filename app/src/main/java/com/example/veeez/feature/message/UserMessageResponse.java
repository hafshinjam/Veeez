package com.example.veeez.feature.message;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserMessageResponse {
    @Expose
    @SerializedName("Status")
    private int status;
    @Expose
    @SerializedName("NotificationCount")
    private int notificationCount;
    @Expose
    @SerializedName("Object")
    private List<MessageObject> messages;

    public int getStatus() {
        return status;
    }

    public int getNotificationCount() {
        return notificationCount;
    }

    public List<MessageObject> getMessages() {
        return messages;
    }
}
