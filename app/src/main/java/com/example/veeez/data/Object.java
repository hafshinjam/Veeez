
package com.example.veeez.data;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Object {

    @SerializedName("AddressId")
    private Long mAddressId;
    @SerializedName("FullAddress")
    private String mFullAddress;
    @SerializedName("Lat")
    private String mLat;
    @SerializedName("Long")
    private String mLong;
    @SerializedName("MapImage")
    private String mMapImage;
    @SerializedName("Plaque")
    private String mPlaque;
    @SerializedName("Title")
    private java.lang.Object mTitle;
    @SerializedName("Unit")
    private Long mUnit;

    public Long getAddressId() {
        return mAddressId;
    }

    public void setAddressId(Long addressId) {
        mAddressId = addressId;
    }

    public String getFullAddress() {
        return mFullAddress;
    }

    public void setFullAddress(String fullAddress) {
        mFullAddress = fullAddress;
    }

    public String getLat() {
        return mLat;
    }

    public void setLat(String lat) {
        mLat = lat;
    }

    public String getLong() {
        return mLong;
    }

    public void setLong(String mlong) {
        mLong = mlong;
    }

    public String getMapImage() {
        return mMapImage;
    }

    public void setMapImage(String mapImage) {
        mMapImage = mapImage;
    }

    public String getPlaque() {
        return mPlaque;
    }

    public void setPlaque(String plaque) {
        mPlaque = plaque;
    }

    public java.lang.Object getTitle() {
        return mTitle;
    }

    public void setTitle(java.lang.Object title) {
        mTitle = title;
    }

    public Long getUnit() {
        return mUnit;
    }

    public void setUnit(Long unit) {
        mUnit = unit;
    }

}
