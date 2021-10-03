
package com.example.veeez.data;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class UserAddressResponse {

    @Expose
    @SerializedName("AddressCount")
    private Long mAddressCount;

    @Expose
    @SerializedName("Object")
    private List<Object> mObject;

    @Expose
    @SerializedName("Status")
    private Long mStatus;

    public Long getAddressCount() {
        return mAddressCount;
    }

    public void setAddressCount(Long addressCount) {
        mAddressCount = addressCount;
    }

    public List<Object> getObject() {
        return mObject;
    }

    public void setObject(List<Object> object) {
        mObject = object;
    }

    public Long getStatus() {
        return mStatus;
    }

    public void setStatus(Long status) {
        mStatus = status;
    }

}
