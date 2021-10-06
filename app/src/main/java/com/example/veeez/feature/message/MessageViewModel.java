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
        String id = "ba53ab81-fbf3-40b1-aacc-6be48841264f";
//        UserManager userManager = new UserManager(context);
//        String id = userManager.getUserId();
        return apiInterface.getUserMessages(id);
    }
}
