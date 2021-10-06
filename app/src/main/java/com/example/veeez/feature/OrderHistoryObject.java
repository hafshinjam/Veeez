package com.example.veeez.feature;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderHistoryObject {
    @Expose
    @SerializedName("Id")
    private int Id;
    @Expose
    @SerializedName("OrderDateTime")
    private String OrderDateTime;
    @Expose
    @SerializedName("Status")
    private String Status;
    @Expose
    @SerializedName("Price")
    private int Price;
    @Expose
    @SerializedName("Origin")
    private String Origin;
    @Expose
    @SerializedName("Destination")
    private String Destination;
    @Expose
    @SerializedName("ImagePath")
    private String ImagePath;

    public int getId() {
        return Id;
    }

    public String getOrderDateTime() {
        return OrderDateTime;
    }

    public String getStatus() {
        return Status;
    }

    public int getPrice() {
        return Price;
    }

    public String getOrigin() {
        return Origin;
    }

    public String getDestination() {
        return Destination;
    }

    public String getImagePath() {
        return ImagePath;
    }
}
