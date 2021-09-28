
package com.example.veeez.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuthResponse {

    @Expose
    @SerializedName("Code")
    private String mCode;

    @Expose
    @SerializedName("Status")
    private Long mStatus;

    @Expose
    @SerializedName("Text")
    private String mText;

    @Expose
    @SerializedName("UserId")
    private String mUserId;

    public String getCode() {
        return mCode;
    }

    public void setCode(String code) {
        mCode = code;
    }

    public Long getStatus() {
        return mStatus;
    }

    public void setStatus(Long status) {
        mStatus = status;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    public String getUserId() {
        return mUserId;
    }

    public void setUserId(String userId) {
        mUserId = userId;
    }

}
