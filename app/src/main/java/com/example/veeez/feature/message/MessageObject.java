package com.example.veeez.feature.message;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MessageObject {
    @Expose
    @SerializedName("Text")
    private String text;
    @Expose
    @SerializedName("Id")
    private int id;
    @Expose
    @SerializedName("DateTime")
    private String dateTime;

    public String getText() {
        return text;
    }

    public int getId() {
        return id;
    }

    public String getDateTime() {
        return dateTime;
    }
}
