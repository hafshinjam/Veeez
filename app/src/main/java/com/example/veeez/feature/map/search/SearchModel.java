
package com.example.veeez.feature.map.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchModel {

    @SerializedName("boundingbox")
    @Expose
    private List<String> mBoundingbox;

    @Expose
    @SerializedName("class")
    private String mClass;

    @Expose
    @SerializedName("display_name")
    private String mDisplayName;

    @Expose
    @SerializedName("icon")
    private String mIcon;

    @Expose
    @SerializedName("importance")
    private Double mImportance;

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

    @Expose
    @SerializedName("type")
    private String mType;


    public List<String> getBoundingbox() {
        return mBoundingbox;
    }

    public void setBoundingbox(List<String> boundingbox) {
        mBoundingbox = boundingbox;
    }

    public String getClassM() {
        return mClass;
    }

    public void setClass(String classM) {
        mClass = classM;
    }

    public String getDisplayName() {
        return mDisplayName;
    }

    public void setDisplayName(String displayName) {
        mDisplayName = displayName;
    }

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }

    public Double getImportance() {
        return mImportance;
    }

    public void setImportance(Double importance) {
        mImportance = importance;
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

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }


}
