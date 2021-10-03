
package com.example.veeez.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//@SuppressWarnings("unused")
public class CalculatePriceResponse {
    @Expose

    @SerializedName("Cost")
    private Double cost;

    @Expose

    @SerializedName("Currency")
    private String currency;

    @Expose
    @SerializedName("Distance")
    private Double distance;

    @Expose

    @SerializedName("Duration")
    private Double duration;

    @Expose

    @SerializedName("MinPrice")
    private Long minPrice;

    @Expose

    @SerializedName("Price")
    private Double price;

    @Expose

    @SerializedName("Status")
    private Long status;

    @Expose
    @SerializedName("Text")
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public Long getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Long minPrice) {
        this.minPrice = minPrice;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

}
