package com.example.veeez.feature.message;

import android.content.Context;

import com.example.veeez.data.UserManager;
import com.example.veeez.services.http.veeez.VeeezApiInterface;

import io.reactivex.Single;

public class MessageViewModel {

    private VeeezApiInterface apiInterface;

    public MessageViewModel(VeeezApiInterface veeezApiInterface) {
        apiInterface = veeezApiInterface;
    }

    public Single<UserMessageResponse> getUserMessages(Context context) {
        UserManager userManager = new UserManager(context);
        String userId = userManager.getUserId();
        return apiInterface.getUserMessages(userId);
    }
}
