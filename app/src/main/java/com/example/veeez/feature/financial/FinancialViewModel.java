package com.example.veeez.feature.financial;

import android.content.Context;

import com.example.veeez.data.UserManager;
import com.example.veeez.data.UserManagerProvider;
import com.example.veeez.services.http.veeez.VeeezApiInterface;

import io.reactivex.Single;

public class FinancialViewModel {
    private VeeezApiInterface mVeeezApiInterface;
    private UserManager userManager;

    public FinancialViewModel(VeeezApiInterface apiInterface, Context context) {
        mVeeezApiInterface = apiInterface;
        userManager = UserManagerProvider.getInstance(context);
    }

    public Single<FinancialResponse> getFinancialResponse() {
        String id ="ba53ab81-fbf3-40b1-aacc-6be48841264f";
        return mVeeezApiInterface.getFinancialItems(id);
    }
}
