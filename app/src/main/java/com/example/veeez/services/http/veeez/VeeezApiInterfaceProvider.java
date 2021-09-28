package com.example.veeez.services.http.veeez;

public class VeeezApiInterfaceProvider {
    private static VeeezApiInterface veeezApiInterface;

    public static VeeezApiInterface getInstance() {
        if (veeezApiInterface == null){
            veeezApiInterface = VeeezApiService.getRetrofit().create(VeeezApiInterface.class);
        }
        return veeezApiInterface;
    }
}
