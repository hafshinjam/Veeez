package com.example.veeez.feature;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EditUserResponse {
    @Expose
    @SerializedName("Status")
    private int status;
    @Expose
    @SerializedName("Text")
    private String text;

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
