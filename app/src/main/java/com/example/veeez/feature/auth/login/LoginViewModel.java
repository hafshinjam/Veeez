package com.example.veeez.feature.auth.login;

import com.example.veeez.data.AuthResponse;
import com.example.veeez.services.http.veeez.VeeezApiInterface;

import io.reactivex.Single;

public class LoginViewModel {

    private VeeezApiInterface apiInterface;

    public LoginViewModel(VeeezApiInterface apiInterface) {
        if (this.apiInterface == null){
            this.apiInterface = apiInterface;
        }
    }


    public Single<AuthResponse> login(String phone){
        return apiInterface.login(phone);
    }
}
