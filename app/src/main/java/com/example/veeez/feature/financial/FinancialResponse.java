package com.example.veeez.feature.financial;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FinancialResponse {

    @SerializedName("Status")
    private int status;

    @SerializedName("PaymentCount")
    private int paymentCount;

    @SerializedName("Object")
    private List<FinancialItem> items;

    public void setStatus(int status){
        this.status = status;
    }

    public int getStatus(){
        return status;
    }

    public void setPaymentCount(int paymentCount){
        this.paymentCount = paymentCount;
    }

    public int getPaymentCount(){
        return paymentCount;
    }

    public void setItems(List<FinancialItem> items){
        this.items = items;
    }

    public List<FinancialItem> getItems(){
        return items;
    }
}
