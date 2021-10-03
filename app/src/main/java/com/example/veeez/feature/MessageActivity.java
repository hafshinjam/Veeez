package com.example.veeez.feature;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.veeez.R;
import com.example.veeez.feature.message.MessageRecyclerAdapter;
import com.example.veeez.feature.message.MessageViewModel;
import com.example.veeez.feature.message.UserMessageResponse;
import com.example.veeez.services.http.veeez.VeeezApiInterfaceProvider;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MessageActivity extends AppCompatActivity {

    ImageView ic_back;

    private MessageViewModel messageViewModel;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private RecyclerView messageRecycler;
    private MessageRecyclerAdapter mMessageRecyclerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        ic_back = findViewById(R.id.address_back_btn);
        messageRecycler = findViewById(R.id.addressRv);

        messageViewModel = new MessageViewModel(VeeezApiInterfaceProvider.getInstance());
        messageRecycler.setLayoutManager(new LinearLayoutManager(this));

        messageViewModel.getUserMessages(this)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<UserMessageResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull UserMessageResponse o) {
                        mMessageRecyclerAdapter = new MessageRecyclerAdapter(new UserMessageResponse());
                        messageRecycler.setAdapter(mMessageRecyclerAdapter);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("retrieveError", e.getMessage());
                    }
                });


        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (compositeDisposable != null)
            compositeDisposable.clear();
    }
}