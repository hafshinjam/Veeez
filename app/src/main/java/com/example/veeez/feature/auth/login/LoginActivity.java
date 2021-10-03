package com.example.veeez.feature.auth.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.veeez.data.UserManager;
import com.example.veeez.feature.auth.register.SignUpActivity;
import com.example.veeez.feature.auth.verification.ReceiveCodeActivity;
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

public class LoginActivity extends AppCompatActivity {

    AppCompatButton appCompatButton;
    AppCompatTextView register_txt;
    private EditText phoneEdt;
    private UserManager userManager;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private LoginViewModel loginViewModel = new LoginViewModel(VeeezApiInterfaceProvider.getInstance());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        initView();

        userManager = new UserManager(this);
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
                loginViewModel.login(phoneEdt.getText().toString())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new SingleObserver<AuthResponse>() {
                            @Override
                            public void onSubscribe(@NotNull Disposable d) {
                                compositeDisposable.add(d);
                            }

                            @Override
                            public void onSuccess(@NotNull AuthResponse authResponse) {

                                if (authResponse.getStatus()==0){
                                    Toast.makeText(LoginActivity.this, authResponse.getText(), Toast.LENGTH_SHORT).show();
                                }

                                if (authResponse.getStatus() == 1) {
                                    userManager.saveUserId(authResponse.getUserId());
                                    Intent intent = new Intent(LoginActivity.this, ReceiveCodeActivity.class);
                                    intent.putExtra(Base.EXTRA_KEY_DATA, phoneEdt.getText().toString());
                                    startActivity(intent);

                                }
                            }

                            @Override
                            public void onError(@NotNull Throwable e) {
                                   Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });
    }

    private void initView() {
        appCompatButton = findViewById(R.id.login_btn);
        register_txt = findViewById(R.id.register_txt);
        phoneEdt = findViewById(R.id.login_phone_edt);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (compositeDisposable.size() > 1) {
            compositeDisposable.clear();
        }
    }
}