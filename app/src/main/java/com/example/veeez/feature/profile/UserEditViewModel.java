package com.example.veeez.feature.profile;

import android.content.Context;

import com.example.veeez.data.UserManager;
import com.example.veeez.services.http.veeez.VeeezApiInterface;
import com.google.gson.JsonObject;

import io.reactivex.Single;

public class UserEditViewModel {
    private VeeezApiInterface apiInterface;
    private UserManager userManager;

    public UserEditViewModel(VeeezApiInterface veeezApiInterface, Context context) {
        userManager = new UserManager(context);
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
        jsonObject.addProperty("UserId", userManager.getUserId());
        jsonObject.addProperty("FirstName", firstName);
        jsonObject.addProperty("LastName", lastName);
        jsonObject.addProperty("Phone", phoneNumber);
        jsonObject.addProperty("Gender", gender);
        jsonObject.addProperty("Email", email);
        jsonObject.addProperty("FullAddress", address);
        jsonObject.addProperty("BirthDate", birthday);
        return apiInterface.editUserData(jsonObject);
    }
}
