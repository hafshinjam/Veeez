package com.example.veeez.feature.address;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.veeez.R;
import com.example.veeez.data.UserAddressResponse;
import com.example.veeez.data.UserManager;
import com.example.veeez.data.UserManagerProvider;
import com.example.veeez.services.http.veeez.VeeezApiInterfaceProvider;

import org.jetbrains.annotations.NotNull;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AddressActivity extends AppCompatActivity {
    ImageView ic_back;
    private RecyclerView addressRv;
    private AddressViewModel viewModel = new AddressViewModel(VeeezApiInterfaceProvider.getInstance());
    //private UserManager userManager = UserManagerProvider.getInstance(this);
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    // TODO: 10/2/2021 setup adapter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);


        initView();


        addressRv.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));

        viewModel.getAddress("ba53ab81-fbf3-40b1-aacc-6be48841264f")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<UserAddressResponse>() {
                    @Override
                    public void onSubscribe(@NotNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NotNull UserAddressResponse userAddressResponse) {
                        addressRv.setAdapter(new AddressRecyclerAdapter(userAddressResponse)) ;
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        Log.i("TAG", "onError: "+e.toString());
                    }
                });


        ic_back=findViewById(R.id.address_back_btn);
        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        addressRv = findViewById(R.id.address_rv);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}