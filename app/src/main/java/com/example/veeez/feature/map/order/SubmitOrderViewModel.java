package com.example.veeez.feature.map.order;

import com.example.veeez.data.ApplyVoucherResponse;
import com.example.veeez.data.CalculatePriceResponse;
import com.example.veeez.services.http.veeez.VeeezApiInterface;
import com.example.veeez.services.http.veeez.VeeezApiService;
import com.google.gson.JsonObject;

import io.reactivex.Single;

public class SubmitOrderViewModel {

    private VeeezApiInterface apiInterface;

    public SubmitOrderViewModel(VeeezApiInterface apiInterface) {
        if (this.apiInterface == null){
            this.apiInterface = apiInterface;
        }
    }

    public Single<ApplyVoucherResponse> setVoucher(String code, String userId,int cost, int price){
        return apiInterface.setVoucher(code,userId,cost,price);
    }

    public Single<CalculatePriceResponse> getPrice(Double originLat, Double originLon, Double destinationLat, Double destinationLon, String location, int type) {

        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("OriginLatitude",originLat);
        jsonObject.addProperty("OriginLongitude",originLon);
        jsonObject.addProperty("TargetLatitude",destinationLat);
        jsonObject.addProperty("TargetLongitude",destinationLon);
        jsonObject.addProperty("Location",location);
        jsonObject.addProperty("Type",type);

        return apiInterface.getPrice(jsonObject);
    }
}
