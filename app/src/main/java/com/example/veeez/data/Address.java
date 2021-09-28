
package com.example.veeez.data;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Address {

    @SerializedName("borough")
    private String mBorough;
    @SerializedName("city")
    private String mCity;
    @SerializedName("country")
    private String mCountry;
    @SerializedName("country_code")
    private String mCountryCode;
    @SerializedName("county")
    private String mCounty;
    @SerializedName("district")
    private String mDistrict;
    @SerializedName("neighbourhood")
    private String mNeighbourhood;
    @SerializedName("postcode")
    private String mPostcode;
    @SerializedName("province")
    private String mProvince;
    @SerializedName("road")
    private String mRoad;
    @SerializedName("shop")
    private String mShop;

    public String getBorough() {
        return mBorough;
    }

    public void setBorough(String borough) {
        mBorough = borough;
    }

    public String getCity() {
        return mCity;
    }

    public void setCity(String city) {
        mCity = city;
    }

    public String getCountry() {
        return mCountry;
    }

    public void setCountry(String country) {
        mCountry = country;
    }

    public String getCountryCode() {
        return mCountryCode;
    }

    public void setCountryCode(String countryCode) {
        mCountryCode = countryCode;
    }

    public String getCounty() {
        return mCounty;
    }

    public void setCounty(String county) {
        mCounty = county;
    }

    public String getDistrict() {
        return mDistrict;
    }

    public void setDistrict(String district) {
        mDistrict = district;
    }

    public String getNeighbourhood() {
        return mNeighbourhood;
    }

    public void setNeighbourhood(String neighbourhood) {
        mNeighbourhood = neighbourhood;
    }

    public String getPostcode() {
        return mPostcode;
    }

    public void setPostcode(String postcode) {
        mPostcode = postcode;
    }

    public String getProvince() {
        return mProvince;
    }

    public void setProvince(String province) {
        mProvince = province;
    }

    public String getRoad() {
        return mRoad;
    }

    public void setRoad(String road) {
        mRoad = road;
    }

    public String getShop() {
        return mShop;
    }

    public void setShop(String shop) {
        mShop = shop;
    }

}
