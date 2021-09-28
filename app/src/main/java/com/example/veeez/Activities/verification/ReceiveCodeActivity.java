package com.example.veeez.Activities.verification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.veeez.Activities.HomeActivity;
import com.example.veeez.R;
import com.example.veeez.common.Base;
import com.example.veeez.data.AuthResponse;
import com.example.veeez.services.http.veeez.VeeezApiInterfaceProvider;
import com.raycoarana.codeinputview.CodeInputView;

import org.jetbrains.annotations.NotNull;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ReceiveCodeActivity extends AppCompatActivity {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    CodeInputView codeInputView;
    private AppCompatImageView resendCodeIv;
    AppCompatButton enter_code;

    private ReceiveCodeViewModel viewModel = new ReceiveCodeViewModel(VeeezApiInterfaceProvider.getInstance());
    private String userPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recive_code);

        userPhoneNumber = getIntent().getStringExtra(Base.EXTRA_KEY_DATA);

        codeInputView=findViewById(R.id.codeInputView);
        enter_code=findViewById(R.id.enter_code);

        initViews();

        //codeInputView.setCode("1234");
        codeInputView.setCode("9784");

        resendCodeIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.resendCode(userPhoneNumber)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new SingleObserver<AuthResponse>() {
                            @Override
                            public void onSubscribe(@NotNull Disposable d) {
                                compositeDisposable.add(d);
                            }

                            @Override
                            public void onSuccess(@NotNull AuthResponse authResponse) {
                                // TODO: 9/26/2021 show new code to user
                            }

                            @Override
                            public void onError(@NotNull Throwable e) {

                            }
                        });
            }
        });

       enter_code.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               viewModel.submitVerification(userPhoneNumber,codeInputView.getCode())
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new SingleObserver<AuthResponse>() {
                   @Override
                   public void onSubscribe(@NotNull Disposable d) {
                       compositeDisposable.add(d);
                   }

                   @Override
                   public void onSuccess(@NotNull AuthResponse authResponse) {
                       if (authResponse.getCode().equals("1")){
                           Intent intent=new Intent(ReceiveCodeActivity.this, HomeActivity.class);
                           startActivity(intent);
                           finish();
                       }
                   }

                   @Override
                   public void onError(@NotNull Throwable e) {
                       Log.e("TAG", "onError: "+e.toString() );
                   }
               });

           }
       });



    }

    private void initViews() {
        resendCodeIv=findViewById(R.id.resend_code_iv);
    }
}