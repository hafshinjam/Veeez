
package com.example.veeez.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings("unused")
public class ReverseResponse {

    @Expose
    @SerializedName("address")
    private Address mAddress;

    @Expose
    @SerializedName("boundingbox")
    private List<String> mBoundingbox;

    @Expose
    @SerializedName("display_name")
    private String mDisplayName;

    @Expose
    @SerializedName("lat")
    private String mLat;

    @Expose
    @SerializedName("licence")
    private String mLicence;

    @Expose
    @SerializedName("lon")
    private String mLon;

    @Expose
    @SerializedName("osm_id")
    private Long mOsmId;

    @Expose
    @SerializedName("osm_type")
    private String mOsmType;

    @Expose
    @SerializedName("place_id")
    private Long mPlaceId;

    public Address getAddress() {
        return mAddress;
    }

    public void setAddress(Address address) {
        mAddress = address;
    }

    public List<String> getBoundingbox() {
        return mBoundingbox;
    }

    public void setBoundingbox(List<String> boundingbox) {
        mBoundingbox = boundingbox;
    }

    public String getDisplayName() {
        return mDisplayName;
    }

    public void setDisplayName(String displayName) {
        mDisplayName = displayName;
    }

    public String getLat() {
        return mLat;
    }

    public void setLat(String lat) {
        mLat = lat;
    }

    public String getLicence() {
        return mLicence;
    }

    public void setLicence(String licence) {
        mLicence = licence;
    }

    public String getLon() {
        return mLon;
    }

    public void setLon(String lon) {
        mLon = lon;
    }

    public Long getOsmId() {
        return mOsmId;
    }

    public void setOsmId(Long osmId) {
        mOsmId = osmId;
    }

    public String getOsmType() {
        return mOsmType;
    }

    public void setOsmType(String osmType) {
        mOsmType = osmType;
    }

    public Long getPlaceId() {
        return mPlaceId;
    }

    public void setPlaceId(Long placeId) {
        mPlaceId = placeId;
    }

}
