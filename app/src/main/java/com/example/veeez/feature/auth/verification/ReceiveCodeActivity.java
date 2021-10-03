package com.example.veeez.feature.auth.verification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.veeez.feature.auth.login.LoginActivity;
import com.example.veeez.feature.home.HomeActivity;
import com.example.veeez.R;
import com.example.veeez.common.Base;
import com.example.veeez.data.AuthResponse;
import com.example.veeez.services.http.veeez.VeeezApiInterfaceProvider;

import org.jetbrains.annotations.NotNull;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ReceiveCodeActivity extends AppCompatActivity {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    EditText codeInputView;
    private AppCompatImageView resendCodeIv;
    AppCompatButton enter_code;

    private ReceiveCodeViewModel viewModel = new ReceiveCodeViewModel(VeeezApiInterfaceProvider.getInstance());
    private String userPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recive_code);

        userPhoneNumber = getIntent().getStringExtra(Base.EXTRA_KEY_DATA);

        codeInputView = findViewById(R.id.codeInputView);
        enter_code = findViewById(R.id.enter_code);

        initViews();


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
                                if (authResponse.getStatus() == 1) {
                                    codeInputView.setText("");
                                } else {
                                    Toast.makeText(ReceiveCodeActivity.this, authResponse.getText(), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onError(@NotNull Throwable e) {
                                Toast.makeText(ReceiveCodeActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        });
            }
        });

        enter_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewModel.submitVerification(userPhoneNumber, codeInputView.getText().toString())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new SingleObserver<AuthResponse>() {
                            @Override
                            public void onSubscribe(@NotNull Disposable d) {
                                compositeDisposable.add(d);
                            }

                            @Override
                            public void onSuccess(@NotNull AuthResponse authResponse) {

                                if (authResponse.getStatus() == 0) {
                                    Toast.makeText(ReceiveCodeActivity.this, authResponse.getText(), Toast.LENGTH_SHORT).show();
                                }
                                if (authResponse.getStatus() == 1) {
                                    Intent intent = new Intent(ReceiveCodeActivity.this, HomeActivity.class);
                                    startActivity(intent);
                                    finish();
                                }

                            }

                            @Override
                            public void onError(@NotNull Throwable e) {
                                Toast.makeText(ReceiveCodeActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (compositeDisposable.size() > 0) {
            compositeDisposable.clear();
        }
    }

    private void initViews() {
        resendCodeIv = findViewById(R.id.resend_code_iv);
    }
}