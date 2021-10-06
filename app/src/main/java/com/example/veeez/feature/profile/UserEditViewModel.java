package com.example.veeez.feature.profile;

import com.example.veeez.services.http.veeez.VeeezApiInterface;
import com.google.gson.JsonObject;

import io.reactivex.Single;

public class UserEditViewModel {
    private VeeezApiInterface apiInterface;
    private String id = "";

    public UserEditViewModel(VeeezApiInterface veeezApiInterface, String userId) {
        id = userId;
        if (apiInterface == null)
            apiInterface = veeezApiInterface;
    }

    public Single editProfile(
            String firstName,
            String lastName,
            String phoneNumber,
            Integer gender,
            String email,
            String address,
            String birthday) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("UserId", id);
        jsonObject.addProperty("FirstName", firstName);
        jsonObject.addProperty("LastName", lastName);
        jsonObject.addProperty("Phone", phoneNumber);
        jsonObject.addProperty("Gender", gender);
        jsonObject.addProperty("Email", email);
        jsonObject.addProperty("FullAddress", address);
        if (birthday != null)
            jsonObject.addProperty("BirthDate", birthday);
        return apiInterface.editUserData(jsonObject);
    }
}
