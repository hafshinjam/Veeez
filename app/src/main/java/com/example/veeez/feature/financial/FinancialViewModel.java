package com.example.veeez.feature.financial;

import com.example.veeez.services.http.veeez.VeeezApiInterface;

import io.reactivex.Single;

public class FinancialViewModel {
    private VeeezApiInterface mVeeezApiInterface;

    public FinancialViewModel(VeeezApiInterface apiInterface) {
        mVeeezApiInterface = apiInterface;
    }

    public Single<FinancialResponse> getFinancialResponse() {
        String id = "";
        return mVeeezApiInterface.getFinancialItems(id);
    }
}
