
package com.example.veeez.data;

import com.google.gson.annotations.SerializedName;

public class UserInTripResponse {

    @SerializedName("Progressing")
    private String progressing;
    @SerializedName("Status")
    private Long status;
    @SerializedName("Text")
    private String text;
    @SerializedName("WouldRate")
    private String wouldRate;

    public String getProgressing() {
        return progressing;
    }

    public void setProgressing(String progressing) {
        this.progressing = progressing;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getWouldRate() {
        return wouldRate;
    }

    public void setWouldRate(String wouldRate) {
        this.wouldRate = wouldRate;
    }

}
