package com.example.veeez;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TripObject {
    @Expose
    @SerializedName("Id")
    private int Id;
    @Expose
    @SerializedName("DateTime")
    private String DateTime;
    @Expose
    @SerializedName("Status")
    private int Status;
    @Expose
    @SerializedName("Price")
    private int Price;
    @Expose
    @SerializedName("DriverId")
    private String DriverId;
    @Expose
    @SerializedName("DriverePhone")
    private String DriverePhone;
    @Expose
    @SerializedName("DriverInformation")
    private String DriverInformation;
    @Expose
    @SerializedName("DriverImage")
    private String DriverImage;
    @Expose
    @SerializedName("UserId")
    private String UserId;
    @Expose
    @SerializedName("CarModel")
    private String CarModel;
    @Expose
    @SerializedName("IdentifyTripDriverDateTime")
    private String IdentifyTripDriverDateTime;
    @Expose
    @SerializedName("PaymentType")
    private String PaymentType;
    @Expose
    @SerializedName("OriginAddress")
    private String OriginAddress;
    @Expose
    @SerializedName("Killometers")
    private String Killometers;
    @Expose
    @SerializedName("OriginLat")
    private String OriginLat;
    @Expose
    @SerializedName("OriginLng")
    private String OriginLng;
    @Expose
    @SerializedName("DestinationLat")
    private String DestinationLat;
    @Expose
    @SerializedName("DestinationLng")
    private String DestinationLng;

    public int getId() {
        return Id;
    }

    public String getDateTime() {
        return DateTime;
    }

    public int getStatus() {
        return Status;
    }

    public int getPrice() {
        return Price;
    }

    public String getDriverId() {
        return DriverId;
    }

    public String getDriverePhone() {
        return DriverePhone;
    }

    public String getDriverInformation() {
        return DriverInformation;
    }

    public String getDriverImage() {
        return DriverImage;
    }

    public String getUserId() {
        return UserId;
    }

    public String getCarModel() {
        return CarModel;
    }

    public String getIdentifyTripDriverDateTime() {
        return IdentifyTripDriverDateTime;
    }

    public String getPaymentType() {
        return PaymentType;
    }

    public String getOriginAddress() {
        return OriginAddress;
    }

    public String getKillometers() {
        return Killometers;
    }

    public String getOriginLat() {
        return OriginLat;
    }

    public String getOriginLng() {
        return OriginLng;
    }

    public String getDestinationLat() {
        return DestinationLat;
    }

    public String getDestinationLng() {
        return DestinationLng;
    }
}
