package com.example.veeez.feature.splash;

import androidx.lifecycle.ViewModel;

import com.example.veeez.services.http.veeez.VeeezApiInterface;
import com.example.veeez.services.http.veeez.VeeezApiService;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SplashViewModel extends ViewModel {
    private VeeezApiInterface apiInterface = null;


    public SplashViewModel(VeeezApiInterface apiInterface) {
            this.apiInterface = apiInterface;
    }

    public Single<Boolean> getServerStatus(){
       return apiInterface.isServerReady();
    }
}
