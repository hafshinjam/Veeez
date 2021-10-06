package com.example.veeez.feature;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderHistoryResponse {
    @Expose
    @SerializedName("Status")
    private int Status;
    @Expose
    @SerializedName("OrdersList")
    private List<OrderHistoryObject> OrdersList;
    @Expose
    @SerializedName("OrdersCount")
    private int OrdersCount;
    @Expose
    @SerializedName("Text")
    private String Text ;

    public int getStatus() {
        return Status;
    }

    public List<OrderHistoryObject> getOrdersList() {
        return OrdersList;
    }

    public int getOrdersCount() {
        return OrdersCount;
    }

    public String getText() {
        return Text;
    }
}
