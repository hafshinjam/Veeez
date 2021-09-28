package com.example.veeez.Activities.auth.register;

import com.example.veeez.data.AuthResponse;
import com.example.veeez.services.http.veeez.VeeezApiInterface;
import com.google.gson.JsonObject;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class SignUpViewModel {

    private VeeezApiInterface apiInterface;

    public SignUpViewModel(VeeezApiInterface apiInterface) {
        if (this.apiInterface == null){
            this.apiInterface = apiInterface;
        }
    }


    public Single<AuthResponse> register(String firstName, String lastName,String phoneNumber,String introCode){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("FirstName",firstName);
        jsonObject.addProperty("LastName",lastName);
        jsonObject.addProperty("PhoneNumber",phoneNumber);
        jsonObject.addProperty("IntroCode",introCode);

        return apiInterface.signUp(jsonObject);
    }
}
