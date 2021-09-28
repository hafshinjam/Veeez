package com.example.veeez.Activities.verification;

import com.example.veeez.data.AuthResponse;
import com.example.veeez.services.http.veeez.VeeezApiInterface;

import io.reactivex.Single;

public class ReceiveCodeViewModel {

    private VeeezApiInterface apiInterface;

    public ReceiveCodeViewModel(VeeezApiInterface apiInterface) {
        if (this.apiInterface == null) {
            this.apiInterface = apiInterface;
        }
    }

    public Single<AuthResponse> submitVerification(String phone, String code){
        return apiInterface.userVerification(phone,code);
    }


    public Single<AuthResponse> resendCode(String phone) {
        return apiInterface.resendVerification(phone);
    }
}
