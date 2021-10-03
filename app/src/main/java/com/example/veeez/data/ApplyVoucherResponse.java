
package com.example.veeez.data;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class ApplyVoucherResponse {

    @SerializedName("FinalCost")
    private Double finalCost;
    @SerializedName("FinalMainCost")
    private Double finalMainCost;
    @SerializedName("Percent")
    private Double percent;
    @SerializedName("Status")
    private Long status;
    @SerializedName("Text")
    private String text;

    public Double getFinalCost() {
        return finalCost;
    }

    public void setFinalCost(Double finalCost) {
        this.finalCost = finalCost;
    }

    public Double getFinalMainCost() {
        return finalMainCost;
    }

    public void setFinalMainCost(Double finalMainCost) {
        this.finalMainCost = finalMainCost;
    }

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
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

}
