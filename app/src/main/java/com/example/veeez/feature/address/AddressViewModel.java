package com.example.veeez.feature.address;

import com.example.veeez.data.UserAddressResponse;
import com.example.veeez.services.http.veeez.VeeezApiInterface;

import io.reactivex.Single;

public class AddressViewModel {

    private VeeezApiInterface apiInterface;

    public AddressViewModel(VeeezApiInterface apiInterface) {
        this.apiInterface = apiInterface;
    }

    public Single<UserAddressResponse> getAddress(String userId){
        return apiInterface.getUserAddressList(userId);
    }
}
