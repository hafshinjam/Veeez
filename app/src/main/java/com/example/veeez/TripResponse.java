package com.example.veeez;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TripResponse {
    @Expose
    @SerializedName("Status")
    private int Status;
    @Expose
    @SerializedName("OrdersList")
    private List<TripObject> tripObjects;
    @Expose
    @SerializedName("OrdersCount")
    private int OrdersCount;

    public int getStatus() {
        return Status;
    }

    public List<TripObject> getTripObjects() {
        return tripObjects;
    }

    public int getOrdersCount() {
        return OrdersCount;
    }
}
