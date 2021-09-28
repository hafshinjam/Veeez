package com.example.veeez.Activities.auth.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.veeez.Activities.auth.register.SignUpActivity;
import com.example.veeez.Activities.verification.ReceiveCodeActivity;
import com.example.veeez.R;
import com.example.veeez.data.AuthResponse;
import com.example.veeez.services.http.veeez.VeeezApiInterfaceProvider;

import org.jetbrains.annotations.NotNull;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity {

    AppCompatButton appCompatButton;
    AppCompatTextView register_txt;
    private EditText phoneEdt;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private final LoginViewModel viewModel = new LoginViewModel(VeeezApiInterfaceProvider.getInstance());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        initView();

        register_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
                finish();
            }
        });

        appCompatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewModel.login(phoneEdt.getText().toString())
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
                                    startActivity(new Intent(LoginActivity.this, ReceiveCodeActivity.class));
                                    finish();
                                }
                            }

                            @Override
                            public void onError(@NotNull Throwable e) {
                                Log.i("TAG", "onError: "+e.toString());
                            }
                        });


            }
        });
    }

    private void initView() {
        appCompatButton=findViewById(R.id.login_btn);
        register_txt=findViewById(R.id.register_txt);
        phoneEdt = findViewById(R.id.login_phone_edt);
    }
}