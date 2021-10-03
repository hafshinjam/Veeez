package com.example.veeez.data;

import android.content.Context;

public class UserManagerProvider {
    private static UserManager userManager;


    public static UserManager getInstance(Context context) {
        if (userManager == null){
            userManager = new UserManager(context);
        }
        return userManager;
    }
}
