package com.example.veeez.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;

import com.example.veeez.common.Base;

public class UserManager {
    private SharedPreferences sharedPreferences;

    public UserManager(Context context) {
        sharedPreferences = context.getSharedPreferences("user_shared_pref", Context.MODE_PRIVATE);
    }

    public void saveUserId(String userId) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Base.EXTRA_KEY_USER_AUTH_STATUS, userId);
        editor.apply();
    }

    public void saveUserImage(Uri uri) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Base.EXTRA_IMAGE_URI, uri.toString()).apply();
    }

    public Uri getUserImageUri() {
        return Uri.parse(sharedPreferences.getString(Base.EXTRA_IMAGE_URI, ""));
    }

    public String getUserId() {
        return sharedPreferences.getString(Base.EXTRA_KEY_USER_AUTH_STATUS, "");
    }
}
