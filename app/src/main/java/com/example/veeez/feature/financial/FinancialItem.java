package com.example.veeez.feature.financial;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FinancialItem {
    @Expose
    @SerializedName("Description")
    private String description;

    @Expose
    @SerializedName("Amount")
    private int amount;

    @Expose
    @SerializedName("PaymentId")
    private int paymentId;

    @Expose
    @SerializedName("Title")
    private String title;

    @Expose
    @SerializedName("DateTime")
    private String dateTime;

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getDateTime() {
        return dateTime;
    }
}
