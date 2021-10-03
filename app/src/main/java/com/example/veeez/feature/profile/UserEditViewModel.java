package com.example.veeez.feature.profile;

import com.example.veeez.services.http.veeez.VeeezApiInterface;
import com.google.gson.JsonObject;

import io.reactivex.Single;

public class UserEditViewModel {
    private VeeezApiInterface apiInterface;

    public UserEditViewModel(VeeezApiInterface veeezApiInterface) {
        if (apiInterface == null)
            apiInterface = veeezApiInterface;
    }

    public Single editProfile(String firstName,
                                String lastName,
                                String phoneNumber,
                                Integer gender,
                                String email,
                                String Address,
                                String birthday) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("FirstName", firstName);
        jsonObject.addProperty("LastName", lastName);
        jsonObject.addProperty("PhoneNumber", phoneNumber);
        jsonObject.addProperty("Gender", gender);
        return apiInterface.editUserData(jsonObject);
    }
}
