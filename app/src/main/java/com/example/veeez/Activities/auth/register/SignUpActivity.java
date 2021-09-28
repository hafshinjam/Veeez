package com.example.veeez.Activities.auth.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.veeez.Activities.verification.ReceiveCodeActivity;
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

public class SignUpActivity extends AppCompatActivity {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private EditText nameEdt;
    private EditText familyEdt;
    private EditText phoneEdt;
    private EditText introCodeEdt;
    private Button registerBtn;

    private SignUpViewModel registerViewModel = new SignUpViewModel(VeeezApiInterfaceProvider.getInstance());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        initViews();


        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerViewModel.register(

                        nameEdt.getText().toString(),
                        familyEdt.getText().toString(),
                        phoneEdt.getText().toString(),
                        introCodeEdt.getText().toString()
                ).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new SingleObserver<AuthResponse>() {
                            @Override
                            public void onSubscribe(@NotNull Disposable d) {
                                compositeDisposable.add(d);
                            }

                            @Override
                            public void onSuccess(@NotNull AuthResponse authResponse) {
                                Intent intent = new Intent(SignUpActivity.this, ReceiveCodeActivity.class);
                                intent.putExtra(Base.EXTRA_KEY_DATA,phoneEdt.getText().toString());
                                startActivity(intent);

                            }

                            @Override
                            public void onError(@NotNull Throwable e) {
                                Log.e("TAG", "onError: "+e.getMessage() );
                            }
                        });


            }
        });
    }

    private void initViews() {
        nameEdt = findViewById(R.id.login_phone_edt);
        familyEdt = findViewById(R.id.family_edt);
        phoneEdt = findViewById(R.id.phone_edt);
        introCodeEdt = findViewById(R.id.intro_code_edt);

        registerBtn = findViewById(R.id.register_btn);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }
}