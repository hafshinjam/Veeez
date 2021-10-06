package com.example.veeez.feature;

import com.example.veeez.services.http.veeez.VeeezApiInterface;

import io.reactivex.Single;

public class OrderViewModel {
    private VeeezApiInterface apiInterface;

    public OrderViewModel(VeeezApiInterface apiInterface) {
        this.apiInterface = apiInterface;
    }

    public Single<OrderHistoryResponse> getOderResponse(String userId) {
        return apiInterface.getTripList(userId);
    }
}
