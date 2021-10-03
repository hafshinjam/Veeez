package com.example.veeez.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.veeez.common.Base;

public class UserManager {
    private SharedPreferences sharedPreferences;

    public UserManager(Context context) {
        sharedPreferences = context.getSharedPreferences("user_shared_pref",Context.MODE_PRIVATE);
    }

    public void saveUserId(String userId){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Base.EXTRA_KEY_USER_AUTH_STATUS,userId);
        editor.apply();
    }


    public String getUserId(){
        return sharedPreferences.getString(Base.EXTRA_KEY_USER_AUTH_STATUS,"");
    }
}
